package cn.getcube.develop.service.Impl;

import cn.getcube.develop.dao.cube.LoginLogDao;
import cn.getcube.develop.entity.LoginLog;
import cn.getcube.develop.para.LoginLogPara;
import cn.getcube.develop.service.LoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Rainbow on 16-6-3.
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogDao loginLogDao;

    @Override
    public List<LoginLog> queryInterval(LoginLogPara para) {
        List<LoginLog> loginLogs = loginLogDao.queryInterval(para);
        return loginLogs;
    }
}
