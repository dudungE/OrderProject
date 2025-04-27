import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Order {
    private String orderId;
    private LocalDate orderDate;
    private Customer customer;
    private LocalDate deliveryDate;
    private String status;
    private List<OrderDetail> orderDetails;

    public Order(String orderId, Customer customer, LocalDate orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = orderDate;
        this.status = "접수";
        this.orderDetails = new ArrayList<>();
        this.deliveryDate = orderDate.plusDays(5);
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(int deliveryTime) {
        this.deliveryDate = orderDate.plusDays(deliveryTime);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    // 수주 등록 메서드
    public void registerOrder(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
    }

    // 수주날짜 취득 메서드
    public String getOrderDateInfo() {
        return "수주 번호 " + orderId + "의 주문일자는 " + orderDate + "입니다.";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("수주 정보 [ID: ").append(orderId)
                .append(", 주문일: ").append(orderDate)
                .append(", 고객: ").append(customer != null ? customer.getName() : "잘못 입력된 고객번호");
//                .append(", 고객: ").append(customer.getName());

        if (customer instanceof SpecialCustomer) {
            sb.append("[특별고객]");
        } else {
            sb.append("[일반고객");

        }

        if (deliveryDate != null) {
            sb.append(", 배송예정일: ").append(deliveryDate);
        }

        sb.append(", 상태: ").append(status)
                .append("]\n");


        // 수주 명세
        for (OrderDetail detail : orderDetails) {
            sb.append("  - ").append(detail.toString()).append("\n");
        }

        return sb.toString();
    }
}