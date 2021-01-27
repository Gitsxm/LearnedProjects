package syn;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/16 23:22
 */
public class Company implements Runnable{
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            account.addAmount(new Double("100"));
        }
    }
}
