package org.guili.ecshop.business.guava;

import java.util.List;


/**
 * 
 * 功能描述： guava cache service
 * @author 作者 zhengdong.xiao@vip.com
 * @created 2016-3-2 下午5:52:29
 * @version 1.0.0
 * @date 2016-3-2 下午5:52:29
 */
public interface GuavaCacheService {
  
	/**
	 * 功能描述：对需要延迟处理的可以采用这个机制
	 */
	// public Cache<String, AppClient> callableBuilder() throws Exception;
	
	/**
	 * 功能描述：获取缓存的key，Callable只有在缓存值不存在时，调用
	 */
	public List<String> getCallableCache(final String cacheKey,final Long nextIndex) ;
	
	public List<String> getCallablePreCache(final String cacheKey,final Long prevIndex) ;
	

}
