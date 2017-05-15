package cn.getcube.develop;

/**
 * Created by SubDong on 2016/3/14.
 */
public interface EmailConstants {


    //用户注册
    String registerTemplate = "<!DOCTYPE html>" +
            "<html> " +
            "<head> " +
            "<meta charset=\"UTF-8\"> " +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=10\"> " +
            "</head>" +
            "<body>" +
            "<div> " +
            "<span>此验证码30分钟内有效</span> <br /> " +
            "<span>尊敬的%s，您好：\n" +
            "请点击以下链接完成注册：%s\n" +
            "如果您未注册过开发者平台帐号，请忽略本邮件。感谢您对开发者平台的关注与支持，如果您在使用产品过程中遇到任何问题，可随时联系我们，我们定将竭诚为您服务。</span><br />" +
            "<span style='font-size:14px;color:red'>注：如果点击无效请复制此连接到浏览器中访问：</span>" +
            "</div> " +
            "</body> " +
            "</html>";

    //用户忘记密码验证码发送
    String forgetTemplate = "<!DOCTYPE html>" +
            "<html> " +
            "<head> " +
            "<meta charset=\"UTF-8\"> " +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=10\"> " +
            "</head>" +
            "<body>" +
            "<div> " +
            "<span>此验证码30分钟内有效</span> <br /> " +
            "<span>您好：\n" +
            "请点击以下链接完成你的密码重置：%s\n" +
            "如果您未申请过开发者平台帐号密码重置，请忽略本邮件。感谢您对开发者平台的关注与支持，如果您在使用产品过程中遇到任何问题，可随时联系我们，我们定将竭诚为您服务。</span><br />" +
            "<span style='font-size:14px;color:red'>注：如果点击无效请复制此连接到浏览器中访问：</span>" +
            "</div> " +
            "</body> " +
            "</html>";

    //用户修改邮箱邮件发送
    String fixTemplate = "<!DOCTYPE html>" +
            "<html> " +
            "<head> " +
            "<meta charset=\"UTF-8\"> " +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=10\"> " +
            "</head>" +
            "<body>" +
            "<div> " +
            "<span>此验证码30分钟内有效</span> <br /> " +
            "<span>您好：\n" +
            "请点击以下链接完成你的邮箱修改：%s\n" +
            "如果您未申请过开发者平台帐号邮箱修改，请忽略本邮件。感谢您对开发者平台的关注与支持，如果您在使用产品过程中遇到任何问题，可随时联系我们，我们定将竭诚为您服务。</span><br />" +
            "<span style='font-size:14px;color:red'>注：如果点击无效请复制此连接到浏览器中访问：</span>" +
            "</div> " +
            "</body> " +
            "</html>";

    //用户修改邮箱邮件发送
    String bindTemplate = "<!DOCTYPE html>" +
            "<html> " +
            "<head> " +
            "<meta charset=\"UTF-8\"> " +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=10\"> " +
            "</head>" +
            "<body>" +
            "<div> " +
            "<span>此验证码30分钟内有效</span> <br /> " +
            "<span>您好：\n" +
            "请点击以下链接完成你的邮箱绑定：%s\n" +
            "如果您未申请过开发者平台帐号邮箱绑定，请忽略本邮件。感谢您对开发者平台的关注与支持，如果您在使用产品过程中遇到任何问题，可随时联系我们，我们定将竭诚为您服务。</span><br />" +
            "<span style='font-size:14px;color:red'>注：如果点击无效请复制此连接到浏览器中访问：</span>" +
            "</div> " +
            "</body> " +
            "</html>";
}
