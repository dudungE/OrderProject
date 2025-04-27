import java.time.LocalDate;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderManagementSystem system = new OrderManagementSystem();

        // 기본 데이터 initalize
//        dataInit(system);

        boolean flag = true;
        while (flag) {
            System.out.println("1: 고객정보 입력 2: 상품정보 입력 3: 수주등록 4: 미처리수주 취소 5: 데이터 조회 5: 0: 시스템 종료 ");
            switch (sc.nextInt()) {
                case 1 -> {
                    addCustomerData(system);
                }
                case 2 -> {
                    addProductData(system);
                }
                case 3 -> {
                    addOrderData(system);
                }
                case 4 -> {
                    system.deleteFailedOrder();
                }
                case 5 -> {
                    system.printSystemInfoDetail();
                }
                case 0 -> {
                    System.exit(0);
                }
                default -> System.out.println("잘못 입력하셨습니다.");
            } // swtich end
        } // while end


    }


    public static void addCustomerData(OrderManagementSystem system) {
        // 고객
        Customer customer1 = new Customer("C001", "Customer1", "Seoul", "010-1234-5678");
        Customer customer2 = new Customer("C002", "Customer2", "Busan", "010-5678-1234");
        SpecialCustomer customer3 = new SpecialCustomer("SC001", "SpecialCustomer1", "Seoul", "010-1111-2222", 1);
        SpecialCustomer customer4 = new SpecialCustomer("SC002", "SpecialCustomer2", "Busan", "010-3333-4444", 2);

        system.addCustomer(customer1);
        system.addCustomer(customer2);
        system.addCustomer(customer3);
        system.addCustomer(customer4);
    }

    public static void addProductData(OrderManagementSystem system) {// 상품
        Product product1 = new Product("code001", "product1", 1200);
        Product product2 = new Product("code002", "product2", 8000);
        Product product3 = new Product("code003", "product3", 3500);

        system.addProduct(product1);
        system.addProduct(product2);
        system.addProduct(product3);


        // 창고
        Warehouse warehouse1 = new Warehouse("W001", "물류센터1", "창고관리자1");
        Warehouse warehouse2 = new Warehouse("W002", "물류센터2", "창고관리자2");
        system.addWarehouse(warehouse1);
        system.addWarehouse(warehouse2);

        // 재고
        Inventory inventory1 = new Inventory(product1, warehouse1, 100);
        Inventory inventory2 = new Inventory(product2, warehouse1, 50);
        Inventory inventory3 = new Inventory(product3, warehouse2, 80);

        system.addInventory(inventory1);
        system.addInventory(inventory2);
        system.addInventory(inventory3);
    }


    public static void addOrderData(OrderManagementSystem system) {
// 일반고객 수주 생성 및 등록
        // 고객 불러오기
        Customer customer1 = system.findCustomerById("C001");
        Customer customer2 = system.findCustomerById("C002");
        SpecialCustomer customer3 = (SpecialCustomer) system.findCustomerById("SC001");
        SpecialCustomer customer4 = (SpecialCustomer) system.findCustomerById("SC002");

        // 상품 불러오기
        Product product1 = system.findProductById("code001");
        Product product2 = system.findProductById("code002");
        Product product3 = system.findProductById("code003");

        Order order1 = new Order("O001", customer1, LocalDate.now());
        order1.registerOrder(new OrderDetail(product1, 50));
        order1.registerOrder(new OrderDetail(product3, 30));

        Order order2 = new Order("O002", customer2, LocalDate.now());
        order2.registerOrder(new OrderDetail(product1, 40));
        order2.registerOrder(new OrderDetail(product2, 16));


        // 특별회원 수주 생성 및 등록
        Order order3 = new Order("O003", customer3, LocalDate.now());
        order3.registerOrder(new OrderDetail(product1, 15));
        order3.registerOrder(new OrderDetail(product2, 55));
        order3.registerOrder(new OrderDetail(product3, 10));

        Order order4 = new Order("O004", customer4, LocalDate.now());
        order4.registerOrder(new OrderDetail(product2, 30));
        order4.registerOrder(new OrderDetail(product3, 25));

        // 시스템에 수주 추가
        system.addOrder(order1);
        system.addOrder(order2);
        system.addOrder(order3);
        system.addOrder(order4);
    }

    public void deleteFailedOrder(OrderManagementSystem system) {
        system.deleteFailedOrder();
    }

}
