package forkjoin.searchfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * TODO 异步运行任务-文件后缀名搜索
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 18:17
 */
public class FolderProcessor extends RecursiveTask<List<String>> {

    private static final long serialVersionUID = 513633183101804241L;

    private String path;
    private String extension;//存储扩展名

    public FolderProcessor(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<>();
        List<FolderProcessor> processors = new ArrayList<>();
        File file = new File(path);
        File[] content = file.listFiles();
        if (content!=null){
            for (File file1:content){
                //文件夹递归
                if (file1.isDirectory()){
                    FolderProcessor task = new FolderProcessor(file1.getAbsolutePath(),extension);
                    task.fork();
                    processors.add(task);
                }else {
                    if (checkFile(file1.getName())){
                        list.add(file1.getAbsolutePath());
                    }
                }
            }
        }
        //子任务列表超过50 输出信息
        if (processors.size()>50){
            System.out.println(file.getAbsolutePath()+": "+processors.size()+"tasks run..");
        }
        addResultFromTasks(list,processors);
        return list;
    }

    /**
     * 添加结果到list
     * @param list
     * @param processors
     */
    private void addResultFromTasks(List<String> list, List<FolderProcessor> processors) {
        for (FolderProcessor item:processors){
            list.addAll(item.join());
        }
    }

    /**
     * 检查文件后缀
     * @param name
     * @return
     */
    private boolean checkFile(String name) {
        return name.endsWith(extension);
    }
}
