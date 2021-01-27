package collection.atomicvariable;

import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO 使用原子变量 账户
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/22 11:01
 */
public class Account {
    private AtomicLong balance;

    public Account() {
        balance = new AtomicLong();
    }

    public AtomicLong getBalance() {
        return balance;
    }

    public void setBalance(AtomicLong balance) {
        this.balance = balance;
    }

    public void addAmount(long amount){
        balance.getAndAdd(amount);
    }

    public void subAmount(long amount){
        balance.getAndAdd(-amount);
    }
}
