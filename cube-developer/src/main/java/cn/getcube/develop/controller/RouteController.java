package cn.getcube.develop.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/3/14.
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/route")
public class RouteController {

    /**
     * 产品 跳转
     *
     * @return
     */
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView product() {
        return new ModelAndView("product");
    }

    /**
     * 价格 跳转
     *
     * @return
     */
    @RequestMapping(value = "/price", method = RequestMethod.GET)
    public ModelAndView price() {
        return new ModelAndView("price");
    }

    /**
     * 文档 跳转
     *
     * @return
     */
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public ModelAndView file() {
        return new ModelAndView("file");
    }

    /**
     * 登陆 跳转
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    /**
     * 注册 跳转
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    /**
     * 企业简介 跳转
     *
     * @return
     */
    @RequestMapping(value = "/introduction", method = RequestMethod.GET)
    public ModelAndView introduction() {
        return new ModelAndView("introduction");
    }

    /**
     * 企业案例跳转
     *
     * @return
     */
    @RequestMapping(value = "/casepage", method = RequestMethod.GET)
    public ModelAndView casepage() {
        return new ModelAndView("casepage");
    }

    /**
     * 企业案例跳转
     *
     * @return
     */
    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public ModelAndView app() {
        return new ModelAndView("app");
    }

    /**
     * 个人信息跳转
     *
     * @return
     */
    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public ModelAndView personal() {
        return new ModelAndView("personal");
    }

    /**
     * 忘记密码
     *
     * @return
     */
    @RequestMapping(value = "/forget", method = RequestMethod.GET)
    public ModelAndView forget() {
        return new ModelAndView("forget");
    }

    /**
     * 下载
     *
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView download() {
        return new ModelAndView("sdk_download");
    }

    /**
     * 下载
     *
     * @return
     */
    @RequestMapping(value = "/sdkdownload", method = RequestMethod.GET)
    public ModelAndView sdkdownload() {
        return new ModelAndView("sdk_download");
    }
    
    /**
     * 知识库
     *
     * @return
     */
    @RequestMapping(value = "/knowledge", method = RequestMethod.GET)
    public ModelAndView knowledge() {
        return new ModelAndView("knowledge");
    }
    /**
     * 应用详情
     *
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail() {
        return new ModelAndView("detail");
    }
    /**
     * 反馈
     * @return
     */
    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public ModelAndView feedback(){
    	 return new ModelAndView("feedback");
    }
    /**
     * 反馈成功
     * @return
     */
    @RequestMapping(value = "/feedback_success", method = RequestMethod.GET)
    public ModelAndView feedback_success(){
    	 return new ModelAndView("feedback_success");
    }
}
