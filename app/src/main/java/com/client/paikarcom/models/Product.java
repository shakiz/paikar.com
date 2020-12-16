package com.client.paikarcom.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int ProductId;
    private String ProductTitle;
    private String ProductSmallDescription;
    private String ProductDescription;
    private double ProductPrice;
    private double ProductOldPrice;
    private int[] ProductImages;
    private boolean ProductStockAvailability;
    private double AmountInKg;

    public Product(int productId, String productTitle, String productSmallDescription, String productDescription, double productPrice, double productOldPrice,
                   int[] productImages, boolean productStockAvailability, double amountInKg) {
        ProductId = productId;
        ProductTitle = productTitle;
        ProductSmallDescription = productSmallDescription;
        ProductDescription = productDescription;
        ProductPrice = productPrice;
        ProductOldPrice = productOldPrice;
        ProductImages = productImages;
        ProductStockAvailability = productStockAvailability;
        AmountInKg = amountInKg;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductTitle() {
        return ProductTitle;
    }

    public void setProductTitle(String productTitle) {
        ProductTitle = productTitle;
    }

    public String getProductSmallDescription() {
        return ProductSmallDescription;
    }

    public void setProductSmallDescription(String productSmallDescription) {
        ProductSmallDescription = productSmallDescription;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public double getProductOldPrice() {
        return ProductOldPrice;
    }

    public void setProductOldPrice(double productOldPrice) {
        ProductOldPrice = productOldPrice;
    }

    public int[] getProductImages() {
        return ProductImages;
    }

    public void setProductImages(int[] productImages) {
        ProductImages = productImages;
    }

    public boolean isProductStockAvailability() {
        return ProductStockAvailability;
    }

    public void setProductStockAvailability(boolean productStockAvailability) {
        ProductStockAvailability = productStockAvailability;
    }

    public double getAmountInKg() {
        return AmountInKg;
    }

    public void setAmountInKg(double amountInKg) {
        AmountInKg = amountInKg;
    }

    protected Product(Parcel in) {
        ProductId = in.readInt();
        ProductTitle = in.readString();
        ProductSmallDescription = in.readString();
        ProductDescription = in.readString();
        ProductPrice = in.readDouble();
        ProductOldPrice = in.readDouble();
        ProductImages = in.createIntArray();
        ProductStockAvailability = in.readByte() != 0;
        AmountInKg = in.readDouble();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ProductId);
        dest.writeString(ProductTitle);
        dest.writeString(ProductSmallDescription);
        dest.writeString(ProductDescription);
        dest.writeDouble(ProductPrice);
        dest.writeDouble(ProductOldPrice);
        dest.writeIntArray(ProductImages);
        dest.writeByte((byte) (ProductStockAvailability ? 1 : 0));
        dest.writeDouble(AmountInKg);
    }
}
