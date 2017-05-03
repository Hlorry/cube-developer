package cn.getcube.develop.dao.developes;

import cn.getcube.develop.entity.CertifiedEntity;

/**
 * Created by Administrator on 2016/3/14.
 */
public interface CertifiedDao {
    CertifiedEntity queryByUserId(int userId);

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
}
