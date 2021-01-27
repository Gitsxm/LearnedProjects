package group;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/16 22:14
 */
public class Test {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        Result result = new Result();
        SearchTask task = new SearchTask(result);
        for (int i=0;i<5;i++){
            Thread thread = new Thread(threadGroup,task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Number of thread:"+threadGroup.activeCount());
            System.out.println("Info of group:");
            threadGroup.list();
            Thread[] threads = new Thread[threadGroup.activeCount()];
            threadGroup.enumerate(threads);
            for (Thread thread1:threads){
                System.out.println("Thread "+thread1.getName()+":"+thread1.getState());
            }
        }

    }
}
