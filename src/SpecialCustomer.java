public class SpecialCustomer extends Customer {
    private int memberNum;

    public SpecialCustomer(String customerId, String name, String address, String phone, int memberNum) {
        super(customerId, name, address, phone);
        this.memberNum = memberNum;
    }

    // Getters and Setters
    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }


    @Override
    public String toString() {
        return "고객 정보 [ID: " + getCustomerId() + ", 이름: " + getName() +
                ", 주소: " + getAddress() + ", 연락처: " + getPhone() +
                ", 특별회원번호: " + getMemberNum() + "]";
    }
}
