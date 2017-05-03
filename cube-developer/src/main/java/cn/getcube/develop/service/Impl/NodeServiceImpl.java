package cn.getcube.develop.service.Impl;

import cn.getcube.develop.dao.developes.NodeDao;
import cn.getcube.develop.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class NodeServiceImpl implements NodeService {

	@Autowired
	private NodeDao nodeDao;
	
	@Override
	public int getOneTestNode() {
		return nodeDao.getOneTestNode();
	}

	@Override
	public void insertAppNode(Map para) {

	}

}
