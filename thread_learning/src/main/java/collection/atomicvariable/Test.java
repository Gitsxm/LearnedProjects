package collection.atomicvariable;

import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/22 11:06
 */
public class Test {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(new AtomicLong(10000));
        Company company = new Company(account);
        Thread cthread = new Thread(company);
        cthread.start();
        Bank bank = new Bank(account);
        Thread bthread = new Thread(bank);
        bthread.start();
        System.out.println("Account: init :"+account.getBalance());
        try {
            cthread.join();
            bthread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Account: end :"+account.getBalance());
    }
}
