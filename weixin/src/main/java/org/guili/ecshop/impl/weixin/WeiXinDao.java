package org.guili.ecshop.impl.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.dao.weixin.IWeiXinDao;
import org.guili.ecshop.util.BasicSqlSupport;

/**
 * 微信dao
 * @author zhengdong.xiao
 *
 */
public class WeiXinDao  extends BasicSqlSupport implements IWeiXinDao {
	private static Logger logger=Logger.getLogger(WeiXinDao.class);

	@Override
	public int addweixin(WeiXinHao weiXinHao) {
		int count=0;
		try {
			count=this.session.insert("org.guili.ecshop.dao.weixin.IWeiXinDao.addweixin", weiXinHao);
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}

	@Override
	public WeiXinHao selectWeiXinById(Long id) throws Exception {
		WeiXinHao weixin=new WeiXinHao();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.weixin.IWeiXinDao.selectWeiXinById", paramMap);
		if(list!=null && list.size()>0){
			weixin=(WeiXinHao)list.get(0);
		}else{
			weixin=null;
		}
		logger.debug("success!");
		return weixin;
	}
	@Override
	public List<WeiXinHao> selectNewWeiXin(){
		List list=this.session.selectList("org.guili.ecshop.dao.weixin.IWeiXinDao.selectNewWeiXin");
		if(list!=null && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public WeiXinHao selectWeiXinByhash(Long namehash) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("namehash", namehash);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.weixin.IWeiXinDao.selectWeiXinByhash", paramMap);
		if(list!=null && list.size()>0){
			return (WeiXinHao)list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int updateweixin(WeiXinHao weixin) {
		int count=0;
		//更新状态为已批量处理
		weixin.setStatus(2);
		try {
			count=this.session.update("org.guili.ecshop.dao.weixin.IWeiXinDao.updateweixin", weixin);
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}

	@Override
	public List<String> selectAllWeiXinIds() {
		List list=this.session.selectList("org.guili.ecshop.dao.weixin.IWeiXinDao.selectAllWeiXinIds");
		if(list!=null && list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public int updateweixinStatus(Long id) {
		WeiXinHao weixinstatus=new WeiXinHao();
		weixinstatus.setId(id);
		weixinstatus.setStatus(2);
		int count=0;
		try {
			count=this.session.update("org.guili.ecshop.dao.weixin.IWeiXinDao.updateweixin", weixinstatus);
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}

}
