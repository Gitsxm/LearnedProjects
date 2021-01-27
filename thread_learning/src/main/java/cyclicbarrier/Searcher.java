package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 搜索线程类
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 15:55
 */
public class Searcher implements Runnable{
    private int firstRow;
    private int lastRow;
    private MartixMock martixMock;
    private Result result;
    private int number;
    private CyclicBarrier barrier;

    public Searcher(int firstRow, int lastRow, MartixMock martixMock, Result result, int number, CyclicBarrier barrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.martixMock = martixMock;
        this.result = result;
        this.number = number;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int counter;
        System.out.println(Thread.currentThread().getName()+" processing lines from "+firstRow+" to "+lastRow);
        for (int i= firstRow;i<lastRow;i++){
            int[] row = martixMock.getRow(i);
            counter = 0;
            for (int j=0;j<row.length;j++){
                if (row[j]==number){
                    counter++;
                }
            }
            result.setData(i,counter);
        }
        System.out.println(Thread.currentThread().getName()+" lines processed!");
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
