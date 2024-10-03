import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {
    
    private final static String MAX = "MAX";
    private final static String MIDDLE = "MIDDLE";
    private final static String LOW = "LOW";
    
    private final static Comparator<Order> orderComparator = (o1, o2) -> {
        
        String priority1 = o1.getPriority();
        String priority2 = o2.getPriority();
        
        if (priority1.compareTo(priority2) == 0) {
            return 0;
        }
        
        if (MAX.equals(priority1)) {
            return 1;
        }
        
        if (MAX.equals(priority2)) {
            return -1;
        }
        
        if (LOW.equals(priority1)) {
            return -1;
        }
        
        if (LOW.equals(priority2)) {
            return 1;
        }
        
        return 1;
    };
    
    public static void main(String[] args) {
        
        List<String> ordersFile;
        try {
            ordersFile = Files.readAllLines(Path.of("orders.txt"));
        } catch (Exception e) {
            System.err.println("Ошибка чтения с файла!");
            return;
        }
        
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < ordersFile.size(); i++) {
            
            String[] orderItem = ordersFile.get(i).split(";");
            
            Order order = new Order();
            order.setId(orderItem[0]);
            order.setNumberOrder(orderItem[1]);
            order.setFio(orderItem[2]);
            order.setAddress(orderItem[3]);
            order.setPhone(orderItem[4]);
            order.setPriority(orderItem[5]);
            
            orders.add(order);
        }
        
        List<Order> badAddress = new ArrayList<>();
        List<Order> badPhones = new ArrayList<>();
        List<Order> badPriority = new ArrayList<>();
        
        for (int i = 0; i < orders.size(); i++) {
            
            Order order = orders.get(i);
            //"Россия. Ленинградская область. Санкт-Петербург. набережная реки Фонтанки
            if (!orders.get(i).getAddress().matches("(.+\\..+){3}")) {
                badAddress.add(order);
                System.out.println("Bad address by order:" + order.toString());
            }
            
            if (!orders.get(i).getPhone().matches("\\+\\d-\\d{3}-\\d{3}-\\d{2}-\\d{2}")) {
                badPhones.add(order);
                System.out.println("Bad phone by order:" + order.toString());
            }
            
            if (!orders.get(i).getPriority().matches("MAX|MIDDLE|LOW")) {
                badPriority.add(order);
                System.out.println("Bad priority by order:" + order.toString());
            }
        }
        
        orders.removeAll(badAddress);
        orders.removeAll(badPhones);
        orders.removeAll(badPriority);
        
        List<Order> orders1 = orders.stream().sorted(Comparator.comparing(Order::getAddress)).sorted(orderComparator).toList();
        
        Iterable<String> iterableListGoodOrders = orders1.stream().map(it ->
                it.getId() + ";" + it.getNumberOrder() + ";" + it.getFio() + ";" + it.getAddress() + ";" + it.getPhone() + ";" + it.getPriority()
        ).toList();
        
        System.out.println("Сохраняем успешно провалидированные записи ... ");
        try {
            Files.write(Path.of("orders-good.txt"), iterableListGoodOrders);
        } catch (Exception e) {
            System.err.println("Ошибка сохранения успешных данных в файл!");
            return;
        }
        
        Iterable<String> iterableListBadAddressOrders = badAddress.stream().map(it ->
                it.getId() + ";" + it.getNumberOrder() + ";" + it.getFio() + ";" + it.getAddress() + ";" + it.getPhone() + ";" + it.getPriority()
        ).toList();
        
        System.out.println("Сохраняем плохой адресс ... ");
        try {
            Files.write(Path.of("bad_address_non_valid_orders.txt"), iterableListBadAddressOrders);
        } catch (Exception e) {
            System.err.println("Ошибка сохранения плохих адрессов в файл!");
            return;
        }
        
        Iterable<String> iterableListBadPhoneOrders = badPhones.stream().map(it ->
                it.getId() + ";" + it.getNumberOrder() + ";" + it.getFio() + ";" + it.getAddress() + ";" + it.getPhone() + ";" + it.getPriority()
        ).toList();
        
        System.out.println("Сохраняем плохой телефон ... ");
        try {
            Files.write(Path.of("bad_phone_non_valid_orders.txt"), iterableListBadPhoneOrders);
        } catch (Exception e) {
            System.err.println("Ошибка сохранения плохих телефонов в файл!");
            return;
        }
        
        
        Iterable<String> iterableListBadPriorityOrders = badPriority.stream().map(it ->
                it.getId() + ";" + it.getNumberOrder() + ";" + it.getFio() + ";" + it.getAddress() + ";" + it.getPhone() + ";" + it.getPriority()
        ).toList();
        
        System.out.println("Сохраняем плохой приоритет ... ");
        try {
            Files.write(Path.of("bad_priority_non_valid_orders.txt"), iterableListBadPriorityOrders);
        } catch (Exception e) {
            System.err.println("Ошибка сохранения плохих приоритетов в файл!");
            return;
        }
        
        System.out.println("Successful!");
    }
    
}
