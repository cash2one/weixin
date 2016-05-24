package org.guili.ecshop.business.weixin;
import java.util.List;

import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeiXinHao;
import org.guili.ecshop.business.weixin.bean.WeiXinHaoVO;

/**
 * 问题服务接口
 * @author Administrator
 *
 */
public interface IWeiXinService {
	/**
	 * 添加问题
	 * @param lcProduct
	 * @return
	 * @throws Exception
	 */
	public int addweixin(WeiXinHao wenti);
	
	/**
	 * 添加问题
	 * @param lcProduct
	 * @return
	 * @throws Exception
	 */
	public int updateweixin(WeiXinHao wenti);
	/**
	
	/**
	 * 根据Id查询问题信息
	 * @param pageParam
	 * @return
	 */
	public WeiXinHao selectWeiXinById(Long id) throws Exception;
	
	/**
	 * 根据Ids查询问题信息
	 * @param pageParam
	 * @return
	 */
	public List<WeiXinHao> selectWeiXinByIds(String ids) throws Exception;
	
	public WeiXinHao selectWeiXinByhash(Long namehash);
	
	/**
	 * 批量插入微信公众账号
	 * @param weiXinHaoList
	 * @return
	 */
	public int batAddWeiXin(List<WeiXinHaoVO> weiXinHaoList) ;
	
	public List<WeiXinHao> selectNewWeiXin();
	
	public List<WeiXinHao> selectNewHaosInMongo(int size);
	
	/**
	 * 查询最新的6个微信账号
	 * @return
	 */
	public List<String> selectAllWeiXinIds();
	
	/**
	 * 批量执行完成后更新状态
	 * @param weixin
	 * @return
	 */
	public int updateweixinStatus(Long id);
	
	/**mongodb操作**/
	/**
	 * 从mongodb查询文章根据hash值
	 * @param hash
	 * @return
	 */
	public boolean selectHaoInMongoByHash(Long namehash);
	
	/**
	 * 插入mongodb
	 * @param weiXinArticle
	 * @return
	 */
	public int addHaoInMongo(WeiXinHao wenti);
	
	/**
	 * 查询微信号信息
	 * @param weixinHao
	 * @return
	 */
	public WeiXinHao selectHaoInMongoByHao(String weixinHao);
}
