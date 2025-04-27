public class Inventory {
    private Product product;
    private Warehouse warehouse;
    private int quantity;

    public Inventory(Product product, Warehouse warehouse, int quantity) {
        this.product = product;
        this.warehouse = warehouse;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // 재고 증가 메소드
    public void addStock(int amount) {
        this.quantity += amount;
    }

    // 재고 감소 메소드
    public boolean removeStock(int amount) {
        if (quantity >= amount) {
            this.quantity -= amount;
            return true;
        } else {
            return false; // 재고 부족
        }
    }

    @Override
    public String toString() {
        return "재고 정보 [상품: " + product.getName() + ", 창고: " + warehouse.getLocation() +
                ", 수량: " + quantity + "]";
    }
}
