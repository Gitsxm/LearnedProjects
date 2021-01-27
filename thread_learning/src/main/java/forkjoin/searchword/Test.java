package forkjoin.searchword;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * TODO 合并任务结果-一百行每行一千的矩阵先对每行查询合并再合并整个doc
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 15:16
 */
public class Test {
    public static void main(String[] args) {
        DocumentMock mock = new DocumentMock();
        String[][] document = mock.generateDocument(100, 1000, "the");
        DocumentTask documentTask = new DocumentTask(document, 0, 100, "the");
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(documentTask);
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
        } while (!documentTask.isDone());
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Main: The word appears " + documentTask.get() + " in the document");
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
