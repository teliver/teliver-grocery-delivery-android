package com.telivergrocerydriver;


public class Model {

    private String name,approxPrice,finalPrice;

    private int image;

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Model(int image, String name, String approxPrice, String finalPrice) {
        this.name = name;
        this.approxPrice = approxPrice;
        this.finalPrice = finalPrice;
        this.image = image;

    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApproxPrice() {
        return approxPrice;
    }

    public void setApproxPrice(String approxPrice) {
        this.approxPrice = approxPrice;
    }
}
