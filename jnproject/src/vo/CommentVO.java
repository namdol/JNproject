package vo;

import java.util.Date;

public class CommentVO {
	private int cmt_number;
	private String cmt_name;
	private String cmt_content;
	private Date cmt_date;
	private int article_number;
	private String cmt_password;
	private int re_ref;
	private int re_seq;
	
	public int getCmt_number() {
		return cmt_number;
	}
	public void setCmt_number(int cmt_number) {
		this.cmt_number = cmt_number;
	}
	public String getCmt_name() {
		return cmt_name;
	}
	public void setCmt_name(String cmt_name) {
		this.cmt_name = cmt_name;
	}
	public String getCmt_content() {
		return cmt_content;
	}
	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}
	public Date getCmt_date() {
		return cmt_date;
	}
	public void setCmt_date(Date cmt_date) {
		this.cmt_date = cmt_date;
	}
	public int getArticle_number() {
		return article_number;
	}
	public void setArticle_number(int article_number) {
		this.article_number = article_number;
	}
	@Override
	public String toString() {
		return "CommentVO [cmt_number=" + cmt_number + ", cmt_name=" + cmt_name + ", cmt_content=" + cmt_content
				+ ", cmt_date=" + cmt_date + ", article_number=" + article_number + ", cmt_password=" + cmt_password
				+ "]";
	}
	public String getCmt_password() {
		return cmt_password;
	}
	public void setCmt_password(String cmt_password) {
		this.cmt_password = cmt_password;
	}
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	
	
	
	
	
}
