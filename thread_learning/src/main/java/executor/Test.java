package executor;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/29 23:06
 */
public class Test {
    public static void main(String[] args) {
        Server server = new Server();
        for (int i=0;i<100;i++){
            Task task = new Task("Task "+i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
