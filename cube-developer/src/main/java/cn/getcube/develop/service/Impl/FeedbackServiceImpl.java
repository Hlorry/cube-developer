package cn.getcube.develop.service.Impl;

import cn.getcube.develop.dao.developes.FeedbackDao;
import cn.getcube.develop.entity.FeedbackEntity;
import cn.getcube.develop.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Resource
	private FeedbackDao feedbackDao;

	@Override
	public void add(FeedbackEntity feedback) {
		feedbackDao.insertFeedback(feedback);
	}

	
}
