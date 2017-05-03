package cn.getcube.develop.service;

import cn.getcube.develop.entity.FeedbackEntity;

public interface FeedbackService {
	/**
	 * 插入一条反馈数据
	 * @param feedback
	 */
	public void add(FeedbackEntity feedback);
}
