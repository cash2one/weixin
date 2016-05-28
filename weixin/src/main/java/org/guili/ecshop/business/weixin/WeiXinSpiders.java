package org.guili.ecshop.business.weixin;

import java.util.EnumMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * //加载bean实例，并提供通过enum获取实体bean的方法。
 * @author Administrator
 */
public class WeiXinSpiders  implements ApplicationContextAware{

	 private static Map<WeiXinSpiderType,IWeiXinSpiderService> handlers = new EnumMap<WeiXinSpiderType, IWeiXinSpiderService>(WeiXinSpiderType.class);
	 
	 public static IWeiXinSpiderService getHandler(WeiXinSpiderType spiderType){
		 if(spiderType==null){
			 return null;
		 }
		 return handlers.get(spiderType);
	 }
	 
	 /**
	  * 初始化实体bean
	  */
	 @Override
	 public void setApplicationContext(ApplicationContext applicationContext)
	   throws BeansException {
		  Map<String, IWeiXinSpiderService> beansMap = applicationContext.getBeansOfType(IWeiXinSpiderService.class);
		  WeiXinSpiderType [] spiderTypes = null;
		  for(String beanName:beansMap.keySet()){
			  IWeiXinSpiderService handler = beansMap.get(beanName);
			   spiderTypes = handler.getSpiderType();
			   if(spiderTypes == null || spiderTypes.length == 0){
				   continue;
			   }
			   for(WeiXinSpiderType spiderType: spiderTypes){
				   handlers.put(spiderType, handler);
			   }
		 }
	}

}
