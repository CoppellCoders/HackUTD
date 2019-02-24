package hackutd.com.dormdash;

import java.io.Serializable;

public class Menu implements Serializable{
    String name;
    String priceCalories;
    String description;
    public Menu(String name, String priceCalories, String description){
        this.name = name;
        this.priceCalories = priceCalories;
        this.description = description;
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
