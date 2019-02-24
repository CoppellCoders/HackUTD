package hackutd.com.dormdash;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Menu implements Serializable{
    String name;
    String priceCalories;
    String description;
    int count;
    public Menu(String name, String priceCalories, String description){
        this.name = name;
        this.priceCalories = priceCalories;
        this.description = description;
        this.count = 0;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceCalories() {
        return priceCalories;
    }

    public void setPriceCalories(String priceCalories) {
        this.priceCalories = priceCalories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
