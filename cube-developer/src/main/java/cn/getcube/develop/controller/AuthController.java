package cn.getcube.develop.controller;

import cn.getcube.develop.constants.AuthConstants;
import cn.getcube.develop.constants.EmailConstants;
import cn.getcube.develop.constants.HttpUriCode;
import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.CertifiedEntity;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.service.CertifiedService;
import cn.getcube.develop.utils.EmailUtils;
import cn.getcube.develop.utils.FileUploadUtils;
import cn.getcube.develop.utils.MD5;
import cn.getcube.develop.utils.Md5Helper;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/3/14.
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/auth")
public class AuthController {

    @Resource
    private CertifiedService certifiedService;

    @Resource
    private UserDao userDao;

    @Resource
    JedisCluster jc;

    @RequestMapping(value = "/certified/find", method = RequestMethod.POST)
    public ModelAndView certifiedFind(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(name = "token", required = true) String token,
                                      @RequestParam(name = "id", required = true) Integer id,
                                      @RequestParam(name = "version", required = false) String version) {
        AbstractView jsonView = new MappingJackson2JsonView();
        CertifiedEntity ce = certifiedService.queryCertified(id);
        Map<String, Object> map = new HashMap<>();
        if (Objects.isNull(ce)) {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10008);
            map.put(AuthConstants.AUTH_ERRMSG, "No such check information");
        } else {
            //获取uri 邮箱验证时用户访问页面
            //String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            ce.setTaxImg(HttpUriCode.HTTP_CODE_URI + ce.getTaxImg());
            ce.setAgencyImg(HttpUriCode.HTTP_CODE_URI + ce.getAgencyImg());
            ce.setLicenseImg(HttpUriCode.HTTP_CODE_URI + ce.getLicenseImg());
            ce.setPlPositiveImg(HttpUriCode.HTTP_CODE_URI + ce.getPlPositiveImg());
            ce.setPlSideImg(HttpUriCode.HTTP_CODE_URI + ce.getPlSideImg());
            map.put("cube", ce);
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    /**
     * 详细信息保存
     *
     * @return
     */
    @RequestMapping(value = "/certified/save", method = RequestMethod.POST)
    public ModelAndView certifiedSave(HttpServletRequest request, HttpServletResponse response,
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
                                      @RequestParam(name = "id", required = true) int id,
                                      @RequestParam(name = "email", required = false) String email,
                                      @RequestParam(name = "level", required = false) Integer level,
                                      @RequestParam(name = "version", required = false) String version) {
        String licenseUrl = FileUploadUtils.uploadFile(license, 2, request);
        String agencyImgUrl = FileUploadUtils.uploadFile(agencyImg, 2, request);
        String taxImgUrl = FileUploadUtils.uploadFile(taxImg, 2, request);

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

        Map<String, Object> map = new HashMap<>();
        try {
            CertifiedEntity queryByUserId = certifiedService.queryByUserId(certifiedEntity.getUserId());
            if(queryByUserId != null){
                map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10019);
                map.put(AuthConstants.AUTH_ERRMSG, "你已上传企业证人信息，请耐心等待审核结果");
                if(level != null && level == 1){
                    return new ModelAndView("redirect:/route/personal", map);
                }else{
                    return new ModelAndView("redirect:/route/register", map);
                }
            }else{
                certifiedService.saveCertified(certifiedEntity);
                map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
                map.put(AuthConstants.AUTH_SUCCESS, "certified save ok");
                map.put("email", email);
                if(level != null && level == 1){
                    return new ModelAndView("redirect:/route/personal", map);
                }else{
                    return new ModelAndView("redirect:/route/register", map);
                }
            }

        } catch (Exception e) {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10007);
            map.put(AuthConstants.AUTH_ERRMSG, "Failed to save authentication information");
            return new ModelAndView("redirect:/route/register", map);
        }
    }

    /**
     * 发送账号验证邮件
     *
     * @param request
     * @param response
     * @param email
     * @return
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ModelAndView product(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "email", required = true) String email,
                                @RequestParam(name = "version", required = false) String version) {
        AbstractView jsonView = new MappingJackson2JsonView();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        UserEntity user = userDao.queryUser(userEntity);

        //获取uri 邮箱验证时用户访问页面
        //String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        //发送Email 验证
        //MD5去重算法生成mail验证
        String md5 = Md5Helper.MD5.getMD5(user.getName());
        jc.set(md5, user.getId() + "");
        jc.expire(md5, AuthConstants.AUTH_TOKEN_FAIL_TIME);
        //发送数据
        EmailUtils.sendHtmlEmail("cube-开发者平台-注册验证", String.format(EmailConstants.registerTemplate, user.getName(), HttpUriCode.HTTP_CODE_URI + "/auth/activation?actmd5=" + md5), user.getEmail());

        Map<String, Object> map = new HashMap<>();
        map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
        map.put(AuthConstants.AUTH_SUCCESS, "ok");
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    /**
     * 邮件注册验证接口
     *
     * @param actmd5  系统生成的字符串
     *                当字符串为32位时表示注册账号验证，
     *                当字符串位16位时表示密码重置验证。
     * @param token
     * @param version
     * @return
     */
    @RequestMapping(value = "/activation", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView activation(@RequestParam(name = "actmd5", required = true) String actmd5,
                                   @RequestParam(name = "token", required = false) String token,
                                   @RequestParam(name = "version", required = false) String version) {
        String value = jc.get(actmd5);
        Map<String, Object> map = new HashMap<>();
        String redirect = "";
        if (value != null && actmd5.length() == 32) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(Integer.valueOf(value));
            userEntity.setUpdate_time(new Date());
            userEntity.setActivation(1);
            int updateUser = userDao.updateUser(userEntity);
            if (updateUser > 0) {
                map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
                map.put(AuthConstants.AUTH_SUCCESS, "ok");
                //删除验证reidskey
                jc.del(actmd5);
                redirect = "redirect:/route/register";
            }
        } else if (Objects.isNull(value)) {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10012);
            map.put(AuthConstants.AUTH_ERRMSG, "Verify expired");
            redirect = "redirect:/route/register";
        }
        return new ModelAndView(redirect, map);
    }

    /**
     * 邮件密码重置验证
     *
     * @param actmd5  系统生成的字符串
     * @param token
     * @param version
     * @return
     */
    @RequestMapping(value = "/password/activation", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView pwdActivation(@RequestParam(name = "actmd5", required = true) String actmd5,
                                      @RequestParam(name = "token", required = false) String token,
                                      @RequestParam(name = "version", required = false) String version) {
        String value = jc.get(actmd5);
        Map<String, Object> map = new HashMap<>();
        String redirect = "";
        if (value != null) {
            map.put("id", value);
            map.put("token", actmd5);
            map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
            map.put(AuthConstants.AUTH_SUCCESS, "ok");

            redirect = "redirect:/route/forget";
        } else if (Objects.isNull(value)) {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10012);
            map.put(AuthConstants.AUTH_ERRMSG, "Verify expired");
        }
        return new ModelAndView(redirect, map);
    }

    /**
     * 密码重置邮件发送
     *
     * @param request
     * @param response
     * @param email
     * @return
     */
    @RequestMapping(value = "/password/forget", method = RequestMethod.POST)
    public ModelAndView activation(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(name = "email", required = true) String email,
                                   @RequestParam(name = "version", required = false) String version) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = new HashMap<>();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        UserEntity user = userDao.queryUser(userEntity);

        if (Objects.isNull(user)) {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10008);
            map.put(AuthConstants.AUTH_ERRMSG, "No such check information");
            jsonView.setAttributesMap(map);
            return new ModelAndView(jsonView);
        }
        //获取uri 邮箱验证时用户访问页面
        //String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        //发送Email 验证
        //MD5去重算法生成mail验证
        String md5 = Md5Helper.MD5.getMD5(user.getName());
        jc.set(md5, user.getId() + "");
        jc.expire(md5, AuthConstants.AUTH_TOKEN_FAIL_TIME);

        EmailUtils.sendHtmlEmail("cube-开发者平台", String.format(EmailConstants.forgetTemplate, HttpUriCode.HTTP_CODE_URI + "/auth/password/activation?actmd5=" + md5), email);

        map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
        map.put(AuthConstants.AUTH_SUCCESS, "ok");
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    /**
     * 密码重置
     *
     * @param id
     * @param newPwd
     * @param version
     * @return
     */
    @RequestMapping(value = "/password/mailupdate", method = RequestMethod.POST)
    public ModelAndView mailupdate(@RequestParam(name = "actmd5", required = true) String actmd5,
                                   @RequestParam(name = "id", required = true) Integer id,
                                   @RequestParam(name = "newPwd", required = true) String newPwd,
                                   @RequestParam(name = "version", required = false) String version) {


        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = new HashMap<>();

        if (!jc.exists(actmd5)) {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10012);
            map.put(AuthConstants.AUTH_ERRMSG, "Verify expired");
            jsonView.setAttributesMap(map);
            return new ModelAndView(jsonView);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        MD5 md5 = new MD5.Builder().source(newPwd).salt(AuthConstants.USER_SALT).build();
        userEntity.setPassword(md5.getMD5());
        userEntity.setUpdate_time(new Date());
        int updateUser = userDao.updateUser(userEntity);
        if (updateUser > 0) {
            //删除验证reidskey
            jc.del(actmd5);
            map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
            map.put(AuthConstants.AUTH_SUCCESS, "ok");
            jsonView.setAttributesMap(map);
            return new ModelAndView(jsonView);
        }
        map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_100);
        map.put(AuthConstants.AUTH_ERRMSG, "unknown mistake");
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    /**
     * token 失效时间重置
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/token/reset", method = RequestMethod.POST)
    public ModelAndView reset(@RequestParam(name = "token", required = true) String token) {
        AbstractView jsonView = new MappingJackson2JsonView();

        jc.expire(token, AuthConstants.AUTH_TOKEN_FAIL_TIME);

        Map<String, Object> map = new HashMap<>();
        map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
        map.put(AuthConstants.AUTH_SUCCESS, "OK");
        map.put("token", token);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    /**
     * 登陆后修改密码
     *
     * @param token
     * @param id
     * @param oldPwd
     * @param newPwd
     * @param version
     * @return
     */
    @RequestMapping(value = "/password/update", method = RequestMethod.POST)
    public ModelAndView resmailupdateet(@RequestParam(name = "token", required = true) String token,
                                        @RequestParam(name = "id", required = true) Integer id,
                                        @RequestParam(name = "oldPwd", required = true) String oldPwd,
                                        @RequestParam(name = "newPwd", required = true) String newPwd,
                                        @RequestParam(name = "version", required = false) String version) {
        Boolean exists = jc.exists(token);
        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = new HashMap<>();

        if (exists) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            UserEntity entity = userDao.queryUser(userEntity);
            MD5 md5 = new MD5.Builder().source(oldPwd).salt(AuthConstants.USER_SALT).build();
            if (entity.getPassword().equals(md5.getMD5())) {
                MD5 md51 = new MD5.Builder().source(newPwd).salt(AuthConstants.USER_SALT).build();
                userEntity.setPassword(md51.getMD5());
                userEntity.setUpdate_time(new Date());
                int updateUser = userDao.updateUser(userEntity);
                if (updateUser > 0) {
                    map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
                    map.put(AuthConstants.AUTH_SUCCESS, "ok");
                } else {
                    map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_100);
                    map.put(AuthConstants.AUTH_ERRMSG, "unknown mistake");
                }
            } else {
                map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10003);
                map.put(AuthConstants.AUTH_ERRMSG, "Old password is incorrect");
            }
        } else {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10014);
            map.put(AuthConstants.AUTH_ERRMSG, "Please login account");
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    /**
     * 个人信息保存
     * @return
     */
    @RequestMapping(value = "/personal/save", method = RequestMethod.POST)
    public ModelAndView resmailupdateet(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(name = "token", required = true) String token,
                                        @RequestParam(name = "id", required = true) Integer id,
                                        @RequestParam(name = "plName", required = true) String plName,
                                        @RequestParam(name = "plType", required = true) Integer plType,
                                        @RequestParam(name = "plCardNum", required = true) String plCardNum,
                                        @RequestParam(name = "plPositiveImg", required = false) MultipartFile plPositiveImg,
                                        @RequestParam(name = "plSideImg", required = false) MultipartFile plSideImg,
                                        @RequestParam(name = "plHidnumber", required = false) MultipartFile plHidnumber,
                                        @RequestParam(name = "passport", required = false) MultipartFile passport,
                                        @RequestParam(name = "version", required = false) String version) {
        Boolean exists = jc.exists(token);

        Map<String, Object> map = new HashMap<>();

        if (exists) {
            String plPositiveImgUrl = FileUploadUtils.uploadFile(plPositiveImg, 3, request);
            String plSideImgUrl = FileUploadUtils.uploadFile(plSideImg, 3, request);
            String plHidnumberUrl = FileUploadUtils.uploadFile(plHidnumber, 3, request);
            String passportUrl = FileUploadUtils.uploadFile(passport, 3, request);

            CertifiedEntity entity = new CertifiedEntity();
            entity.setUserId(id);
            entity.setPlName(plName);
            entity.setPlType(plType);
            entity.setPlCardNum(plCardNum);
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

            CertifiedEntity ce = certifiedService.queryCertified(id);
            if (Objects.isNull(ce)) {
                certifiedService.savePersonal(entity);
                map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
                map.put(AuthConstants.AUTH_SUCCESS, "ok");
            } else {
                certifiedService.updatePersonal(entity);
                map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
                map.put(AuthConstants.AUTH_SUCCESS, "ok");
            }
        } else {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10014);
            map.put(AuthConstants.AUTH_ERRMSG, "Please login account");
        }

        return new ModelAndView("redirect:/route/personal", map);
    }

    /**
     * 企业认证信息查询
     * @return
     */
    @RequestMapping(value = "/certified/queryByUserId", method = RequestMethod.POST)
    public ModelAndView resmailupdateet(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(name = "token", required = true) String token,
                                        @RequestParam(name = "userId", required = true) Integer userId,
                                        @RequestParam(name = "version", required = false) String version) {

        Map<String, Object> map = new HashMap<>();
        AbstractView jsonView = new MappingJackson2JsonView();
        if(token == null){
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10016);
            map.put(AuthConstants.AUTH_ERRMSG, "参数缺失");
        }else{
            CertifiedEntity certifiedEntity = certifiedService.queryByUserId(userId);
            if(certifiedEntity != null){
                map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10019);
                map.put(AuthConstants.AUTH_ERRMSG, "你已上传企业证人信息，请耐心等待审核结果");
            }else{
                map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
                map.put(AuthConstants.AUTH_SUCCESS, "OK");
            }
        }


        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
