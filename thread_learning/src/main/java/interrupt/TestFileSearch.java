package interrupt;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/9 16:33
 */
public class TestFileSearch {
    public static void main(String[] args) throws InterruptedException {
        FileSearch fileSearch = new FileSearch("d:\\blog","blog.7z");
        Thread thread = new Thread(fileSearch);
        thread.start();
        thread.sleep(10000);
        thread.interrupt();

    }
}
