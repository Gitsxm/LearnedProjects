package multiple_condition_lock;

/**
 * 文件模拟
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/20 21:00
 */
public class FileMock {
    private String[] content;
    private int index;

    /**
     * 使用随机字符填充内容
     * @param size
     * @param length
     */
    public FileMock(int size,int length) {
        content = new String[size];
        for (int i=0;i<size;i++){
            StringBuilder builder = new StringBuilder(length);
            for (int j=0;j<length;j++){
                int indice = (int) (Math.random()*255);
                builder.append((char) indice);
            }
            content[i] = builder.toString();
        }
        index=0;
    }

    /**
     * 到末尾返回 false
     *
     * @return
     */
    public boolean hasMoreLine(){
        return index<content.length;
    }

    /**
     * 根据 index 返回指定内容
     * @return
     */
    public String getLine(){
        if (hasMoreLine()){
            System.out.println("Mock: "+(content.length-index));
            return content[index++];
        }
        return null;
    }
}
