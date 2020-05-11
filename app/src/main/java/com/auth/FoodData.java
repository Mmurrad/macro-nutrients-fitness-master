package com.auth;

public class FoodData {
    String name;
    String weight;
    String calorie;

    public FoodData() {
    }

    public FoodData(String name, String weight, String calorie) {
        this.name = name;
        this.weight = weight;
        this.calorie = calorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }
}
