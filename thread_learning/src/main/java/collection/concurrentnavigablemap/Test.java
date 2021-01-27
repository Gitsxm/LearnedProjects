package collection.concurrentnavigablemap;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.IntStream;

/**
 * TODO 线程安全可遍历映射 取第一个entry 最后一个 entry 以及截取子 entry
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/20 17:15
 */
public class Test {
    public static void main(String[] args) {
        ConcurrentNavigableMap<String,Contact> map = new ConcurrentSkipListMap<>();
        Thread[] threads = new Thread[25];
        int counter = 0;
        for(char i='A';i<'Z';i++){
            Task task = new Task(map,String.valueOf(i));
            threads[counter] = new Thread(task);
            threads[counter].start();
            counter++;
        }
        IntStream.range(0,25).forEach(e->{
            try {
                threads[e].join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        System.out.println("Main: Size of the map:"+map.size());
        Map.Entry<String,Contact> element;
        Contact contact;
        element = map.firstEntry();
        contact = element.getValue();
        System.out.println("Main: First entry is :"+contact.getName()+":"+contact.getPhone());

        element = map.lastEntry();
        contact = element.getValue();
        System.out.println("Main: Last entry is :"+contact.getName()+":"+contact.getPhone());

        System.out.println("Main: SubMap from A1996 to B1002");
        ConcurrentNavigableMap subMap = map.subMap("A1996","B1002");
        do {
            element = subMap.pollFirstEntry();
            if (null != element){
                contact = element.getValue();
                System.out.println(contact.getName()+":"+contact.getPhone());
            }
        }while (null != element);
    }
}
