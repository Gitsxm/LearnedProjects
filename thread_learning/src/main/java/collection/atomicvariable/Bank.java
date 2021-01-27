package collection.atomicvariable;

import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/22 11:05
 */
public class Bank implements Runnable{
    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        IntStream.range(0,10).forEach(e->{
            account.subAmount(1000);
        });
    }
}
