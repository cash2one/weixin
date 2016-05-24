package org.guili.ecshop.util;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.guili.ecshop.bean.common.DomainConstans;
import org.guili.ecshop.exception.SchedulerException;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * mongo db util
 * @author zhengdong.xiao
 *
 */
public class MongoUtils {
	
	private static final Cache<String, MongoClient> cacheClient = CacheBuilder.newBuilder().maximumSize(1).build();
	private static final Cache<String, MongoDatabase> cacheDB = CacheBuilder.newBuilder().maximumSize(1).build();
	private static final Cache<String, MongoCollection<Document>> cacheColl = CacheBuilder.newBuilder()
			.maximumSize(MongoUtils.getTotalColl().size()).build();

	public static MongoClient getMongoClient() {
		try {
			return cacheClient.get("MongoClient", new Callable<MongoClient>() {

				@Override
				public MongoClient call() throws Exception {
					return initMongoClient();
				}
			});
		} catch (ExecutionException e) {
			throw new SchedulerException(e.getCause());
		}
	}

	public static MongoDatabase getDatabase() {
		final String databaseName = DomainConstans.mongodb_databaseName;
		try {
			return cacheDB.get(databaseName, new Callable<MongoDatabase>() {

				@Override
				public MongoDatabase call() throws Exception {
					return getMongoClient().getDatabase(databaseName);
				}
			});
		} catch (ExecutionException e) {
			throw new SchedulerException(e.getCause());
		}
	}

	public static MongoCollection<Document> getCollection(final String collectionName) {
		try {
			return cacheColl.get(collectionName, new Callable<MongoCollection<Document>>() {

				@Override
				public MongoCollection<Document> call() throws Exception {
					return getDatabase().getCollection(collectionName);
				}
			});
		} catch (ExecutionException e) {
			throw new SchedulerException(e.getCause());
		}
	}

	public static ImmutableSet<String> getTotalColl() {
		String collStr = DomainConstans.mongodb_total_colls;
		String[] collArray = StringUtils.split(collStr, "|");

		Builder<String> collBuilder = ImmutableSet.<String> builder();
		for (int i = 0; i < collArray.length; i++) {
			collBuilder.add(collArray[i]);
		}
		return collBuilder.build();
	}

	public static boolean checkCollName(String collectionName) {
		ImmutableSet<String> collSet = getTotalColl();
		return collSet.contains(collectionName);
	}

	private static MongoClient initMongoClient() {

//		String host = ctxPropMap.getString("scheduler.mongodb.host");
//		int port = ctxPropMap.getIntValue("scheduler.mongodb.port");
//		String userName = ctxPropMap.getString("scheduler.mongodb.userName");
//		String database = ctxPropMap.getString("scheduler.mongodb.databaseName");
//		String password = ctxPropMap.getString("scheduler.mongodb.password");

		ServerAddress sa = new ServerAddress(DomainConstans.mongodb_host, DomainConstans.mongodb_port);
		List<MongoCredential> mongoCredentialList = Lists
				.newArrayList(MongoCredential.createCredential(DomainConstans.mongodb_userName, DomainConstans.mongodb_databaseName, DomainConstans.mongodb_password.toCharArray()));
		return new MongoClient(sa, mongoCredentialList);
//		return new MongoClient(sa);
	}
}
