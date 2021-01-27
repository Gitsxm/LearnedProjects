package collection.concurrentnavigablemap;

/**
 * TODO 线程安全可遍历映射 ConcurrentNavigableMap
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/20 17:03
 */
public class Contact {
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
