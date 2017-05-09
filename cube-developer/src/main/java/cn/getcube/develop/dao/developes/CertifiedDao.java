package cn.getcube.develop.dao.developes;

import cn.getcube.develop.entity.CertifiedEntity;

/**
 * Created by Administrator on 2016/3/14.
 */
public interface CertifiedDao {
    /**
     * 根据用户id查询
     * @param userId
     * @param type
     * @return
     */
    CertifiedEntity queryByUserId(int userId,int type);

    void saveCertified(CertifiedEntity certified);

    /**
     * 保存个人信息
     * @param certified
     * @return
     */
    int savePersonal(CertifiedEntity certified);

    /**
     *
     * @param certified
     * @return
     */
    int updatePersonal(CertifiedEntity certified);

    /**
     *  根据id查询
     * @param id
     * @return
     */
    CertifiedEntity queryCertified(int id);
}
