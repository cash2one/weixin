package org.guili.ecshop.business.weixin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.guili.ecshop.bean.common.DomainConstans;
import org.guili.ecshop.business.mongo.MongoService;
import org.guili.ecshop.business.weixin.IWeiXinService;
import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.business.weixin.bean.WeiXinHaoVO;
import org.guili.ecshop.dao.weixin.IWeiXinDao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**
 * 微信服务类
 * @author zhengdong.xiao
 *
 */

public class WeiXinService implements IWeiXinService {
	private static Logger logger=Logger.getLogger(WeiXinService.class);
	private IWeiXinDao weiXinDao;
	
    private MongoService mongoService;
    
	private String weixinhaoCollection=DomainConstans.mongodb_weixinhao_collectionName;
	
	@Override
	public int addweixin(WeiXinHao weixin) {
		if(weixin.getNamehash() !=null && weixin.getNamehash()>=0){
			return 0;
		}
		//防止重复插入。
		 if(selectWeiXinByhash(weixin.getNamehash())!=null){
			 return 0;
		 }
		 System.out.println("add..."+weixin.getName());
		return weiXinDao.addweixin(weixin);
	}

	@Override
	public int updateweixin(WeiXinHao weixin) {
		if(weixin==null){
			return 0;
		}
		 System.out.println("update..."+weixin.getName());
		return weiXinDao.updateweixin(weixin);
	}
	@Override
	public WeiXinHao selectWeiXinById(Long id) throws Exception {
		if(id !=null && id>=0){
			return weiXinDao.selectWeiXinById(id);
		}
		return null;
	}
	
	

	@Override
	public WeiXinHao selectWeiXinByhash(Long namehash) {
		if(namehash !=null){
			return weiXinDao.selectWeiXinByhash(namehash);
		}
		return null;
	}
	
//	@Override
//	public int batAddWeiXin(List<WeiXinHaoVO> weiXinHaoList) {
//		if(weiXinHaoList==null || weiXinHaoList.isEmpty()){
//			return 0;
//		}
//		for(WeiXinHaoVO weixin:weiXinHaoList){
//			if(weixin==null){
//				continue;
//			}
//			if(weixin.getWeiXinHao().getNamehash() ==null){
//				continue;
//			}
//			//防止重复插入。
//			 if(selectWeiXinByhash(weixin.getWeiXinHao().getNamehash())!=null){
//				 System.out.println("exists..."+weixin.getWeiXinHao().getName());
//				 continue;
//			 }else{
//				 System.out.println("add..."+weixin.getWeiXinHao().getName());
//				 weiXinDao.addweixin(weixin.getWeiXinHao());
//			 }
//		}
//		return 0;
//	}
	
	@Override
	public int batAddWeiXin(List<WeiXinHaoVO> weiXinHaoList) {
		if(weiXinHaoList==null || weiXinHaoList.isEmpty()){
			return 0;
		}
		for(WeiXinHaoVO weixin:weiXinHaoList){
			if(weixin==null){
				continue;
			}
			if(weixin.getWeiXinHao().getNamehash() ==null){
				continue;
			}
			//防止重复插入。
			 if(this.selectHaoInMongoByHash(weixin.getWeiXinHao().getNamehash())){
				 System.out.println("exists..."+weixin.getWeiXinHao().getName());
				 continue;
			 }else{
				 System.out.println("add..."+weixin.getWeiXinHao().getName());
//				 weiXinDao.addweixin(weixin.getWeiXinHao());
				 this.addHaoInMongo(weixin.getWeiXinHao());
			 }
		}
		return 0;
	}

	public IWeiXinDao getWeiXinDao() {
		return weiXinDao;
	}

	public void setWeiXinDao(IWeiXinDao weiXinDao) {
		this.weiXinDao = weiXinDao;
	}

	@Override
	public List<WeiXinHao> selectWeiXinByIds(String ids) throws Exception {
		
		if(StringUtils.isEmpty(ids)){
			return null;
		}
		List<WeiXinHao> weixinhaoList=new ArrayList<WeiXinHao>();
		String[] weixinids=ids.split(",");
		for(String weixinid:weixinids){
			WeiXinHao weiXinHao=weiXinDao.selectWeiXinById(Long.parseLong(weixinid));
			weixinhaoList.add(weiXinHao);
		}
		return weixinhaoList;
	}

	@Override
	public List<WeiXinHao> selectNewWeiXin() {
		return weiXinDao.selectNewWeiXin();
	}

	@Override
	public List<String> selectAllWeiXinIds() {
		return weiXinDao.selectAllWeiXinIds();
	}

	@Override
	public int updateweixinStatus(Long id) {
		return weiXinDao.updateweixinStatus(id);
	}

	private static final boolean FIND=true;
	private static final boolean NOT_FIND=false;
	
	@Override
	public boolean selectHaoInMongoByHash(Long namehash) {
		List<Document> docs=mongoService.findHao(null, namehash, null, null, 0, 1, weixinhaoCollection);
		System.out.println("namehash:-----"+namehash);
		if(docs==null || docs.isEmpty()){
			return NOT_FIND;
		}else{
			return FIND;
		}
	}
	
	@Override
	public WeiXinHao selectHaoInMongoByHao(String weixinHao) {
//		List<Document> docs=mongoService.findHao(null, namehash, null, null, 0, 1, weixinhaoCollection);
		List<Document> docs=mongoService.findHao(null, null, null, weixinHao, 0, 1, weixinhaoCollection);
		if(docs==null || docs.isEmpty()){
			return null;
		}
		String jsonString = docs.get(0).toJson();
		
		//json转对象
		JSONObject jsonObject= JSON.parseObject(jsonString);
		WeiXinHao weiXinHao = JSONObject.toJavaObject(jsonObject, WeiXinHao.class);
		return weiXinHao;
	}
	
	@Override
	public List<WeiXinHao> selectNewHaosInMongo(int size) {
//		List<Document> docs=mongoService.findHao(null, namehash, null, null, 0, 1, weixinhaoCollection);
		List<Document> docs=mongoService.findNewHaos(null, null, null, null, 0, size, weixinhaoCollection);
		if(docs==null || docs.isEmpty()){
			return null;
		}
		List<WeiXinHao> weiXinHaoList=Lists.newArrayList();
		//组合数据
		for(Document doc:docs){
			String jsonString = doc.toJson();
			JSONObject jsonObject= JSON.parseObject(jsonString);
			WeiXinHao weiXinHao = JSONObject.toJavaObject(jsonObject, WeiXinHao.class);
			weiXinHaoList.add(weiXinHao);
		}
		return weiXinHaoList;
	}
	
	@Override
	public int addHaoInMongo(WeiXinHao weiXinHao) {
		//对象转json
		String jsonString=JSON.toJSONString(weiXinHao);
		//存mongo
		mongoService.insertMany(jsonString, weixinhaoCollection);
		return 1;
	}

	public MongoService getMongoService() {
		return mongoService;
	}

	public void setMongoService(MongoService mongoService) {
		this.mongoService = mongoService;
	}

}
