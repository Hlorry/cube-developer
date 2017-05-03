package cn.getcube.develop.entity;

/**
 * Created by Rainbow on 16-6-3.
 */
public class LoginLog {

    /**
     * 号码
     */
    private String cube;

    /**
     * 显示名称
     */
    private String display_name;

    /**
     * 第一次登录时间
     */
    private String first;

    /**
     * 最近一次登录时间
     */
    private String latest;

    /**
     * 累计登录次数
     */
    private String times;

    public String getCube() {
        return cube;
    }

    public void setCube(String cube) {
        this.cube = cube;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "cube='" + cube + '\'' +
                ", display_name='" + display_name + '\'' +
                ", first='" + first + '\'' +
                ", latest='" + latest + '\'' +
                ", times='" + times + '\'' +
                '}';
    }
}
