package cn.getcube.develop.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zjx on 2017/5/5.
 * 正则表达式
 */
public class RegexUtil {

    /**
     * 验证正则表达式
     */
    public static boolean validate(String str, String regex) {
        boolean flag;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证邮箱
     */
    public static boolean isEmail(String email) {
        final String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return validate(email, regex);
    }

    /**
     * 手机号码:
     * 13[0-9], 14[5,7], 15[0, 1, 2, 3, 5, 6, 7, 8, 9], 17[0, 1, 6, 7, 8], 18[0-9]
     * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
     * "^1(3[4-9]|4[7]|5[0-27-9]|7[08]|8[2-478])\\d{8}$"
     * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
     * "^1(3[0-2]|4[5]|5[56]|7[0156]|8[56])\\d{8}$"
     * 电信号段: 133,149,153,170,173,177,180,181,189
     * "^1(3[3]|4[9]|53|7[037]|8[019])\\d{8}$"
     */
    public static boolean checkMobile(String mobile) {
        if (null == mobile)
            return false;
        final String regex = "^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$";
        return validate(mobile, regex);
    }
}
