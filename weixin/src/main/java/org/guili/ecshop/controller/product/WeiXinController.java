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
import org.guili.ecshop.business.weixin.bean.ArticleType;
import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.business.weixin.bean.WeixinListVo;
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
	
	private static final int TOTAL_NUM=1000;
	private static final int ONE_PAGE=25;
	private static final int Article_Relation_Num=10;
	private static final int TIME_OUT=5000;
	private static final String PUBLIC_KEY="Xiaozd12";
	
	//推送消息时间
	private static final int MIN_HOUR=8;
	private static final int MAX_HOUR=23;
	//最新的数量
	private static final int NEARLY_COUNT=10;
	
	
	
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
	
		//微信列表页
		@RequestMapping(value={ "/weixin/list.htm"},method={RequestMethod. GET})
		public String list(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
						,Long tagid,Long nextId,Long prevId) throws Exception{
			
			//一页评论
			WeixinListVo weixinListVo=weiXinArticleService.selectOnePageArticleInMongoByTag(tagid,nextId, prevId,null);
			
			 //如果返回数据为null指向404
	        if(weixinListVo==null){
	        	//测试页面
				return "redirect:/errors/404.htm";
	        }
	        //用于回显的url部分
			String urlParam=this.setTagid(tagid);
			
			//最新微信号
			WeixinListVo nearlyWeixinList=weiXinArticleService.getNearlyArticles(null, null, null, null, NEARLY_COUNT);
			request.setAttribute("newWeiXinHao", nearlyWeixinList.getWeiXinArticleList());
			
			//分页参数
	        request.setAttribute("urlParam", urlParam);
	        request.setAttribute("tagid", tagid);
	        
	        request.setAttribute("weiXinArticleList", weixinListVo.getWeiXinArticleList());
	        //
	        if(weixinListVo.getPreId()!=null){
	        	request.setAttribute("preId", weixinListVo.getPreId());
	        }
	        //
	        if(weixinListVo.getNextId()!=null){
	        	request.setAttribute("nextId", weixinListVo.getNextId());
	        }
	        request.setAttribute("tagname", this.categoryName(tagid));
			//测试页面
			return "weixin/weixinlist";
		}
		
		//微信列表页
		@RequestMapping(value={ "/weixin/hot.htm"},method={RequestMethod. GET})
		public String hot(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
						,Long tagid,Long nextId) throws Exception{
			
			Integer status=ArticleType.ArticleType_SPECIAL.getValue();
			
			//查询热门文章列表
			WeixinListVo weixinListVo=weiXinArticleService.selectOnePageArticleInMongoByTag(null,nextId, null,status);
			 //如果返回数据为null指向404
	        if(weixinListVo==null || weixinListVo.getWeiXinArticleList()==null || weixinListVo.getWeiXinArticleList().size()==0){
	        	//测试页面
				return "redirect:/errors/404.htm";
	        }
			//分页参数
	        request.setAttribute("weiXinArticleList", weixinListVo.getWeiXinArticleList());
	        //
	        if(weixinListVo.getNextId()!=null && weixinListVo.getWeiXinArticleList().size()==ONE_PAGE){
	        	request.setAttribute("nextId", weixinListVo.getNextId());
	        }
			//测试页面
			return "weixin/weixinlist_hot";
		}
		
		
		//微信列表页
		@RequestMapping(value={ "/index.htm", "/" })
		public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap
						  	,String pageNum,Long tagid) throws Exception{
			
			WeixinListVo weixinListVo=weiXinArticleService.selectOnePageArticleInMongoByTag(tagid,null, null,null);
			 //如果返回数据为null指向404
	        if(weixinListVo==null){
	        	//测试页面
				return "redirect:/errors/404.htm";
	        }
	        //用于回显的url部分
			String urlParam=this.setTagid(tagid);
			//热门公众号
			WeixinListVo nearlyWeixinList=weiXinArticleService.getNearlyArticles(null, null, null, null, NEARLY_COUNT);
			request.setAttribute("newWeiXinHao", nearlyWeixinList.getWeiXinArticleList());
			//分页参数
	        request.setAttribute("urlParam", urlParam);
	        request.setAttribute("tagid", tagid);
	        
	        request.setAttribute("weiXinArticleList", weixinListVo.getWeiXinArticleList());
	        //
	        if(weixinListVo.getPreId()!=null){
	        	request.setAttribute("preId", weixinListVo.getPreId());
	        }
	        //
	        if(weixinListVo.getNextId()!=null){
	        	request.setAttribute("nextId", weixinListVo.getNextId());
	        }
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
						  	,String weixin_hao,Long preId,Long nextId) throws Exception{
			
			//一页评论
			int totalRow=TOTAL_NUM;
	        WeixinListVo weixinListVo=weiXinArticleService.selectOneHaoPageArticleInMongoByTag(weixin_hao, nextId, preId);
	        
	        //如果返回数据为null指向404
	        if(weixinListVo==null){
	        	//测试页面
				return "redirect:/errors/404.htm";
	        }
	        //查询一个账号信息
	        if(weixinListVo!=null && weixinListVo.getWeiXinArticleList()!=null && weixinListVo.getWeiXinArticleList().size()>0){
	        	request.setAttribute("weiXinHao", weixinListVo.getWeiXinArticleList().get(0));	
	        }
	        //
	        if(weixinListVo.getPreId()!=null){
	        	request.setAttribute("preId", weixinListVo.getPreId());
	        }
	        //
	        if(weixinListVo.getNextId()!=null){
	        	request.setAttribute("nextId", weixinListVo.getNextId());
	        }
	        //判断是否还有下一页
	        if(weixinListVo.getWeiXinArticleList().size()==ONE_PAGE){
	        	request.setAttribute("hasNext", true);
	        }
	        
	        request.setAttribute("weiXinArticleList", weixinListVo.getWeiXinArticleList());
			//测试页面
			return "weixin/weixinone";
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
		
}
