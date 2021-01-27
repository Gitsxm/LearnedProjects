package customerpoolexecutor.myforkjoincustomtask;

import java.util.Arrays;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 23:39
 */
public class Task extends MyWorkerTask{
    private int[] arr;
    private int start,end;

    public Task(String name) {
        super(name);
    }

    public Task(String name, int[] arr, int start, int end) {
        super(name);
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public void compute() {
        if (end-start>100){
            int mid = (start+end)/2;
            Task task1 = new Task(getName()+"1",arr,start,mid);
            Task task2 = new Task(getName()+"2",arr,mid,end);
            invokeAll(task1,task2);
        }else {
            Arrays.stream(arr).map(e->e++);
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
