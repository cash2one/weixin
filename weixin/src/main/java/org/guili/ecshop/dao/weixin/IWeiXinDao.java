package org.guili.ecshop.dao.weixin;
import java.util.List;

import org.guili.ecshop.business.weixin.bean.WeiXinHao;
/**
 * 评论
 * @author guili
 */
public interface IWeiXinDao {
	
	/**
	 * 添加微信公众号
	 * @param lcProduct
	 * @return
	 * @throws Exception
	 */
	public int addweixin(WeiXinHao weiXinHao);
	
	/**
	 *更新微信信息
	 * @param lcProduct
	 * @return
	 * @throws Exception
	 */
	public int updateweixin(WeiXinHao wenti);
	
	/**
	 * 根据Id查询微信号信息
	 * @param pageParam
	 * @return
	 */
	public WeiXinHao selectWeiXinById(Long id) throws Exception;
	
	/**
	 * 根据hash查询微信号信息
	 * @param pageParam
	 * @return
	 */
	public WeiXinHao selectWeiXinByhash(Long namehash);
	
	/**
	 * 查询最新的6个微信账号
	 * @return
	 */
	public List<WeiXinHao> selectNewWeiXin();
	
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
	
}
