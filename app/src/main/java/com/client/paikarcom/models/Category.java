package com.client.paikarcom.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ImageResId);
        dest.writeString(CategoryText);
    }

    protected Category(Parcel in) {
        ImageResId = in.readInt();
        CategoryText = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
