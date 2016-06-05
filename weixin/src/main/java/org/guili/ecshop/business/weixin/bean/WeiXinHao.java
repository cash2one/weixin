package org.guili.ecshop.business.weixin.bean;
import java.io.Serializable;
import java.util.Date;
/**
 * 理财问题
 * @author guili
 */
public class WeiXinHao implements Serializable{
	
	private static final long serialVersionUID = 254560722009547399L;
	private Long   	id;		 		//问题id
	private Long  	namehash;		//公众号名称hash
	private String 	name; 			//公众号名称
	private String 	weixin_id;		//微信id
	private String 	openid; 		//openid
	private String 	ma; 			//二维码
	private String 	touxiang; 		//头像
	private String 	description; 	//描述
	private String 	renzheng; 		//认证信息
	private Long 	tag_id; 		//标签id
	private Integer status; 		//状态  
	private Date 	createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNamehash() {
		return namehash;
	}
	public void setNamehash(Long namehash) {
		this.namehash = namehash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeixin_id() {
		return weixin_id;
	}
	public void setWeixin_id(String weixin_id) {
		this.weixin_id = weixin_id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTouxiang() {
		return touxiang;
	}
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRenzheng() {
		return renzheng;
	}
	public void setRenzheng(String renzheng) {
		this.renzheng = renzheng;
	}
	public Long getTag_id() {
		return tag_id;
	}
	public void setTag_id(Long tag_id) {
		this.tag_id = tag_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
