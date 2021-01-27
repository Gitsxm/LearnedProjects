package phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 并发阶段运行-Phaser  文件搜索统计线程
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/23 14:36
 */
public class FileSearch implements Runnable {
    private String initPath;
    private String suffix;
    private Phaser phaser;
    private List<String> results;

    public FileSearch(String initPath, String suffix, Phaser phaser) {
        this.initPath = initPath;
        this.suffix = suffix;
        this.phaser = phaser;
        this.results = new ArrayList<>();
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+" Starting...");
        File file = new File(initPath);
        if (file.isDirectory()){
            directoryProcess(file);
        }
        if (!checkResults()){
            return;
        }
        filterResults();
        if (!checkResults()){
            return;
        }
        showInfo();
        phaser.arriveAndDeregister();
        System.out.println(Thread.currentThread().getName()+" Work completed.");
    }

    private void directoryProcess(File file) {
        File[] files = file.listFiles();
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    directoryProcess(files[i]);
                } else {
                    fileProcess(files[i]);
                }
            }
        }
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(suffix)) {
            results.add(file.getAbsolutePath());
        }
    }

    private void filterResults() {
        List<String> newResults = new ArrayList<>();
        long actualDate = new Date().getTime();
        for (String str : results) {
            File file = new File(str);
            long fileDate = file.lastModified();
            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                newResults.add(str);
            }
        }
        results = newResults;
    }

    private boolean checkResults() {
        if (results.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " Phase :" + phaser.getPhase() + " : 0 result!");
            System.out.println(Thread.currentThread().getName() + " Phase :" + phaser.getPhase() + " End!");
            phaser.arriveAndDeregister();
            return false;
        } else {
            System.out.println(Thread.currentThread().getName() + " Phase :" + phaser.getPhase() + " :" + results.size() + " results!");
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void showInfo(){
        for (String str:results){
            File file = new File(str);
            System.out.println(Thread.currentThread().getName()+" : "+file.getAbsolutePath());
        }
        phaser.arriveAndAwaitAdvance();
    }
}
