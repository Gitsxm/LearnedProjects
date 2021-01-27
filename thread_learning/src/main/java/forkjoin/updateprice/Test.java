package forkjoin.updateprice;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * TODO 使用 fork/join 更新商品价格示例-更新任务-测试
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 11:44
 */
public class Test {
    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);
        Task task = new Task(products,0,products.size(),0.20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        do{
            System.out.println("Main: Thread Count: "+pool.getActiveThreadCount());
            System.out.println("Main: Thread Steal: "+pool.getStealCount());
            System.out.println("Main: Parallelism: "+pool.getParallelism());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!task.isDone());
        pool.shutdown();
        if (task.isCompletedNormally()){
            System.out.println("Main: The process has completed normally.");
        }
        //打出没有修改的商品
        for (Product product:products){
            if (product.getPrice()!=12){
                System.out.println("Product: "+product.getName()+"--"+product.getPrice());
            }
        }
    }
}
