package callable.dividetask;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * TODO 获取 generator 结果
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/9 23:26
 */
public class ReportProcessor implements Runnable {
    private CompletionService<String> service;
    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        end = false;
    }

    @Override
    public void run() {
        while (!end){
            try {
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                if (null!=result){
                    System.out.println("ReportReceiver:report recceived..."+result.get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ReportSender:End...");
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
