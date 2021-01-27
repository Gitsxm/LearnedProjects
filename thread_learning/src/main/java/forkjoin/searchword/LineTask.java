package forkjoin.searchword;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

/**
 * TODO 合并任务结果-查找文档每行词出现次数
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 14:54
 */
public class LineTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = -6815093018936287894L;

    private String line[];
    private int start, end;
    private String word;

    public LineTask(String[] line, int start, int end, String word) {
        this.line = line;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        Integer result = null;
        if (end - start < 100) {
            result = count(line, start, end, word);
        } else {
            int mid = (start + end) / 2;
            LineTask task1 = new LineTask(line, start, mid, word);
            LineTask task2 = new LineTask(line, mid, end, word);
            invokeAll(task1, task2);
            try {
                result = groupResult(task1.get(), task2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Integer groupResult(Integer integer, Integer integer1) {
        Integer result;
        return integer + integer1;
    }

    private Integer count(String[] line, int start, int end, String word) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (line[i].equals(word)) {
                count++;
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }
}
