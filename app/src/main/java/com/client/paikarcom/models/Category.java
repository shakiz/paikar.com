package com.client.paikarcom.models;

public class Category {
    private int ImageResId;
    private String CategoryText;

    public Category(int imageResId, String categoryText) {
        ImageResId = imageResId;
        CategoryText = categoryText;
    }

    public Category() {
    }

    public int getImageResId() {
        return ImageResId;
    }

    public void setImageResId(int imageResId) {
        ImageResId = imageResId;
    }

    public String getCategoryText() {
        return CategoryText;
    }

    public void setCategoryText(String categoryText) {
        CategoryText = categoryText;
    }
}
