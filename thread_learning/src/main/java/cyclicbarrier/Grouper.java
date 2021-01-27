package cyclicbarrier;

/**
 * 统计线程
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 16:06
 */
public class Grouper implements Runnable {
    private Result result;

    public Grouper(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.println("Grouper: Processing result...");
        int[] data = result.getData();
        for (int number : data) {
            finalResult += number;
        }
        System.out.println("Grouper: total result :"+finalResult);
    }


}
