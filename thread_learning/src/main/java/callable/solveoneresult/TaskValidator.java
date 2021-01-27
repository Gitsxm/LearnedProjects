package callable.solveoneresult;

import java.util.concurrent.Callable;

/**
 * TODO 运行多个任务并处理第一个结果
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/4 23:12
 */
public class TaskValidator implements Callable<String> {
    private UserValidator validator;
    private String user;
    private String pwd;

    public TaskValidator(UserValidator validator, String user, String pwd) {
        this.validator = validator;
        this.user = user;
        this.pwd = pwd;
    }

    @Override
    public String call() throws Exception {
        if (!validator.validate(user,pwd)){
            System.out.println(validator.getName()+": The user has not been found...");
            throw new Exception("Error validate user");
        }
        System.out.println(validator.getName()+": The user has been found...");
        return validator.getName();
    }
}
