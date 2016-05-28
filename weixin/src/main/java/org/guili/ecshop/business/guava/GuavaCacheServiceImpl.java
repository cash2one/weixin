package org.guili.ecshop.business.guava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.guili.ecshop.bean.common.DomainConstans;
import org.guili.ecshop.business.mongo.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

/**
 * 
 * 功能描述： guava 缓存实现
 * @author 作者 zhengdong.xiao@vip.com
 * @created 2016-3-2 下午6:01:29
 * @version 1.0.0
 * @date 2016-3-2 下午6:01:29
 */
public class GuavaCacheServiceImpl implements GuavaCacheService {


	private static Cache<String, List<String>> callableBuilder = null;
	private MongoService mongoService;
	public static final String CACHE_KEY_PRE="CACHE_KEY_PRE";
	public static final String CACHE_KEY_PREV_PRE="CACHE_KEY_PREV_PRE";
	private String articleCollection=DomainConstans.mongodb_article_collectionName;
	private final int PAGE_SIZE=25;
	
	// 二级缓存开关
	private static final Logger logger = LoggerFactory.getLogger(GuavaCacheServiceImpl.class);
	{
		try {
			// 初始化缓存
			callableBuilder = callableBuilder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Cache<String, List<String>> callableBuilder() throws Exception {
		Cache<String, List<String>> cache = CacheBuilder.newBuilder()
											.maximumSize(30000)
				.expireAfterWrite(10, TimeUnit.MINUTES)
											.build();
		return cache;
	}

	@Override
	public List<String> getCallableCache(final String cacheKey,final Long startIndex) {

		try {
			// 从mongo取数据，Callable只有在缓存值不存在时，才会调用
			List<String> ids = callableBuilder.get(cacheKey+startIndex, new Callable<List<String>>() {

				// 如果本地缓存取不到，去redis取缓存
				public  List<String> call() throws Exception {
					try {
						long start = System.currentTimeMillis();
						List<String> cached= Lists.newArrayList();
						//查询标签id
						long tag_id=Long.parseLong(cacheKey.replace(CACHE_KEY_PRE, ""));
//						List<String> cached = redisCacheService.get(RedisKeyPrefix.App, clientId, AppClient.class);
						cached=mongoService.findOneTagArticle(null, null, tag_id,startIndex, PAGE_SIZE, articleCollection);
						//从mongo取数据
						logger.info("getCallableCacheXgetAppClient from cache by client_id,client_id={} ,use={}ms",
								cacheKey, System.currentTimeMillis() - start);
						return cached;
					} catch (Exception e) {
						logger.error("getCallableCacheXgetAppClientXerror happened when get app client from redis cache,client_id");
						return null;
					}
				}
			});
			// 从缓存返回ids
			return ids;
		}catch (Exception e) {
			// 异常抛错误码
			logger.error("GuavaCacheServiceImplXgetCallableCacheXERROR happened when get app client from cache,client_id");
			return null;
		}
	}

	@Override
	public List<String> getCallablePreCache(final String cacheKey,final  Long prevIndex) {
		try {
			// 从mongo取数据，Callable只有在缓存值不存在时，才会调用
			List<String> ids = callableBuilder.get(cacheKey+prevIndex, new Callable<List<String>>() {

				// 如果本地缓存取不到，去redis取缓存
				public  List<String> call() throws Exception {
					try {
						long start = System.currentTimeMillis();
						List<String> cached= Lists.newArrayList();
						//查询标签id
						long tag_id=Long.parseLong(cacheKey.replace(CACHE_KEY_PREV_PRE, ""));
//						List<String> cached = redisCacheService.get(RedisKeyPrefix.App, clientId, AppClient.class);
						cached=mongoService.findPrevPageArticle(null, null, tag_id,prevIndex, PAGE_SIZE, articleCollection);
						//从mongo取数据
						logger.info("getCallableCacheXgetAppClient from cache by client_id,client_id={} ,use={}ms",
								cacheKey, System.currentTimeMillis() - start);
						return cached;
					} catch (Exception e) {
						logger.error("getCallableCacheXgetAppClientXerror happened when get app client from redis cache,client_id");
						return null;
					}
				}
			});
			// 从缓存返回ids
			return ids;
		}catch (Exception e) {
			// 异常抛错误码
			logger.error("GuavaCacheServiceImplXgetCallableCacheXERROR happened when get app client from cache,client_id");
			return null;
		}
	}
	
	public void setMongoService(MongoService mongoService) {
		this.mongoService = mongoService;
	}

}
