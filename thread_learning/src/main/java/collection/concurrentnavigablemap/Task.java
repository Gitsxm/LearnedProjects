package collection.concurrentnavigablemap;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/20 17:11
 */
public class Task implements Runnable{
    private ConcurrentNavigableMap<String,Contact> map;
    private String id;

    public Task(ConcurrentNavigableMap<String, Contact> map, String id) {
        this.map = map;
        this.id = id;
    }

    @Override
    public void run() {
        IntStream.range(0,1000).forEach(e->{
            Contact contact = new Contact(id,String.valueOf(e+1000));
            map.put(id+contact.getPhone(),contact);
        });
    }
}
