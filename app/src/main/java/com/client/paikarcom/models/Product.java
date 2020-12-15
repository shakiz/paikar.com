package com.client.paikarcom.models;

public class Product {
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
}
