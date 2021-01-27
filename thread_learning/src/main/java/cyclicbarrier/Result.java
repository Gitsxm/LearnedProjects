package cyclicbarrier;

/**
 * 记录中间结果
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 15:52
 */
public class Result {
    private int data[];

    public Result(int size) {
        data = new int[size];
    }

    public int[] getData() {
        return data;
    }

    public void setData(int position,int value) {
        data[position] = value;
    }
}
