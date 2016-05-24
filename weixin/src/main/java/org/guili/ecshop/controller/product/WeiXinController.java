package org.guili.ecshop.controller.product;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.guili.ecshop.business.weixin.IWeiXinArticleService;
import org.guili.ecshop.business.weixin.IWeiXinService;
import org.guili.ecshop.business.weixin.IWeiXinSpiderService;
import org.guili.ecshop.business.weixin.WeiXinArticleSpider;
import org.guili.ecshop.business.weixin.WeiXinSpiderType;
import org.guili.ecshop.business.weixin.WeiXinSpiders;
import org.guili.ecshop.business.weixin.WeixinSiteMapService;
import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.controller.common.BaseProfileController;
import org.guili.ecshop.util.common.UrlHelper;
import org.guili.ecshop.util.common.page.Pager;
import org.guili.ecshop.util.common.page.PagerHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 产品控制controller
 * @author guili
 *
 */
@Controller
public class WeiXinController extends BaseProfileController{
	
	@Resource(name="weixinService")
	private IWeiXinService weiXinService;
	@Resource(name="weiXinArticleService")
	private IWeiXinArticleService weiXinArticleService;
	
	@Resource(name="weixinSiteMapService")
	private WeixinSiteMapService weixinSiteMapService;
	private static final int TOTAL_NUM=1000;
	private static final int ONE_PAGE=25;
	private static final int Article_Relation_Num=10;
	private static final int TIME_OUT=5000;
	private static final String PUBLIC_KEY="Xiaozd12";
	
	//推送消息时间
	private static final int MIN_HOUR=8;
	private static final int MAX_HOUR=23;
	
	
	
	//测试微信公众号详细页
	@RequestMapping(value={ "/weixin/detail.htm"},method={RequestMethod. GET})
	public String detail(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
			,@RequestParam(value = "hash", required = false) Integer hash) throws Exception{
		
		if(hash==null){
			return "weixin/weixinchuansong";
		}
		//测试页面
		WeiXinArticle weiXinArticle = weiXinArticleService.selectWeiXinArticleByhash(hash);
		
		//查询该微信号 最新10篇文章
		List<WeiXinArticle> weiXinArticleList=weiXinArticleService.selectPageArticleInMongoByTag(null, weiXinArticle.getWeixin_hao(), 0, Article_Relation_Num);
		
		request.setAttribute("weiXinArticle",weiXinArticle);
		request.setAttribute("weiXinArticleList",weiXinArticleList);
		
		return "weixin/weixin_new_detail";
	}
	
	
	
	@RequestMapping(value={ "/weixin/spider.htm"},method={RequestMethod. GET})
	public void batAddWenda(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,String key) throws Exception{
		if(StringUtils.isEmpty(key) || !key.equals(PUBLIC_KEY)){
			return;
		}
		
//		//微信
		IWeiXinSpiderService wentiSpiderService=WeiXinSpiders.getHandler(WeiXinSpiderType.WEIXIN);
		
		String urlpre="http://weixin.sogou.com/pcindex/pc/";
		String urlend=".html";
		
		//循环抓取数据
		while(true){
			for(int i=0;i<=19;i++){
//				for(int i=0;i<=2;i++){
					String urlmid="pc_"+i;
					for(int j=0;j<=2;j++){
						String innerurl="";
						if(j==0){
							innerurl=urlpre+urlmid+"/"+urlmid+urlend;
						}else{
							innerurl=urlpre+urlmid+"/"+j+urlend;
						}
						System.out.println(innerurl);
						if(StringUtils.isEmpty(innerurl)){
							return;
						}
						Thread.sleep(TIME_OUT);
						//抓取数据
						wentiSpiderService.batCrawlWeixin(innerurl, true);
					}
			}
			
			//推送数据到baidu
			pushDataToBaidu();
		}
		
	}
	
	//推送数据到baidu
	private void pushDataToBaidu(){
		//如果当前时间是8点到24点允许推送消息
		Calendar now=Calendar.getInstance();
		int hour=now.get(Calendar.HOUR_OF_DAY);
		if(hour>=MIN_HOUR && hour<=MAX_HOUR){
			//实时推送baidu收录
			try {
				weixinSiteMapService.onlinePush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
		//微信列表页
		@RequestMapping(value={ "/weixin/list.htm"},method={RequestMethod. GET})
		public String list(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
						,Long tagid,String weixinHao,String pageNum) throws Exception{
			
			if(tagid==null){
				tagid=1L;
			}
			//一页评论
			int totalRow=TOTAL_NUM;
			//分页信息
	        Pager pager=null;
	        String basePath=UrlHelper.getRealPath(request); 			  //基础绝对路径
	        if(StringUtils.isNotEmpty(pageNum)){
	        	if(Integer.parseInt(pageNum)<1){
	        		pageNum="1";	        	
	        	}
	        	pager = PagerHelper.getPager(pageNum, totalRow,ONE_PAGE); //初始化分页对象  
	        }else{
	        	pager = PagerHelper.getPager("1", totalRow,ONE_PAGE); 	  //初始化分页对象  
	        }
	        pager.setLinkUrl(basePath+request.getRequestURI()); 		  //设置跳转路径  
	        request.setAttribute("pager", pager);
	        
//	        List<WeiXinArticle> weiXinArticleList=weiXinArticleService.selectPageArticleByTag(tagid, pager.getStartRow(), ONE_PAGE);
	        List<WeiXinArticle> weiXinArticleList=weiXinArticleService.selectPageArticleInMongoByTag(tagid, weixinHao, pager.getStartRow(), ONE_PAGE);
	        //用于回显的url部分
			String urlParam=this.setTagid(tagid);
			
			//热门公众号
			//最新微信号
			List<WeiXinHao>  newWeiXinHaos = weiXinService.selectNewHaosInMongo(8);
			request.setAttribute("newWeiXinHao", newWeiXinHaos);
			
			//热门id，
//			List<WeiXinHao> reWeiXinHao=weiXinService.selectWeiXinByIds(WeixinConstans.re_ids);
//			//推荐id，
//			List<WeiXinHao> tuijianWeiXinHao=weiXinService.selectWeiXinByIds(WeixinConstans.tuijian_ids);
//			//最新收录，
//			List<WeiXinHao> newWeiXinHao=weiXinService.selectNewWeiXin();
//			request.setAttribute("reWeiXinHao", reWeiXinHao);
//			request.setAttribute("tuijianWeiXinHao", tuijianWeiXinHao);
//			request.setAttribute("newWeiXinHao", newWeiXinHao);
			//分页参数
	        request.setAttribute("urlParam", urlParam);
	        request.setAttribute("tagid", tagid);
	        
	        request.setAttribute("weiXinArticleList", weiXinArticleList);
	        request.setAttribute("tagname", this.categoryName(tagid));
			//测试页面
			return "weixin/weixinlist";
		}
		
		//微信列表页
		@RequestMapping(value={ "/index.htm", "/" })
		public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
						  	,String pageNum,Long tagid) throws Exception{
			if(tagid==null){
				tagid=1L;
			}
			//一页评论
			int totalRow=TOTAL_NUM;
			//分页信息
	        Pager pager=null;
	        String basePath=UrlHelper.getRealPath(request); 			  //基础绝对路径
	        if(StringUtils.isNotEmpty(pageNum)){
	        	if(Integer.parseInt(pageNum)<1){
	        		pageNum="1";	        	
	        	}
	        	pager = PagerHelper.getPager(pageNum, totalRow,ONE_PAGE); //初始化分页对象  
	        }else{
	        	pager = PagerHelper.getPager("1", totalRow,ONE_PAGE); 	  //初始化分页对象  
	        }
	        pager.setLinkUrl(basePath+request.getRequestURI()); 		  //设置跳转路径  
	        request.setAttribute("pager", pager);
	        List<WeiXinArticle> weiXinArticleList=weiXinArticleService.selectPageArticleInMongoByTag(tagid, null, pager.getStartRow(), ONE_PAGE);
	        //用于回显的url部分
			String urlParam=this.setTagid(tagid);
			
			//最新微信号
			List<WeiXinHao>  newWeiXinHaos = weiXinService.selectNewHaosInMongo(8);
			request.setAttribute("newWeiXinHao", newWeiXinHaos);
			//分页参数
	        request.setAttribute("urlParam", urlParam);
	        request.setAttribute("tagid", tagid);
	        
	        request.setAttribute("weiXinArticleList", weiXinArticleList);
	        request.setAttribute("tagname", this.categoryName(tagid));
			//测试页面
			return "weixin/weixinlist";
		}
		
		
		//微信列表页
		@RequestMapping(value={ "/weixin.htm"},method={RequestMethod. GET})
		public String weixin(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
						  	,String pageNum,Long tagid) throws Exception{
			
			if(tagid==null){
				tagid=1L;
			}
			//一页评论
			int totalRow=TOTAL_NUM;
			//分页信息
	        Pager pager=null;
	        String basePath=UrlHelper.getRealPath(request); 			  //基础绝对路径
	        if(StringUtils.isNotEmpty(pageNum)){
	        	if(Integer.parseInt(pageNum)<1){
	        		pageNum="1";	        	
	        	}
	        	pager = PagerHelper.getPager(pageNum, totalRow,ONE_PAGE); //初始化分页对象  
	        }else{
	        	pager = PagerHelper.getPager("1", totalRow,ONE_PAGE); 	  //初始化分页对象  
	        }
	        pager.setLinkUrl(basePath+request.getRequestURI()); 		  //设置跳转路径  
	        request.setAttribute("pager", pager);
	        
//	        List<WeiXinArticle> weiXinArticleList=weiXinArticleService.selectPageArticleByTag(tagid, pager.getStartRow(), ONE_PAGE);
	        List<WeiXinArticle> weiXinArticleList=weiXinArticleService.selectPageArticleInMongoByTag(tagid, null, pager.getStartRow(), ONE_PAGE);
	        //用于回显的url部分
			String urlParam=this.setTagid(tagid);
			
			//最新微信号
			List<WeiXinHao>  newWeiXinHaos = weiXinService.selectNewHaosInMongo(8);
			request.setAttribute("newWeiXinHao", newWeiXinHaos);
			//热门id，
//			List<WeiXinHao> reWeiXinHao=weiXinService.selectWeiXinByIds(WeixinConstans.re_ids);
//			//推荐id，
//			List<WeiXinHao> tuijianWeiXinHao=weiXinService.selectWeiXinByIds(WeixinConstans.tuijian_ids);
//			//最新收录，
//			List<WeiXinHao> newWeiXinHao=weiXinService.selectNewWeiXin();
//			request.setAttribute("reWeiXinHao", reWeiXinHao);
//			request.setAttribute("tuijianWeiXinHao", tuijianWeiXinHao);
//			request.setAttribute("newWeiXinHao", newWeiXinHao);
			//分页参数
	        request.setAttribute("urlParam", urlParam);
	        request.setAttribute("tagid", tagid);
	        
	        request.setAttribute("weiXinArticleList", weiXinArticleList);
	        request.setAttribute("tagname", this.categoryName(tagid));
			//测试页面
			return "weixin/weixinlist";
		}
		/**初始化参数
		 */
		private String setTagid(Long tagid){
			StringBuffer urlParam=new StringBuffer();
			urlParam.append("weixin/list-");
			//构造背景
			if(tagid!=null){
				urlParam.append(tagid+"-");
			}else{
				urlParam.append("-");
			}
			//微信号设置为null
			urlParam.append("-");
			return urlParam.toString();
		}
		
		//微信列表页
		@RequestMapping(value={ "/weixin/one.htm"},method={RequestMethod. GET})
		public String one(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
						  	,String pageNum,String weixin_hao) throws Exception{
			
			//一页评论
			int totalRow=TOTAL_NUM;
			//分页信息
	        Pager pager=null;
	        String basePath=UrlHelper.getRealPath(request); 			  //基础绝对路径
	        if(StringUtils.isNotEmpty(pageNum)){
	        	pager = PagerHelper.getPager(pageNum, totalRow,ONE_PAGE); //初始化分页对象  
	        }else{
	        	pager = PagerHelper.getPager("1", totalRow,ONE_PAGE); 	  //初始化分页对象  
	        }
	        pager.setLinkUrl(basePath+request.getRequestURI()); 		  //设置跳转路径  
	        request.setAttribute("pager", pager);
//	        WeiXinHao weiXinHao= weiXinService.selectWeiXinById(haoid);
	        WeiXinHao weiXinHao = weiXinService.selectHaoInMongoByHao(weixin_hao);
	      //用于回显的url部分
			String urlParam=this.setparam(weixin_hao);
			//分页参数
	        request.setAttribute("urlParam", urlParam);
	        
	        List<WeiXinArticle> weiXinArticleList=weiXinArticleService.selectPageArticleInMongoByTag(null, weixin_hao, pager.getStartRow(), ONE_PAGE);
	        request.setAttribute("weiXinArticleList", weiXinArticleList);
	        
	        //查询一个账号信息
	        if(weiXinArticleList!=null && weiXinArticleList.size()>0){
	        	request.setAttribute("weiXinHao", weiXinArticleList.get(0));	
	        }
	        
			//测试页面
			return "weixin/weixinone";
		}
		
		/**初始化参数
		 */
		private String setparam(String weixin_hao){
			StringBuffer urlParam=new StringBuffer();
			urlParam.append("weixin/one-");
			//构造背景
			if(StringUtils.isNotEmpty(weixin_hao)){
				urlParam.append(weixin_hao+"-");
			}else{
				urlParam.append("-");
			}
			return urlParam.toString();
		}
		
		private String categoryName(Long tagid){
			if(tagid==null){
				return "推荐";
			}
			//热门
			if(tagid==1L){
				return "热门";
			}
				//潮人帮
				if(tagid==11L){
					return "服装.搭配.化妆";
				}else
				//辣妈帮
				if(tagid==12L){
					return "辣妈帮.育儿经";
				}else
				//点赞党
				if(tagid==13L){
					return "杂说";
				}else
				//旅行家
				if(tagid==14L){
					return "旅行.摄影";
				}else
				//职场人
				if(tagid==15L){
					return "职场生活";
				}else
				//美食家
				if(tagid==16L){
					return "美食.菜谱";
				}else
				//古今通
				if(tagid==17L){
					return "历史.文学";
				}else
				//学霸族
				if(tagid==18L){
					return "人文.教育";
				}else
				//星座控
				if(tagid==19L){
					return "恋爱.星座";
				}else
				//体育迷
				if(tagid==20L){
					return "运动.体育";
				}else
				//推荐
				if(tagid==2L){
					return "推荐";
				}else
				//段子手
				if(tagid==8L){
					return "段子.笑话";
				}else
				//养生堂
				if(tagid==3L){
					return "健康.养生";
				}else
				//私房话
				if(tagid==4L){
					return "情感.心理";
				}else
				//八卦精
				if(tagid==5L){
					return "电影.音乐";
				}else
				//爱生活
				if(tagid== 6L){
					return "做自己.爱生活";
				}else
				//财经迷
				if(tagid==7L){
					return "理财.财经";
				}else
				//汽车迷
				if(tagid==9L){
					return "汽车达人";
				}else
				//科技咖
				if(tagid==10L){
					return "IT.科技";
				}
				else{
					return "推荐";
				}
		}
		
		
		/**
		 * 动态扩展 proxy ip
		 * @param request
		 * @param response
		 * @param modelMap
		 * @param hash
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value={ "/push.htm"},method={RequestMethod. GET})
		public String push(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
				,@RequestParam(value = "key", required = false) String key) throws Exception{
			
			//参数异常
			if(key==null ||  !key.equals(PUBLIC_KEY)){
				return "redirect:/index.htm";
			}
			//实时推送百度
			weixinSiteMapService.onlinePush();
			
			return "weixin/weixin_proxy";
		}
		
		/**
		 * 动态扩展 proxy ip
		 * @param request
		 * @param response
		 * @param modelMap
		 * @param hash
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value={ "/proxy.htm"},method={RequestMethod.POST})
		public String proxy(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
				,@RequestParam(value = "proxy_ips", required = false) String proxy_ips,@RequestParam(value = "is_add", required = false) boolean is_add) throws Exception{
			
			//参数异常
			if(proxy_ips==null ){
				return "redirect:/index.htm";
			}
			//增加proxy ip
			if(is_add){
				String[] proxyIps=proxy_ips.split(";");
				if(proxyIps==null || proxyIps.length==0){
					return "redirect:/index.htm";
				}
				//新增proxy ips
				for(String ipAndport:proxyIps){
					//blockingQueue.add(ipAndport);
					WeiXinArticleSpider.blockingQueue.add(ipAndport);
				}
				
			}else{
				//移除无用的proxy ip
				String[] proxyIps=proxy_ips.split(";");
				if(proxyIps==null || proxyIps.length==0){
					return "redirect:/index.htm";
				}
				//新增proxy ips
				for(String ipAndport:proxyIps){
					//blockingQueue.add(ipAndport);
					WeiXinArticleSpider.blockingQueue.remove(ipAndport);
				}
			}
			
			
			return "redirect:/index.htm";
		}
		
		/**
		 * 动态扩展 proxy ip
		 * @param request
		 * @param response
		 * @param modelMap
		 * @param hash
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value={ "/getProxy.htm"},method={RequestMethod. GET})
		public String proxy(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
				,@RequestParam(value = "key", required = false) String key) throws Exception{
			
			//参数异常
			if(key==null ||  !key.equals(PUBLIC_KEY)){
				return "redirect:/index.htm";
			}
			//增加proxy ip
			StringBuffer proxyIps=new StringBuffer();
			Iterator<String> iterator= WeiXinArticleSpider.blockingQueue.iterator();
			while(iterator.hasNext()){
				String ip=iterator.next();
				proxyIps.append(ip+";");
			}
			request.setAttribute("proxyips",proxyIps.toString());
			request.setAttribute("proxyips_size", WeiXinArticleSpider.blockingQueue.size());
			
			return "weixin/weixin_proxy";
		}
		
		//测试微信公众号详细页
		@RequestMapping(value={ "/weixin/createSiteMap.htm"},method={RequestMethod. GET})
		public String createSiteMap(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
				,String key) throws Exception{
			
			if(StringUtils.isEmpty(key) || !key.equals(PUBLIC_KEY)){
				return "weixin/weixinchuansong";
			}
			weixinSiteMapService.buildHaoSiteMap("E:/weixinspider/sitemap_detail.xml",40);
			
			return "weixin/weixin_proxy";
		}
		
	public static void main(String[] args) {
//		System.out.println("七夕节过去了,不少人忙着秀恩爱的日子刚得以消停,就纷纷反映他们最近被一条火爆朋友圈的信息刷爆了.对这条信息大感兴趣的不仅仅有仍是单身狗的那些人,就连恋爱中的人,结婚成家者也乐此不彼,加入其中.对于已经成双成对的人,你们有时间不去谈情说爱,居然还能...".length());
	}
}
