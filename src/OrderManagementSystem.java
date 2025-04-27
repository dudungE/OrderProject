import java.util.ArrayList;
import java.util.List;

public class OrderManagementSystem {
    private List<Customer> customers;
    private List<Product> products;
    private List<Warehouse> warehouses;
    private List<Inventory> inventories;
    private List<Order> orders;
    private List<Order> ordersFailed;


    /**
     * 고객
     * 상품 창고 재고
     * 성공한 주문, 시랲한 주문 클래스
     * <p>
     * 1. 재고량이 부족하면 "재고부족"으로 상품 주문 취소
     * 2. 특별회원의 경우 모든 상품에 대해 10% 할인율 적용
     */

    // Constructor
    public OrderManagementSystem() {
        customers = new ArrayList<>();
        products = new ArrayList<>();
        warehouses = new ArrayList<>();
        inventories = new ArrayList<>();
        orders = new ArrayList<>();
        ordersFailed = new ArrayList<>();
    }


    //==========================================================================================

    /**
     * add methods
     */

    // Customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    // Product
    public void addProduct(Product product) {
        products.add(product);
    }

    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductCode().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    // Warehouse
    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public Warehouse findWarehouseById(String warehouseId) {
        for (Warehouse warehouse : warehouses) {
            if (warehouse.getWarehouseId().equals(warehouseId)) {
                return warehouse;
            }
        }
        return null;
    }

    // Inventory
    public void addInventory(Inventory inventory) {
        inventories.add(inventory);
    }

    public Inventory findInventory(String productId, String warehouseId) {
        for (Inventory inventory : inventories) {
            if (inventory.getProduct().getProductCode().equals(productId) &&
                    inventory.getWarehouse().getWarehouseId().equals(warehouseId)) {
                return inventory;
            }
        }
        return null;
    }

    //==========================================================================================

    /**
     * 1. 재고량이 부족하면 "재고부족"으로 상품 주문 취소
     */


    // Order
    public void addOrder(Order order) {
        List<String> insufficientProducts = getInsufficientProducts(order);

        discountApply(order);

        if (insufficientProducts.isEmpty()) {
            fulfillOrder(order);
            orders.add(order);
            order.setStatus("처리 완료");
        } else {
            ordersFailed.add(order);
            order.setStatus("재고 부족: " + String.join(", ", insufficientProducts)); // 부족 상품 이름 표시
        }
    }

    // 수주 허가 프로세스
    private List<String> getInsufficientProducts(Order order) {
        List<String> insufficientProducts = new ArrayList<>();

        for (OrderDetail detail : order.getOrderDetails()) {
            Product product = detail.getProduct();
            int requiredQuantity = detail.getQuantity();

            int availableStock = 0;
            for (Inventory inventory : inventories) {
                if (inventory.getProduct().getProductCode().equals(product.getProductCode())) {
                    availableStock += inventory.getQuantity();
                }
            }

            if (availableStock < requiredQuantity) {
                insufficientProducts.add(product.getName()); // 상품 이름 추가
            }
        }

        return insufficientProducts; // 부족한 상품 리스트 리턴
    }

    private void fulfillOrder(Order order) {
        for (OrderDetail detail : order.getOrderDetails()) { // OrderDetail 객체 하나씩 불러오기
            Product product = detail.getProduct();
            int remainingQuantity = detail.getQuantity();

            // inventory 객체들 하나씩 돌며 필요한 재고량을 감소시키며 주문 실행
            for (Inventory inventory : inventories) {
                if (inventory.getProduct().getProductCode().equals(product.getProductCode())) {
                    int stock = inventory.getQuantity();

                    if (stock >= remainingQuantity) {
                        inventory.removeStock(remainingQuantity);
                        break;
                    } else {
                        inventory.removeStock(stock);
                        remainingQuantity -= stock;
                    }
                }
            }
        }
    }

    /**
     * 2. 특별회원의 경우 모든 상품에 대해 10% 할인율 적용
     */

    public void discountApply(Order order) {
        if (order.getCustomer() instanceof SpecialCustomer) {
            for (OrderDetail detail : order.getOrderDetails()) {
                detail.setUnitPrice(detail.getUnitPrice() * (1 - detail.getDiscountRate()));
            }
        }
    }

    // 실패한 수주 삭제 메서드

    public void deleteFailedOrder() {
        ordersFailed.clear();
    }


    // 시스템 정보 출력 메소드
    public void printSystemInfo() {
        line();
        System.out.println("\n===== 수주 관리 시스템 정보 =====");
        System.out.println("등록된 고객: " + customers.size() + "명");
        System.out.println("등록된 상품: " + products.size() + "개");
        System.out.println("등록된 창고: " + warehouses.size() + "개");
        System.out.println("등록된 재고: " + inventories.size() + "건");
        System.out.println("처리된 수주: " + orders.size() + "건");
        line();
    }


    public void printSystemInfoDetail() {
        line();
        System.out.println("=========== 수주 관리 시스템 상세 데이터 ===========");
        System.out.println(">>등록된 고객: " + customers.size() + "명");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i));
        }
        System.out.println();
        System.out.println(">>등록된 상품: " + products.size() + "개");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
        System.out.println();
        System.out.println(">>등록된 창고: " + warehouses.size() + "개");
        for (int i = 0; i < warehouses.size(); i++) {
            System.out.println(warehouses.get(i));
        }
        System.out.println();
        System.out.println(">>등록된 재고: " + inventories.size() + "건");
        for (int i = 0; i < inventories.size(); i++) {
            System.out.println(inventories.get(i));
        }
        System.out.println();
        System.out.println(">>처리된 수주: " + orders.size() + "건");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
        System.out.println(">>미처리 수주: " + ordersFailed.size() + "건");
        for (int i = 0; i < ordersFailed.size(); i++) {
            System.out.println(ordersFailed.get(i));
            line();
        }
    }


    public static void line() {
        System.out.println("=========================================================");
    }


}