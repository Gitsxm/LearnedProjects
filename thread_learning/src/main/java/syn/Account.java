package syn;

import lombok.Data;

/**
 * 账户类  synchronized 创建临界区保证数据一致。
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/16 23:15
 */
@Data
public class Account {
    //余额
    private Double balance;

    /**
     * 增加钱
     * @param amount
     */
    public synchronized void addAmount(Double amount){
        //使用中间变量创造了余额不一致的可能。
        Double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp+=amount;
        balance=tmp;
    }

    /**
     * 扣去
     * @param amount
     */
    public synchronized void subAmount(Double amount){
        Double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp-=amount;
        balance=tmp;
    }
}
