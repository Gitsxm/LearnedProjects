package org.example.thread;

public class Test {

    public static void updateData(){
        System.out.println("接收数据。。。");
        System.out.println("数据保存");
        UpdateData task = new UpdateData();
        task.start();
        System.out.println("保存成功");
        return;
    }

    public static void main(String[] args) {
        updateData();
    }
}
