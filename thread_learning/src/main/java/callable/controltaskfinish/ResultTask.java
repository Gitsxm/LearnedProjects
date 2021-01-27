package callable.controltaskfinish;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/8 18:55
 */
public class ResultTask extends FutureTask<String> {
    private String name;


    public ResultTask(Callable<String> callable) {
        super(callable);
        this.name = ((ExecutableTask)callable).getName();
    }

    @Override
    protected void done() {
        if (isCancelled()){
            System.out.println(name+": Has been canceled...");
        }else {
            System.out.println(name+": Has finished");
        }
    }
}
