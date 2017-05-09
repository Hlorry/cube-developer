package cn.getcube.develop.service;

import cn.getcube.develop.entity.CertifiedEntity;

/**
 * Created by Administrator on 2016/3/14.
 */
public interface CertifiedService {
    CertifiedEntity queryCertified(int userId,int type);

    void saveCertified(CertifiedEntity certified);

    /**
     * 保存个人信息
     * @param certified
     * @return
     */
    int savePersonal(CertifiedEntity certified);

    /**
     * 更新个人信息
     * @param certified
     * @return
     */
    int updatePersonal(CertifiedEntity certified);

    /**
     * 通过用户id查询认证信息
     * @param userId
     * @return
     */
    CertifiedEntity queryByUserId(Integer userId,int type);
}
