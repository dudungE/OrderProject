public class Warehouse {
    private String warehouseId;
    private String location;
    private String managerName;

    public Warehouse(String warehouseId, String location, String managerName) {
        this.warehouseId = warehouseId;
        this.location = location;
        this.managerName = managerName;
    }

    // Getters and Setters
    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return "창고 정보 [ID: " + warehouseId + ", 위치: " + location +
                ", 관리자: " + managerName + "]";
    }
}
