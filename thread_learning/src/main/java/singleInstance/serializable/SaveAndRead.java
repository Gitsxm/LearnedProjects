package singleInstance.serializable;

import java.io.*;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 10:25
 */
public class SaveAndRead {
    public static void main(String[] args) {
        MyObject object = MyObject.getInstance();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("myObject.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println(object.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(new File("myObject.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            MyObject myObject = (MyObject) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println(myObject.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
