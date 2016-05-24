package org.guili.ecshop.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.guili.ecshop.util.file.SFTPChannel;
import org.guili.ecshop.util.file.SFTPConstants;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;


/**
 * 文件操作工具类
 * @ClassName:   FileTools 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author:      guilige 
 * @date         2013-11-28 上午10:47:48 
 */
public class FileTools {
	
	private static final Logger LOG = Logger.getLogger(FileTools.class.getName());
	/**
	 * 读取文件
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String read(String filepath) throws FileNotFoundException, IOException {  
		String returnstr="";
        File file = new File(filepath);// 指定要读取的文件 
        // 获得该文件的缓冲输入流  
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));  
        String line = "";// 用来保存每次读取一行的内容  
        while ((line = bufferedReader.readLine()) != null) {  
        	returnstr=returnstr+line;
        }  
        bufferedReader.close();// 关闭输入流  
        return returnstr;
     }  
	
	 public static void appendToFile(String file, String conent) {  
	        BufferedWriter out = null;  
	        try {  
	            out = new BufferedWriter(new OutputStreamWriter(  
	                    new FileOutputStream(file, true)));  
	            out.write(conent);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                out.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }
	 
	//写文件
	public static void write(String path, String content) {
		      String s1 = new String();
		      try {
		       File f = new File(path);
		       if (f.exists()) {
		        System.out.println("文件存在");
		       } else {
		        System.out.println("文件不存在，正在创建...");
		        if (f.createNewFile()) {
		         System.out.println("文件创建成功！");
		        } else {
		         System.out.println("文件创建失败！");
		        }
		       }
		       s1+=content;
		       BufferedWriter output = new BufferedWriter(new FileWriter(f));
		       output.write(s1);
		       output.close();
		      } catch (Exception e) {
		       e.printStackTrace();
		      }
		}
	
	/**
	 * 传送文件到远程服务器
	 * @param host  远程服务器ip
	 * @param port	远程服务器port
	 * @param username	远程服务器用户名	
	 * @param pwd		远程服务器密码
	 * @param filename	传送文件名
	 * @param filePath	传送文件路径
	 */
	public static void writeFileToRemote(String host,String username,String password,String filename,String sourceFilePath,String desFilePath){
		//判断服务器是否允许ftp
		 Map<String, String> sftpDetails = new HashMap<String, String>();
	    // 设置主机ip，端口，用户名，密码
	    sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, host);
	    sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME,username);
	    sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, password);
	    sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
	    
	    String src = sourceFilePath+filename; // 本地文件名
	    String dst = desFilePath+filename; // 目标文件名
	    SFTPChannel channel =new SFTPChannel();
	    ChannelSftp chSftp;
	    //远程写文件
		try {
			chSftp = channel.getChannel(sftpDetails, 6000);
			chSftp.put(src, dst, ChannelSftp.OVERWRITE);
		    chSftp.quit();
			channel.closeChannel();
		} catch (JSchException e) {
			LOG.error("writeFileToRemote JSchException!");
		}catch (SftpException e) {
			LOG.error("writeFileToRemote SftpException!");
		} catch (Exception e) {
			LOG.error("writeFileTo Exception!");
		} finally{
			if(channel!=null){
				try {
					channel.closeChannel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		 String filename="E:/newProject/tmallbacktest.txt";
//		 try {
//				File file=new File(filename);
//				if(!file.exists()){
//					file.createNewFile();
//				}
//				FileTools.appendToFile(filename, "test");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		 FileTools.writeFileToRemote("112.124.43.134", "root", "OKMnji1127", "test.txt", "E:\\weixin\\", "/usr/local/tomcat-maogou/temp/");
	}
}
