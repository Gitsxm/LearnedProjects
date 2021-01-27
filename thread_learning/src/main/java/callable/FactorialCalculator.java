package callable;

import java.util.concurrent.Callable;

/**
 * TODO Callable 实例 返回结果
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/29 23:45
 */
public class FactorialCalculator implements Callable<Integer> {
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if (number == 0 || number == 1) {
            result = 1;
        } else {
            for (int i = 2; i < number; i++) {
                result *= i;
                Thread.sleep(20);
            }
        }
        System.out.println(Thread.currentThread().getName()+" : "+result);
        return result;
    }
}
