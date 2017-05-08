package cn.getcube.develop.utils;

import cn.getcube.develop.AuthConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by SubDong on 2016/3/16.
 */
public class FileUploadUtils {
    //用户头像存放地址
    public static final String FILE_PATH_USERAVATAR = "cubeImage/userAvatar/";
    //认证图片存放地址
    public static final String FILE_PATH_CERTIFIED = "cubeImage/certified/";
    //身份证图片存放地址
    public static final String FILE_PATH_IDCARD = "cubeImage/idCard/";
    //应用图片存放地址
    public static final String FILE_PATH_APPAVATAR = "cubeImage/appAvatar/";


    /**
     * 文件上传
     *
     * @param file
     * @param imageType 图片类型 1.用户头像 /cubeImage/userAvatar/  2.认证图片 /cubeImage/certified/  3.身份证图片  /cubeImage/idCard/
     *                  4.应用图标 /cubeImage/appAvatar/
     * @return
     */
    public static String uploadFile(MultipartFile file, int imageType) {
        String file_path = (imageType == 1 ? FILE_PATH_USERAVATAR : imageType == 2 ? FILE_PATH_CERTIFIED : FILE_PATH_IDCARD);
        file_path = imageType == 4 ? FILE_PATH_APPAVATAR : file_path;
        String allFileName = file.getOriginalFilename();
        String fileName = getFileExtension(allFileName);
        fileName = fileName.toLowerCase();
        //"gif", "jpeg", "jpg", "bmp", "png"
        if (fileName.equals("png") ||
                fileName.equals("gif") ||
                fileName.equals("jpeg") ||
                fileName.equals("jpg") ||
                fileName.equals("bmp")) {

            File tempFile = new File(AuthConstants.AUTH_FILE_PATH.replaceAll("\\\\", "/") + file_path, new Date().getTime() + "." + String.valueOf(fileName));
            File tempFileParent = new File(tempFile.getParentFile().getParent());
            if (!tempFileParent.exists()) {
                tempFileParent.mkdir();
            }
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdir();
            }
            try {
                if (!tempFile.exists()) {
                    tempFile.createNewFile();
                }
                file.transferTo(tempFile);
                return "/" + file_path + tempFile.getName();
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        } else {
            return null;
        }
    }

    public static String getFileExtension(String fullName) {
        FileUploadUtils.checkNotNull(fullName);
        String fileName = (new File(fullName)).getName();
        int dotIndex = fileName.lastIndexOf(46);
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }
}
