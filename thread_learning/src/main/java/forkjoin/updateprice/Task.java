package forkjoin.updateprice;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * TODO 使用 fork/join 更新商品价格示例-更新任务 以 10 为分割点
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 11:32
 */
public class Task extends RecursiveAction {

    private static final long serialVersionUID = 2494963268385939307L;

    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        //如果 last 与 first 的属性插值小于 10 ，则更新价格，否则就下分任务线程分两段执行。
        if (last-first<10){
            updatePrice();
        }else {
            int middle = (last+first)/2;
            System.out.println("Task: Pending tasks:"+getQueuedTaskCount());
            Task t1 = new Task(products,first,middle+1,increment);
            Task t2 = new Task(products,middle+1,last,increment);
            invokeAll(t1,t2);
        }
    }

    private void updatePrice() {
        for (int i= first;i<last;i++){
            Product product = products.get(i);
            product.setPrice(product.getPrice()*(1+increment));
        }
    }
}
