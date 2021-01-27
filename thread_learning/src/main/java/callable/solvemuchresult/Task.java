package callable.solvemuchresult;

import java.util.concurrent.Callable;

/**
 * TODO 运行多个任务病处理所有结果-任务
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/7 19:16
 */
public class Task implements Callable<Result> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        System.out.println(name + " Starting...");
        int duration = (int) (Math.random() * 10);
        System.out.println(name + " Waiting " + duration + " seconds for results.");
        try{
            Thread.sleep(duration * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        int value = 0;
        for (int i=0;i<5;i++){
            value += Math.random()*100;
        }
        Result result = new Result();
        result.setName(name);
        result.setValue(value);
        System.out.println(name+" End...");
        return result;
    }
}
