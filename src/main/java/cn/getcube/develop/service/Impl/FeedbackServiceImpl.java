package cn.getcube.develop.service.Impl;

import cn.getcube.develop.dao.developes.FeedbackDao;
import cn.getcube.develop.entity.FeedbackEntity;
import cn.getcube.develop.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public void add(FeedbackEntity feedback) {
		feedbackDao.insertFeedback(feedback);
	}

	
}
