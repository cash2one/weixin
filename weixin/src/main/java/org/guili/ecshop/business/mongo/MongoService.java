package org.guili.ecshop.business.mongo;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Projections;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.guili.ecshop.bean.common.DomainConstans;
import org.guili.ecshop.exception.SchedulerException;
import org.guili.ecshop.util.MongoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xerial.snappy.Snappy;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import static com.mongodb.client.model.Filters.*;

/**
 * mongo db 插入查询
 * @author zhengdong.xiao
 *
 */
@Service
public class MongoService {

	private static final Logger	log	= LoggerFactory.getLogger(MongoService.class);
    private static final String USER_ID = "userId";
    private static final String BIND_TIME = "bindTime";
   

    private static final Cache<String, BlockingQueue<byte[]>> cacheQueue = CacheBuilder.newBuilder()
            .maximumSize(MongoUtils.getTotalColl().size()).build();

    public void insertMany(final String document, final String collectionName) {
        Preconditions.checkArgument(MongoUtils.checkCollName(collectionName), "collection not exist in mongo");

        BlockingQueue<byte[]> documents = getQueue(collectionName);
        try {
            documents.put(Snappy.compress(document, Charsets.UTF_8));

            log.info("put document into BlockingQueue success. collectionName: {}, document: {}", collectionName, document);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SchedulerException("could not put document into BlockingQueue", e);
        } catch (IOException e) {
            throw new SchedulerException("snappy compress document error", e);
        }

        if (documents.size() >= DomainConstans.LOG_BATCH_SIZE) {
            final List<byte[]> selectDocs = Lists.newLinkedList();

            synchronized (this) {
                if (documents.size() >= DomainConstans.LOG_BATCH_SIZE) {
                    documents.drainTo(selectDocs);
                }
            }

            //如果数量大于100，同步插入mongodb
            if (CollectionUtils.isNotEmpty(selectDocs)) {
            	 log.info("start insert many docs. the size: {}", selectDocs.size());

                 MongoUtils.getCollection(collectionName).insertMany(Lists.transform(selectDocs, new Function<byte[], Document>() {
                     @Override
                     public Document apply(byte[] input) {
                         try {
                             return Document.parse(Snappy.uncompressString(input, Charsets.UTF_8));
                         } catch (IOException e) {
                             throw new SchedulerException("snappy uncompress document error", e);
                         }
                     }
                 }));
            }
        }
    }

    public List<Document> find(long userId, Date startTime, Date endTime, int start, int size, String collectionName) {
        Preconditions.checkArgument(MongoUtils.checkCollName(collectionName), "collection name not exist in mongo");

        final List<Document> docs = Lists.newLinkedList();
        Bson bson = null;

        if (startTime != null && endTime != null) {
            if (endTime.before(startTime)) {
                throw new SchedulerException(
                        String.format("endTime is before startTime. startTime: %s, endTime: %s", startTime, endTime));
            }

            bson = and(eq(USER_ID, userId), gte(BIND_TIME, startTime), lte(BIND_TIME, endTime));
        } else if (startTime != null) {
            bson = and(eq(USER_ID, userId), gte(BIND_TIME, startTime));
        } else if (endTime != null) {
            bson = and(eq(USER_ID, userId), lte(BIND_TIME, endTime));
        } else {
            bson = and(eq(USER_ID, userId));
        }

        FindIterable<Document> iterable = MongoUtils.getCollection(collectionName).find(bson).projection(Projections.exclude("_id")).limit(size).skip(start);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                docs.add(document);
            }
        });

        return docs;
    }
    
    //文章字段
    private static final String Article_ID = "id";
    private static final String Article_Tag_ID = "tag_id";
    private static final String Article_Titlehash = "titlehash";
    private static final String Article_Hao_name_hash = "hao_name_hash";
    private static final String Article_Weixin_hao = "weixin_hao";
    
    public List<Document> findArticle(Long id,Integer titlehash,String hao_name_hash,String weixin_hao,Long tag_id,int start, int size, String collectionName) {
        Preconditions.checkArgument(MongoUtils.checkCollName(collectionName), "collection name not exist in mongo");

        final List<Document> docs = Lists.newLinkedList();
        Bson bson = null;

        if (id != null) {
        	 bson = and(eq(Article_ID, id ));
        } else if (titlehash != null) {
        	 bson = and(eq(Article_Titlehash, titlehash ));
        } else if (!StringUtils.isEmpty(hao_name_hash)) {
        	 bson = and(eq(Article_Hao_name_hash, hao_name_hash ));
        }else if (!StringUtils.isEmpty(weixin_hao)) {
       	 bson = and(eq(Article_Weixin_hao, weixin_hao ));
        } else if (tag_id != null){
         	 bson = and(eq(Article_Tag_ID, tag_id));
        }

        FindIterable<Document> iterable = MongoUtils.getCollection(collectionName).find(bson).projection(Projections.exclude("_id")).limit(size).skip(start);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                docs.add(document);
            }
        });

        return docs;
    }
    
    /**
     * 查询文章列表
     * @param id
     * @param titlehash
     * @param hao_name_hash
     * @param weixin_hao
     * @param tag_id
     * @param start
     * @param size
     * @param collectionName
     * @return
     */
    public List<Document> findArticle(String hao_name_hash,String weixin_hao,Long tag_id,int start, int size, String collectionName) {
        Preconditions.checkArgument(MongoUtils.checkCollName(collectionName), "collection name not exist in mongo");

        final List<Document> docs = Lists.newLinkedList();
        Bson bson = null;

        //组合查询条件
        if (tag_id != null){
        	 bson = and(eq(Article_Tag_ID, tag_id));
        } else if (!StringUtils.isEmpty(hao_name_hash)) {
        	 bson = and(eq(Article_Hao_name_hash, hao_name_hash ));
        }else if (!StringUtils.isEmpty(weixin_hao)) {
       	 bson = and(eq(Article_Weixin_hao, weixin_hao ));
        }
        List<String> fieldNames=this.buildIncludeFieldNames();
        
        
        //sort desc
        Bson sortBson = null;
//        {"createTime":-1}
        sortBson=and(eq("createTime", -1 ));
        /**
         * 条件查询mongodb
         */
        FindIterable<Document> iterable = MongoUtils.getCollection(collectionName).find(bson)
        											.projection(Projections.exclude("_id"))
        											.projection(Projections.include(fieldNames))
        											.sort(sortBson).limit(size).skip(start);
        
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                docs.add(document);
            }
        });

        return docs;
    }
    
    /**
     * 查询最新文章列表
     * @param id
     * @param titlehash
     * @param hao_name_hash
     * @param weixin_hao
     * @param tag_id
     * @param start
     * @param size
     * @param collectionName
     * @return
     */
    public List<Document> findNewArticle(String hao_name_hash,String weixin_hao,Long tag_id,Date startTime,int start, int size, String collectionName) {
        Preconditions.checkArgument(MongoUtils.checkCollName(collectionName), "collection name not exist in mongo");

        final List<Document> docs = Lists.newLinkedList();
        Bson bson = null;
        bson = and(eq(status, 1 ));
        
        List<String> fieldNames=this.buildNearIncludeFieldNames();
        
        //sort desc
        Bson sortBson = null;
//        {"createTime":-1}
        sortBson=and(eq("createTime", -1 ));
        /**
         * 条件查询mongodb
         */
        FindIterable<Document> iterable = MongoUtils.getCollection(collectionName).find(bson)
        											.projection(Projections.exclude("_id"))
        											.projection(Projections.include(fieldNames))
        											.sort(sortBson).limit(size).skip(start);
        
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                docs.add(document);
            }
        });

        return docs;
    }
    
    /**
     * 构建列表查询，需要返回的字段列表
     * @return
     */
    private  List<String> buildIncludeFieldNames(){
    	List<String> fieldNames=Lists.newArrayList();
    	fieldNames.add("id");
    	fieldNames.add("title");
    	fieldNames.add("titlehash");
    	fieldNames.add("description");
    	fieldNames.add("hao_name");
    	fieldNames.add("hao_name_hash");
    	fieldNames.add("hao_desc");
    	fieldNames.add("weixin_hao");
    	fieldNames.add("createTime");
    	fieldNames.add("article_pre");
    	fieldNames.add("read_times");
    	return fieldNames;
    }
    
    /**
     * 构建列表查询，需要返回的字段列表
     * @return
     */
    private  List<String> buildNearIncludeFieldNames(){
    	List<String> fieldNames=Lists.newArrayList();
    	fieldNames.add("id");
    	fieldNames.add("title");
    	fieldNames.add("titlehash");
    	return fieldNames;
    }
    
    //文章字段
    private static final String Hao_weixin_id = "weixin_id";
    private static final String Hao_namehash = "namehash";
    private static final String Hao_tag_id = "tag_id";
    private static final String Hao_id = "id";
    private static final String status = "status";
    
    public List<Document> findHao(Long id,Long namehash,Long tag_id,String weixin_id,int start, int size, String collectionName) {
        Preconditions.checkArgument(MongoUtils.checkCollName(collectionName), "collection name not exist in mongo");

        final List<Document> docs = Lists.newLinkedList();
        Bson bson = null;

        if (id != null) {
        	 bson = and(eq(Hao_id, id ));
        } else if (namehash != null) {
        	 bson = and(eq(Hao_namehash, namehash ));
        } else if (!StringUtils.isEmpty(weixin_id)) {
        	 bson = and(eq(Hao_weixin_id, weixin_id ));
        } else if (tag_id != null){
         	 bson = and(eq(Hao_tag_id, tag_id));
        }

        FindIterable<Document> iterable = MongoUtils.getCollection(collectionName).find(bson).projection(Projections.exclude("_id")).limit(size).skip(start);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                docs.add(document);
            }
        });

        return docs;
    }
    
    public List<Document> findNewHaos(Long id,Long namehash,Long tag_id,String weixin_id,int start, int size, String collectionName) {
        Preconditions.checkArgument(MongoUtils.checkCollName(collectionName), "collection name not exist in mongo");

        final List<Document> docs = Lists.newLinkedList();
        Bson bson = null;

        /*if (id != null) {
        	 bson = and(eq(Hao_id, id ));
        } else if (namehash != null) {
        	 bson = and(eq(Hao_namehash, namehash ));
        } else if (!StringUtils.isEmpty(weixin_id)) {
        	 bson = and(eq(Hao_weixin_id, weixin_id ));
        } else if (tag_id != null){
         	 bson = and(eq(Hao_tag_id, tag_id));
        }*/
        
        bson = and(eq(status, 1 ));
        
        //sort desc
        Bson sortBson = null;
        sortBson=and(eq("createTime", -1 ));

        FindIterable<Document> iterable = MongoUtils.getCollection(collectionName)
        											.find(bson)
        											.projection(Projections.exclude("_id"))
        											.sort(sortBson)
        											.limit(size).skip(start);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                docs.add(document);
            }
        });

        return docs;
    }

    private BlockingQueue<byte[]> getQueue(final String collectionName) {
        try {
            return cacheQueue.get(collectionName, new Callable<BlockingQueue<byte[]>>() {
                @Override
                public BlockingQueue<byte[]> call() throws Exception {
                    return new LinkedBlockingQueue<byte[]>(DomainConstans.LOG_BATCH_SIZE * 2);
                }
            });
        } catch (ExecutionException e) {
            throw new SchedulerException(e.getCause());
        }
    }
}
