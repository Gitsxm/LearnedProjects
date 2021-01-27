package callable.solveoneresult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO 运行多个任务并处理第一个结果
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/4 23:17
 */
public class Test {
    public static void main(String[] args) {
        String user = "test";
        String pwd = "test";
        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator baseValidator = new UserValidator("DataBase");
        TaskValidator ldapTask = new TaskValidator(ldapValidator,user,pwd);
        TaskValidator dbTask = new TaskValidator(baseValidator,user,pwd);
        List<TaskValidator> taskValidatorList = new ArrayList<>();
        taskValidatorList.add(ldapTask);
        taskValidatorList.add(dbTask);

        ExecutorService executorService = Executors.newCachedThreadPool();
        String result;
        try {
           result = executorService.invokeAny(taskValidatorList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("Main: End.....");

    }
}
