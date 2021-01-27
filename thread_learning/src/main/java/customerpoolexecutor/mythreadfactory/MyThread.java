package customerpoolexecutor.mythreadfactory;

import java.util.Date;

/**
 * TODO 实现 ThreadFactory 接口生成定制线程
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 9:11
 */
public class MyThread extends Thread{
    private Date creationDate;
    private Date startDate;
    private Date finishDate;

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreationDate();
    }

    public void setCreationDate() {
        this.creationDate = new Date();
    }

    public void setStartDate() {
        this.startDate = new Date();
    }

    public void setFinishDate() {
        this.finishDate = new Date();
    }

    @Override
    public void run() {
        setStartDate();
        super.run();
        setFinishDate();
    }

    public long getExecutionTime(){
        return finishDate.getTime()-startDate.getTime();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getName());
        buffer.append(": ");
        buffer.append(" Creation Date :");
        buffer.append(creationDate);
        buffer.append(" : Running time :");
        buffer.append(getExecutionTime());
        buffer.append(" milliseconds .");
        return buffer.toString();
    }
}

