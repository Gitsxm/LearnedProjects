package customerpoolexecutor.myforkjoincustomtask;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

/**
 * TODO 定制 fork join 里的任务
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 23:34
 */
public abstract class MyWorkerTask extends ForkJoinTask<Void> {

    private String name;

    public MyWorkerTask(String name) {
        this.name = name;
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        Date startDate = new Date();
        compute();
        Date endDate = new Date();
        long diff = endDate.getTime()-startDate.getTime();
        System.out.println("MyWorkerTask : complete in : "+diff);
        return true;
    }

    public abstract void compute();

    public String getName() {
        return name;
    }
}
