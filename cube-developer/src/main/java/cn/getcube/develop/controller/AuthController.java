package cn.getcube.develop.controller;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.EmailConstants;
import cn.getcube.develop.HttpUriCode;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.anaotation.TokenVerify;
import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.CertifiedEntity;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.service.CertifiedService;
import cn.getcube.develop.utils.*;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static cn.getcube.develop.StateCode.AUTH_ERROR_10004;
import static cn.getcube.develop.StateCode.Ok;

/**
 * Created by Administrator on 2016/3/14.
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/auth")
public class AuthController {

    @Resource
    JedisCluster jc;
    @Resource
    private CertifiedService certifiedService;
    @Resource
    private UserDao userDao;

    /**
     * 通过ID 查询认证信息
     *
     * @param token
     * @param version
     * @return
     */
    @RequestMapping(value = "/certified/find", method = RequestMethod.POST)
    @TokenVerify
    public BaseResult certifiedFind(@RequestParam(name = "token", required = true) String token,
                                    @RequestParam(name = "id", required = true) Integer id,
                                    @RequestParam(name = "version", required = false) String version,
                                    UserEntity userSession) {
        CertifiedEntity ce = certifiedService.queryCertified(id);
        Map<String, Object> map = new HashMap<>();
        if (Objects.isNull(ce)) {
            return BaseResult.build(StateCode.AUTH_ERROR_10008, "No such check information");
        } else {
            //获取uri 邮箱验证时用户访问页面
            //String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            if(ce.getType()==1){
                ce.setPlPositiveImg(HttpUriCode.HTTP_CODE_URI + ce.getPlPositiveImg());
                ce.setPlSideImg(HttpUriCode.HTTP_CODE_URI + ce.getPlSideImg());
            }else {
                ce.setTaxImg(HttpUriCode.HTTP_CODE_URI + ce.getTaxImg());
                ce.setAgencyImg(HttpUriCode.HTTP_CODE_URI + ce.getAgencyImg());
                ce.setLicenseImg(HttpUriCode.HTTP_CODE_URI + ce.getLicenseImg());
            }
            map.put("cube", ce);

            DataResult<Map<String, Object>> dataResult = new DataResult<>();
            dataResult.setCode(Ok.getCode());
            dataResult.setDesc("OK.");
            dataResult.setData(map);
            return dataResult;
        }
    }

    /**
     * 查询当前登录用户是否上传企业认证信息
     *
     * @return
     */
    @RequestMapping(value = "/certified/queryByUserId", method = RequestMethod.POST)
    @TokenVerify
    public BaseResult queryByUserIdCertified(@RequestParam(name = "token", required = true) String token,
                                             @RequestParam(name = "version", required = false) String version,
                                             UserEntity userSession) {

        Map<String, Object> map = new HashMap<>();
        if (token == null) {
            return BaseResult.build(StateCode.AUTH_ERROR_10016, "参数缺失");
        } else {
            CertifiedEntity certifiedEntity = certifiedService.queryByUserId(userSession.getId(),0);
            if (certifiedEntity != null) {
                DataResult<Map<String, Object>> dataResult = new DataResult<>();
                dataResult.setCode(Ok.getCode());
                dataResult.setDesc("当前用户已上传企业证人信息，请耐心等待审核结果");

                certifiedEntity.setTaxImg(HttpUriCode.HTTP_CODE_URI + certifiedEntity.getTaxImg());
                certifiedEntity.setAgencyImg(HttpUriCode.HTTP_CODE_URI + certifiedEntity.getAgencyImg());
                certifiedEntity.setLicenseImg(HttpUriCode.HTTP_CODE_URI + certifiedEntity.getLicenseImg());
                certifiedEntity.setPlPositiveImg(HttpUriCode.HTTP_CODE_URI + certifiedEntity.getPlPositiveImg());
                certifiedEntity.setPlSideImg(HttpUriCode.HTTP_CODE_URI + certifiedEntity.getPlSideImg());
                map.put("cube", certifiedEntity);
                dataResult.setData(map);
                return dataResult;
            } else {
                return BaseResult.build(StateCode.AUTH_ERROR_10008, "当前用户还未上传企业认证信息");
            }
        }
    }

    /**
     * 详细信息保存
     *
     * @return
     */
    @TokenVerify
    @RequestMapping(value = "/certified/save", method = RequestMethod.POST)
    public BaseResult certifiedSave(@RequestParam(name = "token", required = true) String token,
                                      @RequestParam(name = "cpName", required = true) String cpName,
                                      @RequestParam(name = "cpAddress", required = true) String cpAddress,
                                      @RequestParam(name = "licenseNum", required = true) String licenseNum,
                                      @RequestParam(name = "agency", required = true) String agency,
                                      @RequestParam(name = "taxNum", required = true) String taxNum,
                                      @RequestParam(name = "corporate", required = true) String corporate,
                                      @RequestParam(name = "cpPhone", required = true) String cpPhone,
                                      @RequestParam(name = "cpWebsite", required = true) String cpWebsite,
                                      @RequestParam(name = "license", required = true) MultipartFile license,
                                      @RequestParam(name = "agencyImg", required = true) MultipartFile agencyImg,
                                      @RequestParam(name = "taxImg", required = true) MultipartFile taxImg,
                                      @RequestParam(name = "email", required = false) String email,
                                      @RequestParam(name = "level", required = false) Integer level,
                                      @RequestParam(name = "version", required = false) String version,
                                      UserEntity userSession) {
        BaseResult result = new BaseResult();
        int id = userSession.getId();

        String licenseUrl = FileUploadUtils.uploadFile(license, 2);
        String agencyImgUrl = FileUploadUtils.uploadFile(agencyImg, 2);
        String taxImgUrl = FileUploadUtils.uploadFile(taxImg, 2);

        CertifiedEntity certifiedEntity = new CertifiedEntity();
        certifiedEntity.setCompanyName(cpName);
        certifiedEntity.setCompanyAddress(cpAddress);
        certifiedEntity.setLicenseNum(licenseNum);
        certifiedEntity.setLicenseImg(licenseUrl);
        certifiedEntity.setAgencyNum(agency);
        certifiedEntity.setAgencyImg(agencyImgUrl);
        certifiedEntity.setTaxNum(taxNum);
        certifiedEntity.setTaxImg(taxImgUrl);
        certifiedEntity.setCorporate(corporate);
        certifiedEntity.setCompanyPhone(cpPhone);
        certifiedEntity.setCompanyWebsite(cpWebsite);
        certifiedEntity.setUserId(id);
        certifiedEntity.setType(0);
        try {
            CertifiedEntity queryByUserId = certifiedService.queryByUserId(certifiedEntity.getUserId(),0);
            if (queryByUserId != null) {
                result.setCode(StateCode.AUTH_ERROR_10019.getCode());
                result.setDesc("你已上传企业证人信息，请耐心等待审核结果");
            } else {
                certifiedService.saveCertified(certifiedEntity);
                result.setCode(StateCode.Ok.getCode());
                result.setDesc(AuthConstants.MSG_OK);
            }

        } catch (Exception e) {
            result.setCode(StateCode.AUTH_ERROR_10007.getCode());
            result.setDesc("Failed to save authentication information");
        }
        return result;
    }

    /**
     * 发送账号验证邮件
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public BaseResult product(@RequestParam(name = "account", required = true) String account,
                              @RequestParam(name = "version", required = false) String version) {
        UserEntity userEntity = new UserEntity();
        if (account.contains("@")) {
            userEntity.setEmail(account);
        } else {
            userEntity.setPhone(account);
        }
        UserEntity user = userDao.queryUser(userEntity);

        if (user != null && Objects.nonNull(userEntity.getEmail())) {
            //发送Email 验证
            //MD5去重算法生成mail验证
            String md5 = Md5Helper.MD5.getMD5(System.currentTimeMillis()+"");
            jc.set(md5, user.getId() + "");
            jc.expire(md5, AuthConstants.AUTH_TOKEN_FAIL_TIME);
            //发送数据
            EmailUtils.sendHtmlEmail("cube-开发者平台-注册验证", String.format(EmailConstants.registerTemplate, user.getName(), HttpUriCode.HTTP_CODE_URI + "/auth/email/activation?actmd5=" + md5), user.getEmail());
            return BaseResult.build(Ok, AuthConstants.MSG_OK);
        } else if (user != null && Objects.nonNull(userEntity.getPhone())) {
            SendMSMUtils.postRequest(account,null,1);
            return BaseResult.build(Ok, AuthConstants.MSG_OK);
        } else {
            return BaseResult.build(StateCode.AUTH_ERROR_10021.getCode(), "帐号不存在");
        }
    }

    /**
     * 密码重置验证邮件或手机发送
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/password/forget", method = RequestMethod.POST)
    public BaseResult forget(@RequestParam(name = "account", required = true) String account,
                             @RequestParam(name = "version", required = false) String version) {

        UserEntity userEntity = new UserEntity();
        if (account.contains("@")) {
            userEntity.setEmail(account);
        } else {
            userEntity.setPhone(account);
        }

        UserEntity user = userDao.queryUser(userEntity);

        if (Objects.isNull(user)) {
            return BaseResult.build(StateCode.AUTH_ERROR_10021, "No users");
        }

        //没有激活不能找回密码
        if (user.getActivation()==0) {
            return BaseResult.build(StateCode.AUTH_ERROR_10034, "账号未激活");
        }
        //获取uri 邮箱验证时用户访问页面
        //String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        if (Objects.nonNull(userEntity.getEmail())) {
            //发送Email 验证
            //MD5去重算法生成mail验证
            String md5 = Md5Helper.MD5.getMD5(System.currentTimeMillis()+"");
            jc.set(md5, user.getId() + "");
            jc.expire(md5, AuthConstants.AUTH_TOKEN_FAIL_TIME);
            EmailUtils.sendHtmlEmail("cube-开发者平台", String.format(EmailConstants.forgetTemplate, HttpUriCode.HTTP_CODE_URI + "/auth/password/activation?actmd5=" + md5), account);
            return BaseResult.build(Ok, AuthConstants.MSG_OK);
        } else {
            int postRequest = SendMSMUtils.postRequest(account,null,4);
            if (200==postRequest) {
                return BaseResult.build(Ok, AuthConstants.MSG_OK);
            } else {
                return BaseResult.build(StateCode.AUTH_ERROR_9999, "SMS send failure");
            }
        }
    }

    /**
     * 修改邮箱发送验证邮件
     *
     * @param email
     * @return
     */
    @TokenVerify
    @RequestMapping(value = "/email/fix", method = RequestMethod.POST)
    public BaseResult fix(@RequestParam(name = "token", required = true) String token,
                          @RequestParam(name = "email", required = true) String email,
                          @RequestParam(name = "version", required = false) String version,
                          UserEntity userSession) {
        UserEntity userEntity = new UserEntity();
        if (RegexUtil.isEmail(email)) {
            userEntity.setEmail(email);
        }else {
            return BaseResult.build(AUTH_ERROR_10004, AuthConstants.FORMAT_ERROR);
        }

        UserEntity user = userDao.queryUser(userEntity);
        if (!Objects.isNull(user)) {
            return BaseResult.build(StateCode.AUTH_ERROR_10023, "邮箱已被注册");
        }
        //获取uri 邮箱验证时用户访问页面
        //String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        //发送Email 验证
        //MD5去重算法生成mail验证
        String md5 = Md5Helper.MD5.getMD5(System.currentTimeMillis()+"");
        jc.set(md5+"_fix", userSession.getId() + "_"+email);
        jc.expire(md5+"_fix", AuthConstants.AUTH_TOKEN_FAIL_TIME);
        EmailUtils.sendHtmlEmail("cube-开发者平台", String.format(EmailConstants.fixTemplate, HttpUriCode.HTTP_CODE_URI + "/auth/fix/activation?actmd5=" + md5), email);
        return BaseResult.build(Ok, AuthConstants.MSG_OK);
    }

    /**
     * 修改邮箱发送验证邮件
     *
     * @param email
     * @return
     */
    @TokenVerify
    @RequestMapping(value = "/email/bind", method = RequestMethod.POST)
    public BaseResult bindEmail(@RequestParam(name = "token", required = true) String token,
                          @RequestParam(name = "email", required = true) String email,
                          @RequestParam(name = "version", required = false) String version,
                          UserEntity userSession) {
        if(!Objects.isNull(userSession.getEmail())){
            return BaseResult.build(StateCode.AUTH_ERROR_10032, "已绑定邮箱");
        }

        UserEntity userEntity = new UserEntity();
        if (RegexUtil.isEmail(email)) {
            userEntity.setEmail(email);
        }else {
            return BaseResult.build(AUTH_ERROR_10004, AuthConstants.FORMAT_ERROR);
        }

        UserEntity user = userDao.queryUser(userEntity);
        if (!Objects.isNull(user)) {
            return BaseResult.build(StateCode.AUTH_ERROR_10023, "邮箱已被注册");
        }
        //获取uri 邮箱验证时用户访问页面
        //String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        //发送Email 验证
        //MD5去重算法生成mail验证
        String md5 = Md5Helper.MD5.getMD5(System.currentTimeMillis()+"");
        jc.set(md5+"_bind", userSession.getId() + "_"+email);
        jc.expire(md5+"_bind", AuthConstants.AUTH_TOKEN_FAIL_TIME);
        EmailUtils.sendHtmlEmail("cube-开发者平台", String.format(EmailConstants.bindTemplate, HttpUriCode.HTTP_CODE_URI + "/auth/bind/activation?actmd5=" + md5), email);
        return BaseResult.build(Ok, AuthConstants.MSG_OK);
    }


    /**
     * 修改邮箱发送验证邮件
     *
     * @return
     */
    @TokenVerify
    @RequestMapping(value = "/email/unbind", method = RequestMethod.POST)
    public BaseResult unbindEmail(@RequestParam(name = "token", required = true) String token,
                                @RequestParam(name = "version", required = false) String version,
                                UserEntity userSession) {
        if(Objects.isNull(userSession.getEmail())){
            return BaseResult.build(StateCode.AUTH_ERROR_10030, "未绑定邮箱");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userSession.getId());
        UserEntity user = userDao.queryUser(userEntity);
        if (Objects.isNull(user.getPhone())) {
            return BaseResult.build(StateCode.AUTH_ERROR_10029, "未绑定手机，不能解绑邮箱");
        }
        //获取uri 邮箱验证时用户访问页面
        //String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        //发送Email 验证
        //MD5去重算法生成mail验证
        String md5 = Md5Helper.MD5.getMD5(System.currentTimeMillis()+"");
        jc.set(md5+"_unbind", userSession.getId() + "_"+user.getEmail());
        jc.expire(md5+"_unbind", AuthConstants.AUTH_TOKEN_FAIL_TIME);
        EmailUtils.sendHtmlEmail("cube-开发者平台", String.format(EmailConstants.unbindTemplate, HttpUriCode.HTTP_CODE_URI + "/auth/unbind/activation?actmd5=" + md5), userSession.getEmail());
        return BaseResult.build(Ok, AuthConstants.MSG_OK);
    }



    /**
     * 密码重置
     *
     * @param newPwd
     * @param version
     * @return
     */
    @RequestMapping(value = "/password/mailupdate", method = RequestMethod.POST)
    public BaseResult mailupdate(@RequestParam(name = "actmd5", required = true) String actmd5,
                                 @RequestParam(name = "newPwd", required = true) String newPwd,
                                 @RequestParam(name = "version", required = false) String version) {
        BaseResult result = new BaseResult();
        if (!jc.exists(actmd5)) {
            result.setCode(StateCode.AUTH_ERROR_10012.getCode());
            result.setDesc("Verify expired");
            return result;
        }
        int id = Integer.parseInt(jc.get(actmd5));
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        MD5 md5 = new MD5.Builder().source(newPwd).salt(AuthConstants.USER_SALT).build();
        userEntity.setPassword(md5.getMD5());
        userEntity.setUpdate_time(new Date());
        int updateUser = userDao.updateUser(userEntity);
        if (updateUser > 0) {
            //删除验证redis-key
            jc.del(actmd5);
            result.setCode(StateCode.Ok.getCode());
            result.setDesc(AuthConstants.MSG_OK);
            return result;
        }
        result.setCode(StateCode.AUTH_ERROR_100.getCode());
        result.setDesc("unknown mistake");
        return result;
    }

    /**
     * token 失效时间重置
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/token/reset", method = RequestMethod.POST)
    public DataResult<Map> reset(@RequestParam(name = "token", required = true) String token, UserEntity userSession) {
        DataResult result = new DataResult();
        jc.expire(token, AuthConstants.AUTH_TOKEN_FAIL_TIME);
        Map<String, String> map = new HashMap<>();
        result.setCode(AuthConstants.OK);
        result.setDesc(AuthConstants.MSG_OK);
        map.put("token", token);
        result.setData(map);
        return result;
    }

    /**
     * 登陆后修改密码
     *
     * @param token
     * @param oldPwd
     * @param newPwd
     * @param version
     * @return
     */
    @TokenVerify
    @RequestMapping(value = "/password/update", method = RequestMethod.POST)
    public BaseResult resmailupdateet(@RequestParam(name = "token", required = true) String token,
                                        @RequestParam(name = "oldPwd", required = true) String oldPwd,
                                        @RequestParam(name = "newPwd", required = true) String newPwd,
                                        @RequestParam(name = "version", required = false) String version,
                                        UserEntity userSession) {
        BaseResult result = new BaseResult();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userSession.getId());
        UserEntity entity = userDao.queryUser(userEntity);
        MD5 md5 = new MD5.Builder().source(oldPwd).salt(AuthConstants.USER_SALT).build();
        if (entity.getPassword().equals(md5.getMD5())) {
            MD5 md51 = new MD5.Builder().source(newPwd).salt(AuthConstants.USER_SALT).build();
            userEntity.setPassword(md51.getMD5());
            userEntity.setUpdate_time(new Date());
            int updateUser = userDao.updateUser(userEntity);
            if (updateUser > 0) {
                jc.del("token"+userSession.getId());
                jc.del(token);
                result.setCode(AuthConstants.OK);
                result.setDesc(AuthConstants.MSG_OK);
            } else {
                result.setCode(StateCode.AUTH_ERROR_100.getCode());
                result.setDesc("unknown mistake");
            }
        } else {
            result.setCode(StateCode.AUTH_ERROR_10003.getCode());
            result.setDesc("Old password is incorrect");
        }
        return result;
    }

    /**
     * 个人信息保存
     *
     * @return
     */
    @TokenVerify
    @RequestMapping(value = "/personal/save", method = RequestMethod.POST)
    public BaseResult personalSave(@RequestParam(name = "token", required = true) String token,
                                        @RequestParam(name = "plName", required = true) String plName,
                                        @RequestParam(name = "plType", required = true) Integer plType,
                                        @RequestParam(name = "plCardNum", required = true) String plCardNum,
                                        @RequestParam(name = "plPositiveImg", required = false) MultipartFile plPositiveImg,
                                        @RequestParam(name = "plSideImg", required = false) MultipartFile plSideImg,
                                        @RequestParam(name = "plHidnumber", required = false) MultipartFile plHidnumber,
                                        @RequestParam(name = "passport", required = false) MultipartFile passport,
                                        @RequestParam(name = "version", required = false) String version,
                                        UserEntity userSession) {
            BaseResult result = new BaseResult();
            int id = userSession.getId();
            String plPositiveImgUrl = plPositiveImg==null?null: FileUploadUtils.uploadFile(plPositiveImg, 3);
            String plSideImgUrl = plSideImg==null ? null :FileUploadUtils.uploadFile(plSideImg, 3);
            String plHidnumberUrl = plHidnumber==null?null:FileUploadUtils.uploadFile(plHidnumber, 3);
            String passportUrl = passport==null?null:FileUploadUtils.uploadFile(passport, 3);
            CertifiedEntity entity = new CertifiedEntity();
            entity.setUserId(id);
            entity.setPlName(plName);
            entity.setPlType(plType);
            entity.setPlCardNum(plCardNum);
            entity.setType(1);
            if (plPositiveImgUrl != null) {
                entity.setPlPositiveImg(plPositiveImgUrl);
            }
            if (plSideImgUrl != null) {
                entity.setPlSideImg(plSideImgUrl);
            }

            if (plHidnumberUrl != null) {
                entity.setPlHidnumber(plHidnumberUrl);
            }
            if (passportUrl != null) {
                entity.setPassport(passportUrl);
            }

            try {
                CertifiedEntity ce = certifiedService.queryByUserId(id,1);
                if (Objects.isNull(ce)) {
                    certifiedService.savePersonal(entity);
                    result.setCode(StateCode.Ok.getCode());
                    result.setDesc(AuthConstants.MSG_OK);
                } else {
                    certifiedService.updatePersonal(entity);
                    result.setCode(StateCode.Ok.getCode());
                    result.setDesc(AuthConstants.MSG_OK);
                }
            }catch (Exception e){
                result.setCode(StateCode.Unknown.getCode());
                result.setDesc("Unknown error");
            }
        return result;
    }

}
