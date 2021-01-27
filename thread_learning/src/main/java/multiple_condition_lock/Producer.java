package multiple_condition_lock;

/**
 * 写入缓冲区
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/20 21:59
 */
public class Producer implements Runnable{
    private FileMock mock;
    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLine()){
            String line = mock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
