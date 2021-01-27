package syn;

/**
 * 银行类
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/16 23:19
 */
public class Bank implements Runnable{
    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    /**
     * 扣钱方法
     */
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            this.account.subAmount(new Double("100"));
        }
    }
}
