package org.example.file;

import java.io.File;

public class DeleteFiles {
    public static void main(String[] args) {
        File file = new File("d:/test");
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files){
                String fileName = f.getName();
                if (fileName.substring(0,fileName.lastIndexOf(".")).equals("base_admin")){
                    f.delete();
                }
            }
        }
    }
}
