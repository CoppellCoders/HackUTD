package hackutd.com.dormdash;
import java.io.Serializable;
class Restraunts implements Serializable{
    String rName;
    String imageUrl;
    String rating;
    String rType;
    String distance;
    public Restraunts(String rName, String imageUrl, String rating, String rType, String distance){
        this.rName = rName;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.rType = rType;
        this.distance = distance;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
