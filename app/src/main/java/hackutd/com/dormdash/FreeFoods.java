package hackutd.com.dormdash;

import java.io.Serializable;

public class FreeFoods implements Serializable {
    String title, location, time, desc;

    public FreeFoods(String title, String location, String time, String desc) {
        this.title = title;
        this.location = location;
        this.time = time;
        this.desc = desc;
    }
    public FreeFoods() {

    }
    @Override
    public String toString() {
        return "FreeFoods{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
