package callable.dividetask;

import java.util.concurrent.CompletionService;

/**
 * TODO 模拟请求获取报告
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/9 23:23
 */
public class ReportRequest implements Runnable {

    private String name;
    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenertor genertor = new ReportGenertor(name,"Report");
        service.submit(genertor);
    }
}
