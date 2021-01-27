package callable.dividetask;

import java.util.concurrent.Callable;

/**
 * TODO 在执行器中分离任务的启动和结果的处理
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/9 23:17
 */
public class ReportGenertor implements Callable<String> {
    private String sender;
    private String title;

    public ReportGenertor(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() throws Exception {
        long duration = (long) (Math.random()*10);
        try {
            System.out.println(sender+"_"+title+": ReprotGenertor: generating a report during "+ duration+" seconds...");
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String ret = sender+":"+title;
        return ret;
    }
}
