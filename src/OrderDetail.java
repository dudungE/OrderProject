class OrderDetail {
    private Product product;
    private int quantity;
    private double unitPrice;
    private double discountRate;

    public OrderDetail(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        this.discountRate = 0.1;
    }

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    // 소계 계산 메소드
    public double getSubtotal() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return "수주명세 [상품: " + product.getName() +
                ", 수량: " + quantity + ", 단가: " + unitPrice +
                ", 소계: " + getSubtotal() + "]";
    }
}