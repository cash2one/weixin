package org.guili.ecshop.business.weixin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.guili.ecshop.bean.common.DomainConstans;
import org.guili.ecshop.business.guava.GuavaCacheService;
import org.guili.ecshop.business.guava.GuavaCacheServiceImpl;
import org.guili.ecshop.business.mongo.MongoService;
import org.guili.ecshop.business.weixin.IWeiXinArticleService;
import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeixinListVo;
import org.guili.ecshop.dao.weixin.IWeiXinArticleDao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**
 * 微信文章服务
 * @author zhengdong.xiao
 *
 */
public class WeiXinArticleService implements IWeiXinArticleService {

	private static Logger logger=Logger.getLogger(WeiXinService.class);
	private IWeiXinArticleDao weiXinArticleDao;
	
    private MongoService mongoService;
    
    private GuavaCacheService guavaCacheService;
    
	private String articleCollection=DomainConstans.mongodb_article_collectionName;
	
	private final int PAGE_SIZE=25;
	
	@Override
	public int addweixinArticle(WeiXinArticle weiXinArticle) {
		if(weiXinArticle.getTitlehash() !=null && weiXinArticle.getTitlehash()>=0){
			return 0;
		}
		//防止重复插入。
		 if(selectWeiXinArticleByhash(weiXinArticle.getTitlehash())!=null){
			 return 0;
		 }
		 System.out.println("title..."+weiXinArticle.getTitle());
		return weiXinArticleDao.addweixinArticle(weiXinArticle);
	}

	@Override
	public WeiXinArticle selectWeiXinArticleById(Long id) throws Exception {
		if(id !=null && id>=0){
			return weiXinArticleDao.selectWeiXinArticleById(id);
		}
		return null;
	}

	@Override
	public WeiXinArticle selectWeiXinArticleByhash(Integer hash) {
		if(hash !=null){
			List<Document> docs=mongoService.findArticle(null, hash, null, null, null, 0, 1, articleCollection);
			if(docs==null || docs.isEmpty()){
				return null;
			}
			String jsonString = docs.get(0).toJson();
			
//			WeiXinArticle weiXinArticle=JSON.parseObject(jsonString, WeiXinArticle.class);
			JSONObject jsonObject= JSON.parseObject(jsonString);
			WeiXinArticle weiXinArticle = JSONObject.toJavaObject(jsonObject, WeiXinArticle.class);
			return weiXinArticle;
		}
		return null;
	}

//	@Override
//	public int batAddWeiXinArticle(List<WeiXinArticle> weiXinArticleList) {
//		if(weiXinArticleList==null || weiXinArticleList.isEmpty()){
//			return 0;
//		}
//		for(WeiXinArticle weiXinArticle:weiXinArticleList){
//			if(weiXinArticle==null){
//				continue;
//			}
//			if(weiXinArticle.getTitlehash() ==null){
//				continue;
//			}
//			//防止重复插入。
//			 if(selectWeiXinArticleByhash(new Long(weiXinArticle.getTitlehash()))!=null){
//				 continue;
//			 }
//			//添加微信文章
//			 weiXinArticleDao.addweixinArticle(weiXinArticle);
//		}
//		return 0;
//	}
	
	@Override
	public int batAddWeiXinArticle(List<WeiXinArticle> weiXinArticleList) {
		if(weiXinArticleList==null || weiXinArticleList.isEmpty()){
			return 0;
		}
		for(WeiXinArticle weiXinArticle:weiXinArticleList){
			if(weiXinArticle==null){
				continue;
			}
			if(weiXinArticle.getTitlehash() ==null){
				continue;
			}
			//防止重复插入。
			 if(this.selectArticleInMongoByHash(weiXinArticle.getTitlehash())){
				 continue;
			 }
			 System.out.println("add..."+weiXinArticle.getTitle());
			//添加微信文章
//			 weiXinArticleDao.addweixinArticle(weiXinArticle);
			 this.addArticleInMongo(weiXinArticle);
		}
		return 0;
	}

	public IWeiXinArticleDao getWeiXinArticleDao() {
		return weiXinArticleDao;
	}

	public void setWeiXinArticleDao(IWeiXinArticleDao weiXinArticleDao) {
		this.weiXinArticleDao = weiXinArticleDao;
	}

	@Override
	public List<WeiXinArticle> selectPageArticleByHao(Long haoid, int start,
			int pagesize) {
		List<WeiXinArticle> weiXinArticle=weiXinArticleDao.selectPageArticleByHao(haoid, start, pagesize);
		if(weiXinArticle==null){
			return new ArrayList<WeiXinArticle>();
		}
		return weiXinArticle;
	}

	@Override
	public List<WeiXinArticle> selectPageArticleByTag(Long tagid, int start,
			int pagesize) {
		List<WeiXinArticle> weiXinArticle=weiXinArticleDao.selectPageArticleByTag(tagid, start, pagesize);
		if(weiXinArticle==null){
			return new ArrayList<WeiXinArticle>();
		}
		return weiXinArticle;
	}

	private static final boolean FIND=true;
	private static final boolean NOT_FIND=false;
	@Override
	public boolean selectArticleInMongoByHash(Integer hash) {
		System.out.println("hash:-----"+hash);
		List<Document> docs=mongoService.findArticle(null, hash, null, null, null, 0, 1, articleCollection);
		
		if(docs==null || docs.isEmpty()){
			return NOT_FIND;
		}else{
			return FIND;
		}
	}

	@Override
	public int addArticleInMongo(WeiXinArticle weiXinArticle) {
		//对象转json
		String jsonString=JSON.toJSONString(weiXinArticle);
		//存mongo
		mongoService.insertMany(jsonString, articleCollection);
		return 1;
	}

	public MongoService getMongoService() {
		return mongoService;
	}

	public void setMongoService(MongoService mongoService) {
		this.mongoService = mongoService;
	}
	

	public void setGuavaCacheService(GuavaCacheService guavaCacheService) {
		this.guavaCacheService = guavaCacheService;
	}

	@Override
	public List<WeiXinArticle> selectPageArticleInMongoByTag(Long tagid,String weixin_hao,
			int start, int pagesize) {
		
		if(tagid ==null && StringUtils.isEmpty(weixin_hao)){
			return null;
		}
		
		List<Document> docs=mongoService.findArticle(null, weixin_hao, tagid, start, pagesize, articleCollection);
		//查询所有_ids并缓存
		if(docs==null || docs.isEmpty()){
			return null;
		}
		
		List<WeiXinArticle> weiXinArticleList=Lists.newArrayList();
		//组合数据
		for(Document doc:docs){
			String jsonString = doc.toJson();
			JSONObject jsonObject= JSON.parseObject(jsonString);
			WeiXinArticle weiXinArticle = JSONObject.toJavaObject(jsonObject, WeiXinArticle.class);
			//设置相对时间
			weiXinArticle.setCreateTime(new  Date(jsonObject.getJSONObject("createTime").getLong("$numberLong")));
			weiXinArticle.setRelativeTime();
			
			weiXinArticleList.add(weiXinArticle);
		}
		return weiXinArticleList;
	}
	
	@Override
	public WeixinListVo selectOneHaoPageArticleInMongoByTag(String weixin_hao,
			Long nextKey,Long  prevKey) {
		
		if(StringUtils.isEmpty(weixin_hao)){
			return null;
		}
		
		//查询所有_ids并缓存
		List<String> onePageIds=Lists.newArrayList();
		if(nextKey!=null){
			onePageIds=guavaCacheService.getCallableOneHaoCache(GuavaCacheServiceImpl.CACHE_HAO_KEY_PRE+weixin_hao, nextKey);
		}else{
			onePageIds=guavaCacheService.getCallableOneHaoPrevCache(GuavaCacheServiceImpl.CACHE__HAO_KEY_PREV_PRE+weixin_hao,prevKey);
		}
		
//				List<String> onePageIds =guavaCacheService.getOnePageIds(allIds, page);
		
		//防止空数据
		if(onePageIds==null || onePageIds.isEmpty()){
			return null;
		}
		//根据一页ids查询doc
		List<Document> docs = mongoService.findOnePageArticle(onePageIds, articleCollection);
		if(docs==null || docs.isEmpty()){
			return null;
		}
		
		List<WeiXinArticle> weiXinArticleList=Lists.newArrayList();
		
		WeixinListVo weixinListVo=new WeixinListVo();
		//组合数据
		for(int i=0;i<docs.size();i++){
			Document  doc=docs.get(i);
			String jsonString = doc.toJson();
			JSONObject jsonObject= JSON.parseObject(jsonString);
			WeiXinArticle weiXinArticle = JSONObject.toJavaObject(jsonObject, WeiXinArticle.class);
			//设置相对时间
			weiXinArticle.setCreateTime(new  Date(jsonObject.getJSONObject("createTime").getLong("$numberLong")));
			weiXinArticle.setRelativeTime();
			weiXinArticleList.add(weiXinArticle);
			
			//设置前一页的结束
			if(0==i && (nextKey!=null || prevKey!=null)){
				weixinListVo.setPreId(jsonObject.getJSONObject("createTime").getLong("$numberLong"));
			}
			
			//设置后一页的起始id
			if(i==docs.size()-1){
				weixinListVo.setNextId(jsonObject.getJSONObject("createTime").getLong("$numberLong"));
			}
		}
		weixinListVo.setWeiXinArticleList(weiXinArticleList);
		
		return weixinListVo;
	}
	
	@Override
	public WeixinListVo selectOnePageArticleInMongoByTag(Long tagid,
			Long nextKey,Long  prevKey,Integer status) {
		
		if(tagid ==null){
			tagid=0L;;
		}
		
//		List<Document> docs=mongoService.findArticle(null, weixin_hao, tagid, start, pagesize, articleCollection);
		//查询所有_ids并缓存
		List<String> onePageIds=Lists.newArrayList();
		
		//如果是普通查询
		if(status==null){
			if(nextKey!=null && prevKey==null){
				String cache_key=GuavaCacheServiceImpl.CACHE_KEY_PRE+tagid;
				onePageIds=guavaCacheService.getCallableCache(cache_key,nextKey,null,PAGE_SIZE);
			}else if(nextKey==null && prevKey!=null){
				onePageIds=guavaCacheService.getCallablePreCache(GuavaCacheServiceImpl.CACHE_KEY_PREV_PRE+tagid,prevKey);
			}else{
				String cache_key=GuavaCacheServiceImpl.CACHE_KEY_PRE+tagid;
				onePageIds=guavaCacheService.getCallableCache(cache_key,nextKey,null,PAGE_SIZE);
			}
		}else{
			//如果是优质文章查询
			onePageIds=guavaCacheService.getCallableCache(GuavaCacheServiceImpl.CACHE_SPECIAL_KEY_PRE+tagid,nextKey,status,PAGE_SIZE);
		}
		
		//防止空数据
		if(onePageIds==null || onePageIds.isEmpty()){
			return null;
		}
		
		//查询一页ids
//		List<String> onePageIds =guavaCacheService.getOnePageIds(allIds, page);
		//根据一页ids查询doc
		List<Document> docs = mongoService.findOnePageArticle(onePageIds, articleCollection);
		if(docs==null || docs.isEmpty()){
			return null;
		}
		
		WeixinListVo weixinListVo = this.createReturnObject(docs, nextKey, prevKey);
		return weixinListVo;
	}
	
	
	/**
	 * 构建返回数据
	 * @param docs
	 * @param nextKey
	 * @param prevKey
	 * @return
	 */
	private WeixinListVo createReturnObject(List<Document> docs,Long nextKey,Long  prevKey){
		
		List<WeiXinArticle> weiXinArticleList=Lists.newArrayList();
		WeixinListVo weixinListVo=new WeixinListVo();
		//组合数据
		for(int i=0;i<docs.size();i++){
			Document  doc=docs.get(i);
			String jsonString = doc.toJson();
			JSONObject jsonObject= JSON.parseObject(jsonString);
			WeiXinArticle weiXinArticle = JSONObject.toJavaObject(jsonObject, WeiXinArticle.class);
			//设置相对时间
			weiXinArticle.setCreateTime(new  Date(jsonObject.getJSONObject("createTime").getLong("$numberLong")));
			weiXinArticle.setRelativeTime();
			weiXinArticleList.add(weiXinArticle);
			//设置前一页的结束
			if(0==i && (nextKey!=null || prevKey!=null)){
				weixinListVo.setPreId(jsonObject.getJSONObject("createTime").getLong("$numberLong"));
			}
			//设置后一页的起始id
			if(i==docs.size()-1){
				weixinListVo.setNextId(jsonObject.getJSONObject("createTime").getLong("$numberLong"));
			}
		}
		weixinListVo.setWeiXinArticleList(weiXinArticleList);
		return weixinListVo;
	}

	@Override
	public List<WeiXinArticle> selectNewArticleInMongoByTag(Date startTime,
			int start, int pagesize) {
		if(startTime ==null ){
			return null;
		}
		
		List<Document> docs=mongoService.findNewArticle(null,null, null, startTime, start, pagesize, articleCollection);
		if(docs==null || docs.isEmpty()){
			return null;
		}
		
		List<WeiXinArticle> weiXinArticleList=Lists.newArrayList();
		//组合数据
		for(Document doc:docs){
			String jsonString = doc.toJson();
			JSONObject jsonObject= JSON.parseObject(jsonString);
			WeiXinArticle weiXinArticle = JSONObject.toJavaObject(jsonObject, WeiXinArticle.class);
			weiXinArticleList.add(weiXinArticle);
		}
		return weiXinArticleList;
	}

	@Override
	public void updateOneArticle(Integer titlehash) {
		mongoService.updateOneArticle(titlehash, articleCollection);
	}

	@Override
	public WeixinListVo selectOnePageArticleByHot(Long tagid, Long nextKey,
			Long prevKey, Integer status) {
		if(tagid ==null){
			tagid=0L;;
		}
		
//		List<Document> docs=mongoService.findArticle(null, weixin_hao, tagid, start, pagesize, articleCollection);
		//查询所有_ids并缓存
		List<String> onePageIds=Lists.newArrayList();
		
		//如果是普通查询
		if(status==null){
			if(nextKey!=null && prevKey==null){
				String cache_key=GuavaCacheServiceImpl.CACHE_KEY_PRE+tagid;
				onePageIds=guavaCacheService.getCallableCache(cache_key,nextKey,null,PAGE_SIZE);
			}else if(nextKey==null && prevKey!=null){
				onePageIds=guavaCacheService.getCallablePreCache(GuavaCacheServiceImpl.CACHE_KEY_PREV_PRE+tagid,prevKey);
			}else{
				String cache_key=GuavaCacheServiceImpl.CACHE_KEY_PRE+tagid;
				onePageIds=guavaCacheService.getCallableCache(cache_key,nextKey,null,PAGE_SIZE);
			}
		}else{
			//如果是优质文章查询
			onePageIds=guavaCacheService.getCallableCache(GuavaCacheServiceImpl.CACHE_SPECIAL_KEY_PRE+tagid,nextKey,status,PAGE_SIZE);
		}
		
		//防止空数据
		if(onePageIds==null || onePageIds.isEmpty()){
			return null;
		}
		
		//查询一页ids
//		List<String> onePageIds =guavaCacheService.getOnePageIds(allIds, page);
		//根据一页ids查询doc
		List<Document> docs = mongoService.findOnePageArticle(onePageIds, articleCollection);
		if(docs==null || docs.isEmpty()){
			return null;
		}
		
		WeixinListVo weixinListVo = this.createReturnObject(docs, nextKey, prevKey);
		return weixinListVo;
	}

	@Override
	public WeixinListVo getNearlyArticles(Long tagid, Long nextKey,
			Long prevKey, Integer status, Integer count) {
		if(tagid ==null){
			tagid=0L;;
		}
		
		//查询所有_ids并缓存
		List<String> onePageIds=Lists.newArrayList();
		
		//如果是普通查询
		String cache_key=GuavaCacheServiceImpl.CACHE_KEY_PRE+tagid;
		onePageIds=guavaCacheService.getCallableCache(cache_key,nextKey,null,count);
		
		//防止空数据
		if(onePageIds==null || onePageIds.isEmpty()){
			return null;
		}
		
		//根据一页ids查询doc
		List<Document> docs = mongoService.findOnePageArticle(onePageIds, articleCollection);
		if(docs==null || docs.isEmpty()){
			return null;
		}
		
		WeixinListVo weixinListVo = this.createReturnObject(docs, nextKey, prevKey);
		return weixinListVo;
	}

	@Override
	public WeixinListVo getGoodArticles(Long nextKey, Integer status) {
		
//		List<Document> docs=mongoService.findArticle(null, weixin_hao, tagid, start, pagesize, articleCollection);
		//查询所有_ids并缓存
		List<String> onePageIds=Lists.newArrayList();
		
		//如果是普通查询
		onePageIds=mongoService.findGoodArticle(nextKey, 25, articleCollection);
		
		//防止空数据
		if(onePageIds==null || onePageIds.isEmpty()){
			return null;
		}
		
		//查询一页ids
//		List<String> onePageIds =guavaCacheService.getOnePageIds(allIds, page);
		//根据一页ids查询doc
		List<Document> docs = mongoService.findOnePageArticle(onePageIds, articleCollection);
		if(docs==null || docs.isEmpty()){
			return null;
		}
		
		WeixinListVo weixinListVo = this.createReturnObject(docs, nextKey, null);
		return weixinListVo;
	}
	
	
}
