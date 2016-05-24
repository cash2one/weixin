package org.guili.ecshop.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.guili.ecshop.util.FileTools;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

/**
 * 代理检查工具
 * @author zhengdong.xiao
 *
 */
public class DailiUtils {
	
	public static List<String> available_Ips=new ArrayList<String>();
	public static   String[] all_ips=new String[1000];
	
	public static Map<String, String> cookies=new HashMap<String, String>();
	
	static{
		all_ips=new String[]{
				"﻿1.193.162.91:8000",
				"1.193.163.32:8000",
				"1.198.51.23:9797",
				"1.209.188.179:8080",
				"1.234.45.50:3128",
				"1.255.53.81:80",
				"5.53.16.183:8080",
				"5.154.116.178:8088",
				"5.196.208.4:3128",
				"27.115.75.114:8080",
				"27.254.47.203:80",
				"31.173.74.73:8080",
				"36.7.108.56:8000",
				"36.250.69.4:80",
//				"41.72.105.38:3128",
//				"41.74.78.202:3128",
//				"41.87.81.138:8080",
//				"41.161.86.43:3128",
//				"41.188.49.126:3128",
//				"41.207.49.136:8080",
//				"42.96.162.252:3128",
//				"43.243.112.78:3128",
//				"43.243.112.87:3128",
//				"45.65.11.53:3128",
//				"46.41.130.135:3128",
//				"46.146.208.173:8081",
//				"46.209.236.138:8080",
//				"46.225.239.178:8080",
//				"47.88.0.146:3128",
//				"47.88.14.58:3128",
//				"47.88.139.96:3128",
//				"49.1.244.139:3128",
//				"58.18.50.10:3128",
//				"58.20.248.69:8000",
//				"58.22.191.243:80",
//				"58.47.191.140:3128",
//				"58.67.159.50:80",
//				"58.96.181.6:3128",
//				"58.96.181.245:3128",
//				"58.133.61.3:3128",
//				"58.243.0.162:9999",
//				"58.247.30.222:8080",
//				"58.248.137.228:80",
//				"58.253.238.242:80",
//				"58.253.238.243:80",
//				"59.39.88.190:8080",
//				"60.11.11.163:3128",
//				"60.13.74.184:80",
//				"60.13.74.184:81",
//				"60.15.41.214:3128",
//				"60.15.55.228:3128",
//				"60.29.248.142:8080",
//				"60.191.130.36:3128",
//				"60.191.153.12:3128",
//				"60.191.153.75:3128",
//				"60.191.154.246:3128",
//				"60.191.157.155:3128",
//				"60.191.158.211:3128",
//				"60.191.159.85:3128",
//				"60.191.160.20:3128",
//				"60.191.161.28:3128",
//				"60.191.161.244:3128",
//				"60.191.163.235:3128",
//				"60.191.164.22:3128",
//				"60.191.164.59:3128",
//				"60.191.164.83:3128",
//				"60.191.165.69:3128",
//				"60.191.165.100:3128",
//				"60.191.166.130:3128",
//				"60.191.167.11:3128",
//				"60.191.167.93:3128",
//				"60.191.170.52:3128",
//				"60.191.170.122:3128",
//				"60.191.174.227:3128",
//				"60.191.175.54:3128",
//				"60.191.179.53:3128",
//				"60.191.180.38:3128",
//				"60.191.190.174:3128",
//				"60.194.100.51:80",
//				"60.195.250.55:80",
//				"60.219.24.111:3128",
//				"60.219.24.112:3128",
//				"60.219.24.125:3128",
//				"61.50.101.146:80",
//				"61.53.65.52:3128",
//				"61.75.2.124:3128",
//				"61.134.25.106:3128",
//				"61.134.34.148:3128",
//				"61.150.89.67:3128",
//				"61.153.198.178:3128",
//				"61.153.201.187:3128",
//				"61.163.45.240:3128",
//				"61.163.179.124:9797",
//				"61.166.56.187:3128",
//				"61.174.10.22:8080",
//				"61.175.220.4:3128",
//				"61.179.110.8:8081",
//				"61.184.199.203:3128",
//				"64.62.233.67:80",
//				"64.132.113.118:80",
//				"75.151.213.85:8080",
//				"79.120.72.222:3128",
//				"80.82.69.72:3128",
//				"80.87.33.134:8080",
//				"80.242.219.50:3128",
//				"80.251.9.42:8080",
//				"82.99.193.85:8080",
//				"82.99.197.1:8080",
//				"82.192.30.238:8080",
//				"85.143.24.70:80",
//				"85.194.75.18:8080",
//				"86.100.118.44:81",
//				"87.98.163.66:80",
//				"91.238.29.192:9999",
//				"93.51.247.104:80",
//				"93.63.142.144:80",
//				"93.158.193.87:80",
//				"94.23.196.148:3128",
//				"94.100.61.2:8080",
//				"97.73.31.142:87",
//				"101.26.38.162:80",
//				"101.26.38.162:82",
//				"101.66.253.22:8080",
//				"101.200.234.114:8080",
//				"101.226.12.223:80",
//				"101.226.249.237:80",
//				"101.231.46.34:8000",
//				"103.1.48.76:8088",
//				"103.15.122.218:80",
//				"103.247.162.218:3128",
//				"104.238.83.28:443",
//				"106.37.177.251:3128",
//				"106.56.225.200:3128",
//				"106.186.22.65:8888",
//				"109.196.127.35:8888",
//				"109.239.12.10:8000",
//				"110.17.172.150:3128",
//				"110.18.241.9:3128",
//				"110.52.232.56:80",
//				"110.52.232.76:80",
//				"110.74.195.41:8080",
//				"110.232.76.150:18080",
//				"111.1.89.254:80",
//				"111.2.196.130:80",
//				"111.13.12.216:80",
//				"111.47.13.4:80",
//				"111.47.171.41:80",
//				"111.56.13.150:80",
//				"111.200.230.232:80",
//				"111.200.230.233:80",

				};
		
		//SNUID,SUID,SUV
		cookies.put("SNUID", "");
		cookies.put("SUID", "");
		cookies.put("SUV", "");
//		cookies=new String[]{
//				"72940BFC888C962B5259520789E0D438,FB1D83752708930A0000000055E4E2EA,007A78B175831DFB55E4E426AD0C6158",
//				"628719EF999C87397B283D9F9A5A7134,FB1D83752708930A0000000055E4E4AB,0056783975831DFB55E4E4ABCF4A7380",
//				"638A14ED979D86347E12CEC998BD964B,FB1D83752708930A0000000055E4E4E7,009C7E8F75831DFB55E4E4E8FBB48320",
//				"FE19877004001AA6ED9FED1D0510956B,FB1D83752708930A0000000055E4E51F,00A7150A75831DFB55E4E51F6D023801",
//				"66831DE89E98833D70ABFF7E9ECE6983,FB1D83752708930A0000000055E4E56D,00517C4F75831DFB55E4E56E85AA4113"};
		
	}
	public void validateIps(){
		//创建wc
			final WebClient wc = new WebClient(BrowserVersion.FIREFOX_24);
	        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
	        wc.getOptions().setCssEnabled(false); //禁用css支持  
	        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
	        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
	        wc.getOptions().setTimeout(2000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
	        wc.setAjaxController(new NicelyResynchronizingAjaxController());
	        for(String ip_port:all_ips){
	        	if(StringUtils.isEmpty(ip_port)){
	        		break;
	        	}
	        	System.out.println(ip_port);
	        	String ip=ip_port.split(":")[0];
	        	String port=ip_port.split(":")[1];
	        	ProxyConfig proxyConfig=new ProxyConfig(ip, Integer.parseInt(port));
		        wc.getOptions().setProxyConfig(proxyConfig);
				//所有详细理财产品详细页
		        List<String> urllist=new ArrayList<String>();
				String pageXml="";
				WebRequest request;
				try {
					request = new WebRequest(new URL("https://www.baidu.com/"), HttpMethod.GET);
					request.setCharset("utf-8"); 
					long start=System.currentTimeMillis();
					HtmlPage page = (HtmlPage)wc.getPage(request);
			        if(!page.asXml().isEmpty() && page.asXml().length()>1000){
			        	System.out.println("花费时间:"+(System.currentTimeMillis()-start)+"ms");
			        	if((System.currentTimeMillis()-start)/1000<5){
			        		
			        		available_Ips.add(ip_port);
			        		System.out.println("\""+ip_port+"\",");
			        		FileTools.appendToFile("E:/proxy/success.txt", "\""+ip_port+"\",");
			        	}
			        }
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (FailingHttpStatusCodeException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	}
	
	public static Map<String, String> initCookies(){
		//创建wc
		final WebClient wc = new WebClient(BrowserVersion.FIREFOX_24);
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
        wc.getOptions().setCssEnabled(false); //禁用css支持  
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setTimeout(2000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        WebRequest request;
		try {
			request = new WebRequest(new URL("http://weixin.sogou.com/gzh?openid=oIWsFtxiqAdkQPc0LRTrOJu1MY5w"), HttpMethod.GET);
			request.setCharset("utf-8"); 
	        HtmlPage page = (HtmlPage)wc.getPage(request);
	        CookieManager cm= wc.getCookieManager();
	        Set<Cookie> cookies=cm.getCookies();
	        for(Cookie ck:cookies){
//	        	System.out.println(ck.getName()+":"+ck.getValue());
	        	if(ck.getName().equals("SNUID")){
	        		DailiUtils.cookies.put("SNUID", ck.getValue());
	        	}
	        	if(ck.getName().equals("SUID")){
	        		DailiUtils.cookies.put("SUID", ck.getValue());
	        	}
	        	if(ck.getName().equals("SUV")){
	        		DailiUtils.cookies.put("SUV", ck.getValue());
	        	}
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void getips(String sourceFile,String tofile){
        File file = new File(sourceFile);// 指定要读取的文件 
        // 获得该文件的缓冲输入流  
        BufferedReader bufferedReader=null;
        BufferedWriter out = null;  
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tofile, true)));  
	           
			 String line = "";// 用来保存每次读取一行的内容  
	        while ((line = bufferedReader.readLine()) != null) {  
	        	if(line.contains("@")){
	        		String ip=line.substring(0, line.indexOf("@"));
	        		out.write("\""+ip+"\",");
	 	            out.newLine();
	        	}
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			 try {  
	                out.close(); 
	                bufferedReader.close();
	            } catch (IOException e) {  
	                e.printStackTrace();  
	        }  
		}
	} 
	
	public  Map<String, String> testdaili(){
		//创建wc
//		final WebClient wc = new WebClient(BrowserVersion.FIREFOX_24);
		WebClient wc = new WebClient(BrowserVersion.FIREFOX_24,"27.115.75.114",8080);
		
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
        wc.getOptions().setCssEnabled(false); //禁用css支持  
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setTimeout(2000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
//        String ip_port="202.194.101.150:80";
//		String ip=ip_port.split(":")[0];
//    	String port=ip_port.split(":")[1];
//    	ProxyConfig proxyConfig=new ProxyConfig(ip, Integer.parseInt(port));
//        wc.getOptions().setProxyConfig(proxyConfig);
        WebRequest request;
		try {
			request = new WebRequest(new URL("http://www.licaime.com/question-2194-2-1.htm"), HttpMethod.GET);
			request.setCharset("utf-8"); 
	        HtmlPage page = (HtmlPage)wc.getPage(request);
	        CookieManager cm= wc.getCookieManager();
	        Set<Cookie> cookies=cm.getCookies();
	        for(Cookie ck:cookies){
	        	System.out.println(ck.getName()+":"+ck.getValue());
	        }
	        System.out.println(page.asText());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getRandomIp(){
		Random random=new Random();
		return DailiUtils.all_ips[random.nextInt(DailiUtils.all_ips.length)];
	}
	
//	public String getRandomCookie(){
//		Random random=new Random();
//		String cookie=DailiUtils.cookies[random.nextInt(DailiUtils.cookies.length)];
//		System.out.println(cookie);
//		return cookie;
//	}
	
	public  Map<String, String> testdaili1(){
		//创建wc
//		final WebClient wc = new WebClient(BrowserVersion.FIREFOX_24);
		WebClient wc = new WebClient(BrowserVersion.FIREFOX_24,"27.115.75.114",8080);
		
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
        wc.getOptions().setCssEnabled(false); //禁用css支持  
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setTimeout(5000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        // 等待JS驱动dom完成获得还原后的网页
        wc.waitForBackgroundJavaScript(10000);
        WebRequest request;
		try {
			request = new WebRequest(new URL("http://www.dianping.com/shop/8975141/wedding/cases/tag-0"), HttpMethod.GET);
			request.setAdditionalHeader("Referer", "http://www.dianping.com");//设置请求报文头里的refer字段
	        ////设置请求报文头里的User-Agent字段
	        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
	        //wc.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
	        //wc.addRequestHeader和request.setAdditionalHeader功能应该是一样的。选择一个即可。
			request.setCharset("utf-8"); 
	        HtmlPage page = (HtmlPage)wc.getPage(request);
	        CookieManager cm= wc.getCookieManager();
	        Set<Cookie> cookies=cm.getCookies();
	        for(Cookie ck:cookies){
	        	System.out.println(ck.getName()+":"+ck.getValue());
	        }
//	        System.out.println(page.asXml());
	        FileTools.write("E:/spiderhunsha/hunshatestdetail1.txt", page.asXml());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		DailiUtils dailiUtils=new DailiUtils();
//		dailiUtils.validateIps();
//		dailiUtils.getCookies();
//		DailiUtils.getips("E:/proxy/test.txt", "E:/proxy/temp.txt");
//		dailiUtils.getRandomIp();
//		dailiUtils.getRandomCookie();
//		dailiUtils.initCookies();
		dailiUtils.testdaili1();
	}

}
