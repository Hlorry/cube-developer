package cn.getcube.develop.service;

import cn.getcube.develop.entity.LoginLog;
import cn.getcube.develop.para.LoginLogPara;

import java.util.List;

/**
 * Created by Rainbow on 16-6-3.
 */
public interface LoginLogService {

    List<LoginLog> queryInterval(LoginLogPara para);

}
