package org.guili.ecshop.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.guili.ecshop.bean.common.DomainConstans;
import org.guili.ecshop.business.mongo.MongoService;
import org.guili.ecshop.controller.common.BaseProfileController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import org.bson.Document;
/**
 * 首页跳转和首页需要信息的跳转
 * @ClassName:   MainController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author:      guilige 
 * @date         2013-11-19 下午7:09:51 
 *
 */
@Controller
public class TestController extends BaseProfileController {
	private Logger logger=Logger.getLogger(TestController.class);
	
	
	private static final int PAGE_START = 0;
    private static final int PAGE_SIZE = 5;

    @Resource(name="mongoService")
    private MongoService mongoService;
    
	private String collectionName=DomainConstans.mongodb_logininfo_collectionName;

//    @RequestMapping(value = "/find.htm", method = RequestMethod.POST)
//    @ResponseBody
//    public void find(Long userId, Date startTime, Date endTime, Integer start, Integer size, String collectionName) {
//        Preconditions.checkNotNull(userId, "userId is required");
//
//        int startVal = start != null ? start.intValue() : PAGE_START;
//        int sizeVal = size != null ? size.intValue() : PAGE_SIZE;
//        List<Document> documents=mongoService.find(userId.longValue(), startTime, endTime, startVal, sizeVal, collectionName);
//
//        return ;
//    }
    
    @RequestMapping(value = "/testAdd.htm", method = RequestMethod.POST)
    @ResponseBody
    public void addMongo() {
    	for (int i = 0; i < 20000; i++) {
          JSONObject json = new JSONObject();
          json.put("ip", "192.168.32.120");
          json.put("loginTime", "2016-03-14 15:30:13");
          json.put("loginType", "LOGIN");
          json.put("userId", 12484583);
          json.put("username", "13800013801");
          String jsonString = JSON.toJSONString(json);
          mongoService.insertMany(jsonString, collectionName);
      }
        return ;
    }
	
}
