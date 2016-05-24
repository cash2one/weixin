package org.guili.ecshop.business.weixin.bean;
import java.io.Serializable;
import java.util.Date;
/**
 * 微信文章
 * @author guili
 */
public class WeiXinArticleVo implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 254560722102954739L;
	private Long   id;		 		//文章id
	private Long  hao_id;			//公众号id
	private String title; 			//文章标题
	private String hao_name;		//号名称
	private String hao_name_hash;	//号名称hash
	private String hao_desc;		//号名称描述
	private String weixin_hao;		//微信账号名(字母)
	private String description;		//文章描述
	private Date articleTime; 		//文章时间
	private String relativeTime;    //相对现在时间 
	private String read_times;		//文章描述
	
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
	public Date getArticleTime() {
		return articleTime;
	}
	public void setArticleTime(Date articleTime) {
		this.articleTime = articleTime;
	}
	public String getRelativeTime() {
		return relativeTime;
	}
	public void setRelativeTime(String relativeTime) {
		this.relativeTime = relativeTime;
	}
	public String getHao_desc() {
		return hao_desc;
	}
	public void setHao_desc(String hao_desc) {
		this.hao_desc = hao_desc;
	}
	public String getRead_times() {
		return read_times;
	}
	public void setRead_times(String read_times) {
		this.read_times = read_times;
	}
	public String getHao_name_hash() {
		return hao_name_hash;
	}
	public void setHao_name_hash(String hao_name_hash) {
		this.hao_name_hash = hao_name_hash;
	}
	public String getWeixin_hao() {
		return weixin_hao;
	}
	public void setWeixin_hao(String weixin_hao) {
		this.weixin_hao = weixin_hao;
	}
	
}
