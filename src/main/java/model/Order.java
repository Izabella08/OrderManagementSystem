package model;

/**
 * CLASS THAT CONTAINS THE DETAILS REGARDING AN ORDER
 */
public class Order {

    /**
     * Order's attributes
     */
    private Integer orderId;
    private Integer quantity;
    private String clientName;
    private String productName;
    private Integer price;

    public Order() {
    }

    public Order(Integer orderId, Integer quantity, String clientName, String productName, Integer price) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.clientName = clientName;
        this.productName = productName;
        this.price = price;
    }

    public Order(Integer orderId, Integer price) {
        this.orderId = orderId;
        this.price = price;
    }

    public Order(Integer quantity, String clientName, String productName) {
        this.quantity = quantity;
        this.clientName = clientName;
        this.productName = productName;
    }

    public Order(Integer quantity, String clientName, String productName, Integer price) {
        this.quantity = quantity;
        this.clientName = clientName;
        this.productName = productName;
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Order [ id " + orderId + " quantity " + quantity + " client_name " + clientName + " product_name " + productName + " ]";
    }
}
