package cn.getcube.develop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/3/16.
 */
public class MD5 {
    private String md5;

    private MD5(String source, Object salt) {
        this.md5 = generateMD5(source + "{" + salt.toString() + "}");
    }

    private String generateMD5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (int offset = 0, l = b.length; offset < l; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            md5 = buf.toString();
            return md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMD5() {
        return md5;
    }

    public String toString() {
        return "MD5{" +
                "md5='" + md5 + '\'' +
                '}';
    }

    public static class Builder {

        private String source;
        private String salt;

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder salt(String salt) {
            this.salt = salt;
            return this;
        }

        public MD5 build() {
            return new MD5(source, salt);
        }
    }
}
