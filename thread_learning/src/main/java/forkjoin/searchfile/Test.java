package forkjoin.searchfile;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 18:32
 */
public class Test {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FolderProcessor system = new FolderProcessor("C:\\Windows","log");
        FolderProcessor apps = new FolderProcessor("C:\\Program Files","log");
        FolderProcessor documents = new FolderProcessor("D:\\Document","log");
        pool.execute(system);
        pool.execute(apps);
        pool.execute(documents);
        do {
            System.out.println("*******************************************");
            System.out.println("Main: Parallelism: " + pool.getParallelism());
            System.out.println("Main: Active threads: " + pool.getActiveThreadCount());
            System.out.println("Main: Task count: " + pool.getQueuedTaskCount());
            System.out.println("Main: Steal count: " + pool.getStealCount());
            System.out.println("********************************************");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!system.isDone()||!apps.isDone()||!documents.isDone());
        pool.shutdown();
        List<String> results = system.join();
        System.out.println("System:"+results.size());
        results = apps.join();
        System.out.println("Apps:"+results.size());
        results = documents.join();
        System.out.println("Document:"+results.size());
    }
}
