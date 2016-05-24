package org.guili.ecshop.dao.weixin;
import java.util.List;

import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
/**
 * 微信文章dao
 * @author guili
 */
public interface IWeiXinArticleDao {
	
	/**
	 * 添加微信文章
	 * @param lcProduct
	 * @return
	 * @throws Exception
	 */
	public int addweixinArticle(WeiXinArticle weiXinArticle);
	
	/**
	 * 根据Id查询微信文章信息
	 * @param pageParam
	 * @return
	 */
	public WeiXinArticle selectWeiXinArticleById(Long id) throws Exception;
	
	/**
	 * 根据hash查询微信文章信息
	 * @param pageParam
	 * @return
	 */
	public WeiXinArticle selectWeiXinArticleByhash(Long hash);
	
	/**
	 * 根据微信公众号id，分页查询微信文章信息
	 * @param pageParam
	 * @return
	 */
	public List<WeiXinArticle> selectPageArticleByHao(Long haoid,int start,int pagesize);
	
	/**
	 * 根据微信公众号标签id，分页查询微信文章信息
	 * @param pageParam
	 * @return
	 */
	public List<WeiXinArticle> selectPageArticleByTag(Long tagid,int start,int pagesize);
	
}
