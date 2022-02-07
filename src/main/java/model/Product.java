package model;

/**
 * CLASS THAT CONTAINS THE DETAILS REGARDING A PRODUCT
 */
public class  Product {

    /**
     * Product's attributes
     */
    private Integer productId;
    private String productName;
    private Integer productQuantity;
    private Integer productPrice;

    public Product() {

    }

    public Product(Integer productId, String productName, Integer productQuantity, Integer productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public Product(String productName, Integer productQuantity, Integer productPrice) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString(){
        return "Product [ id = " + productId + " name " + productName + " quantity " + productQuantity + " price " + productPrice + " ]";
    }
}
