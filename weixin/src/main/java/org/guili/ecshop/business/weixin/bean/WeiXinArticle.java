package org.guili.ecshop.business.weixin.bean;
import java.io.Serializable;
import java.util.Date;

import org.guili.ecshop.util.RelativeDateFormat;

/**
 * 微信文章
 * @author guili
 */
public class WeiXinArticle implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 2545607221029547399L;
	private Long   id;		 		//问题id
	private Long  hao_id;			//公众号id
	private Long  tag_id;			//公众号标签id
	private String title; 			//问题名称
	private Integer titlehash;		//标题hash
	private String hao_name;		//号名称
	private String hao_name_hash;		//号名称hash
	private String weixin_hao;		//微信账号名(字母)
	private String hao_desc;		//号描述
	private String openid;			//号openid
	private String description;		//文章描述
	private String article_pre;		//文章预览
	private Integer read_times;		//阅读次数
	private Integer status;			//状态   1.正常 2，优质
	private Date createTime; 		//创建时间
	private String relativeTime; 		//文章相对时间
	private String content;			//文章内容
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHao_id() {
		return hao_id;
	}
	public void setHao_id(Long hao_id) {
		this.hao_id = hao_id;
	}
	public Long getTag_id() {
		return tag_id;
	}
	public void setTag_id(Long tag_id) {
		this.tag_id = tag_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHao_name() {
		return hao_name;
	}
	public void setHao_name(String hao_name) {
		this.hao_name = hao_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Integer getTitlehash() {
		return titlehash;
	}
	public void setTitlehash(Integer titlehash) {
		this.titlehash = titlehash;
	}
	
	
	public String getHao_desc() {
		return hao_desc;
	}
	public void setHao_desc(String hao_desc) {
		this.hao_desc = hao_desc;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getWeixin_hao() {
		return weixin_hao;
	}
	public void setWeixin_hao(String weixin_hao) {
		this.weixin_hao = weixin_hao;
	}
	
	public String getHao_name_hash() {
		return hao_name_hash;
	}
	public void setHao_name_hash(String hao_name_hash) {
		this.hao_name_hash = hao_name_hash;
	}
	/**
	 * 对象转换
	 * @return
	 */
	public WeiXinArticleVo tovo(){
		WeiXinArticleVo weiXinArticleVo=new WeiXinArticleVo();
		weiXinArticleVo.setDescription(this.description);
		weiXinArticleVo.setHao_id(this.hao_id);
		weiXinArticleVo.setHao_name(this.hao_name);
		weiXinArticleVo.setId(this.id);
		weiXinArticleVo.setTitle(this.title);
		weiXinArticleVo.setHao_desc(this.hao_desc);
		return weiXinArticleVo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRelativeTime() {
		return relativeTime;
	}
	public void setRelativeTime() {
		relativeTime=RelativeDateFormat.format(this.createTime);
	}
	public String getArticle_pre() {
		return article_pre;
	}
	public void setArticle_pre(String article_pre) {
		this.article_pre = article_pre;
	}
	public Integer getRead_times() {
		return read_times;
	}
	public void setRead_times(Integer read_times) {
		this.read_times = read_times;
	}
	
}
