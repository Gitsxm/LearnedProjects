package callable.dividetask;

import java.util.concurrent.*;

/**
 * TODO 请求与结果分开的示例 两个请求创建两个返回结果，通过sendthread 来判断是否请求线程是否接收和结束
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/9 23:32
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> service = new ExecutorCompletionService<>(executorService);
        ReportRequest faceRequest = new ReportRequest("Face",service);
        ReportRequest onlineRequest = new ReportRequest("Online",service);
        Thread faceThread = new Thread(faceRequest);
        Thread onlineThread = new Thread(onlineRequest);
        ReportProcessor processor = new ReportProcessor(service);
        Thread sendThread = new Thread(processor);
        System.out.println("Main: startting threads.....");
        faceThread.start();
        onlineThread.start();
        sendThread.start();
        try {
            System.out.println("Main:Waiting for the report generators.");
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main:shutdown executor....");
        executorService.shutdown();
        try {
            executorService.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processor.setEnd(true);
        System.out.println("Main:end...");
    }
}
