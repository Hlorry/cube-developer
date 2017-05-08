package cn.getcube.develop.utils;

import org.apache.commons.mail.HtmlEmail;

import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

/**
 * Created by SubDong on 2016/3/14.
 */
public class EmailUtils {

    private static final String CHARSET_UTF_8 = "utf-8";

    private static String hostName;     // 邮件服务器
    private static String from;         // 邮件发送者
    private static String userName;     // 邮箱地址
    private static String password;     // 密码


    static {
        Properties props = new Properties();

        try {
            props.load(new InputStreamReader(EmailUtils.class.getClassLoader().getResourceAsStream("email.properties"), CHARSET_UTF_8));

            hostName = props.getProperty("email.hostName");
            from = props.getProperty("email.from");
            userName = props.getProperty("email.userName");
            password = props.getProperty("email.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>发送HTML邮件
     *
     * @param subject     邮件主题
     * @param msg         邮件内容
     * @param targetEmail 收件人地址
     */
    public static void sendHtmlEmail(String subject, String msg, String targetEmail) {
        HtmlEmail email = new HtmlEmail();

        email.setHostName(hostName);
        email.setAuthentication(userName, password);
        email.setCharset(CHARSET_UTF_8);
        email.setStartTLSEnabled(true);
        try {
            email.setFrom(userName, MimeUtility.encodeText(from,MimeUtility.mimeCharset("utf-8"), null));
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.addTo(targetEmail);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String changeCharSet(
            String str, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return str;
    }


    //生成随机数字和字母,
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "num" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
