package cn.getcube.develop.entity;

public class FeedbackEntity {
	private int id;
	private int userId;
	private String title;
	private String content;
	private String phone;
	private String createTime;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public FeedbackEntity(int id, int userId, String title, String content, String phone, String createTime) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.phone = phone;
		this.createTime = createTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public FeedbackEntity() {
		super();
	}
	
}
