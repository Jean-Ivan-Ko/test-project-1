public class Order {
    
    private String id;
    
    private String numberOrder;
    
    private String fio;
    
    private String address;
    
    private String phone;
    
    private String priority;
    
    public Order() {
    }
    
    public Order(String id, String numberOrder, String fio, String address, String phone, String priority) {
        this.id = id;
        this.numberOrder = numberOrder;
        this.fio = fio;
        this.address = address;
        this.phone = phone;
        this.priority = priority;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNumberOrder() {
        return numberOrder;
    }
    
    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }
    
    public String getFio() {
        return fio;
    }
    
    public void setFio(String fio) {
        this.fio = fio;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", numberOrder='" + numberOrder + '\'' +
                ", fio='" + fio + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
