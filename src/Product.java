public class Product {
    private String productCode;
    private String name;
    private double price;

    public Product(String productCode, String name, double price) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productId) {
        this.productCode = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "상품 정보 [ID: " + productCode + ", 이름: " + name +
                ", 가격: " + price + "]";
    }
}
