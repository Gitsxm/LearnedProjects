package collection.atomicvariable;

import java.util.stream.IntStream;

/**
 * TODO 公司
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/22 11:04
 */
public class Company implements Runnable{
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        IntStream.range(0,10).forEach(e->{
            account.addAmount(1000);
        });
    }
}
