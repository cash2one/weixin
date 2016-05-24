package org.guili.ecshop.impl.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.dao.weixin.IWeiXinArticleDao;
import org.guili.ecshop.util.BasicSqlSupport;

/**
 * 微信文章dao
 * @author zhengdong.xiao
 *
 */
public class WeiXinArticleDao  extends BasicSqlSupport implements IWeiXinArticleDao {

	private static Logger logger=Logger.getLogger(WeiXinArticleDao.class);
	
	@Override
	public int addweixinArticle(WeiXinArticle weiXinArticle) {
		int count=0;
		try {
			count=this.session.insert("org.guili.ecshop.dao.weixin.IWeiXinArticleDao.addweixinArticle", weiXinArticle);
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}

	@Override
	public WeiXinArticle selectWeiXinArticleById(Long id) throws Exception {
		WeiXinArticle weiXinArticle=new WeiXinArticle();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.weixin.IWeiXinArticleDao.selectWeiXinArticleById", paramMap);
		if(list!=null && list.size()>0){
			weiXinArticle=(WeiXinArticle)list.get(0);
		}else{
			weiXinArticle=null;
		}
		logger.debug("success!");
		return weiXinArticle;
	}

	@Override
	public WeiXinArticle selectWeiXinArticleByhash(Long hash) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("titlehash", hash);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.weixin.IWeiXinArticleDao.selectWeiXinArticleByhash", paramMap);
		if(list!=null && list.size()>0){
			return (WeiXinArticle)list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<WeiXinArticle> selectPageArticleByHao(Long haoid, int start,int pagesize) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("hao_id", haoid);
		paramMap.put("start", start);
		paramMap.put("pagesize", pagesize);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.weixin.IWeiXinArticleDao.selectPageArticleByHao", paramMap);
		if(list!=null && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public List<WeiXinArticle> selectPageArticleByTag(Long tagid, int start,
			int pagesize) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("tag_id", tagid);
		paramMap.put("start", start);
		paramMap.put("pagesize", pagesize);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.weixin.IWeiXinArticleDao.selectPageArticleByTag", paramMap);
		if(list!=null && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

}
