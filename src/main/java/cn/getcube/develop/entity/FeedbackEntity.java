package cn.getcube.develop.entity;

public class FeedbackEntity {
	private int id;
	private String title;
	private String content;
	private String phone;
	private String createtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public FeedbackEntity(int id, String title, String content, String phone, String createtime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.phone = phone;
		this.createtime = createtime;
	}
	public FeedbackEntity() {
		super();
	}
	
}
