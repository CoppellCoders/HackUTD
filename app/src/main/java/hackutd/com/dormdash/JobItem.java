package hackutd.com.dormdash;

import java.io.Serializable;

public class JobItem implements Serializable {
    public String title;
    public String img;
    public String desc;
    public double rating;
    public double moneys;
    public double distance;
    String loc;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public JobItem(String title, String img, String desc, double rating, double moneys, double distance, String loc) {
        this.title = title;
        this.img = img;
        this.desc = desc;
        this.rating = rating;
        this.moneys = moneys;
        this.distance = distance;
        this.loc = loc;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getMoneys() {
        return moneys;
    }

    public void setMoneys(double moneys) {
        this.moneys = moneys;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "JobItem{" +
                "title='" + title + '\'' +
                ", img=" + img +
                ", desc='" + desc + '\'' +
                ", rating=" + rating +
                ", moneys=" + moneys +
                ", distance=" + distance +
                '}';
    }
}
