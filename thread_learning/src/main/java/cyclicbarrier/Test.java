package cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 16:13
 */
public class Test {
    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT = 2000;
        MartixMock martixMock = new MartixMock(ROWS, NUMBERS, SEARCH);
        Result result = new Result(ROWS);
        Grouper grouper = new Grouper(result);
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);
        Searcher[] searchers = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i] = new Searcher(i * LINES_PARTICIPANT, i * LINES_PARTICIPANT + LINES_PARTICIPANT, martixMock, result, 5, barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.println("Main: main thread finished!");
    }
}
