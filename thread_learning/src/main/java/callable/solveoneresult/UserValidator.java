package callable.solveoneresult;

import java.util.Random;

/**
 * TODO 运行多个任务并处理第一个结果
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/30 23:34
 */
public class UserValidator {
    private String name;

    public UserValidator(String name) {
        this.name = name;
    }

    /**
     * 模拟验证结果
     * @param name
     * @param password
     * @return
     */
    public boolean validate(String name,String password){
        Random random = new Random();
        int duration = (int) (Math.random()*10);
        System.out.println("UserValidator:"+name+" validate during "+duration+" sec...");
        try {
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return random.nextBoolean();
    }

    public String getName() {
        return name;
    }
}
