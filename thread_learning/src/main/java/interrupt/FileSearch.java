package interrupt;

import java.io.File;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/9 16:21
 */
public class FileSearch implements Runnable{
    private String path;
    private String fileName;

    public FileSearch(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(path);
        if (file.isDirectory()){

            try {
                directoryProcess(file);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread interrupt!");
    }

    private void directoryProcess(File file) throws InterruptedException {
        File[] files = file.listFiles();
        if (null != files){
            for(File file1:files){
                if (file1.isDirectory()){
                    directoryProcess(file1);
                }else {
                    if (fileName.equals(file1.getName())){
                        System.out.println(Thread.currentThread().getName()+file1.getAbsolutePath());
                    }
                }
            }
            if (Thread.interrupted()){
                throw new InterruptedException();
            }
        }
    }
}
