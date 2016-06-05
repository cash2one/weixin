package org.guili.ecshop.business.weixin;
import java.util.Date;
import java.util.List;
import org.guili.ecshop.business.weixin.bean.WeiXinArticle;
import org.guili.ecshop.business.weixin.bean.WeixinListVo;

/**
 * 微信文章服务
 * @author Administrator
 *
 */
public interface IWeiXinArticleService {
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
	public WeiXinArticle selectWeiXinArticleByhash(Integer hash);
	
	/**
	 * 批量插入微信文章
	 * @param weiXinArticleList
	 * @return
	 */
	public int batAddWeiXinArticle(List<WeiXinArticle> weiXinArticleList) ;
	
	
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
	
	/**
	 * 根据微信公众号标签id，分页查询微信文章信息 in mongodb
	 * @param pageParam
	 * @return
	 */
	public List<WeiXinArticle> selectPageArticleInMongoByTag(Long tagid,String weixin_hao,
			int start, int pagesize);
	
	/**mongodb操作**/
	/**
	 * 从mongodb查询文章根据hash值
	 * @param hash
	 * @return
	 */
	public boolean selectArticleInMongoByHash(Integer hash);
	
	/**
	 * 插入mongodb
	 * @param weiXinArticle
	 * @return
	 */
	public int addArticleInMongo(WeiXinArticle weiXinArticle);
	
	/**
	 * 查询最新的文章
	 * @param startTime
	 * @param start
	 * @param pagesize
	 * @return
	 */
	public List<WeiXinArticle> selectNewArticleInMongoByTag(Date startTime,
			int start, int pagesize);
	
	/**
	 * 分页查询标签下数据
	 * @param tagid
	 * @param nextKey
	 * @param prevKey
	 * @return
	 */
	public WeixinListVo selectOnePageArticleInMongoByTag(Long tagid,
			Long nextKey,Long  prevKey) ;
	
	/**
	 * 分页查询单个微信号
	 * @param weixin_hao
	 * @param nextKey
	 * @param prevKey
	 * @return
	 */
	public WeixinListVo selectOneHaoPageArticleInMongoByTag(String weixin_hao,
			Long nextKey,Long  prevKey);
	
}
