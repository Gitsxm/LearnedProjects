package syn;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/16 23:25
 */
public class Test {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(new Double(1000));
        Company company = new Company(account);
        Bank bank = new Bank(account);
        Thread add = new Thread(company);
        Thread sub = new Thread(bank);
        System.out.println("Account init balance:"+account.getBalance());
        add.start();
        sub.start();
        try {
            add.join();
            sub.join();
            System.out.println("Account final balance:"+account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
