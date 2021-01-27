package cyclicbarrier;

import java.util.Random;

/**
 * 在集合点同步--矩阵搜索统计示例
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 15:43
 */
public class MartixMock {
    private int data[][];

    public MartixMock(int size, int length, int number) {
        int count = 0;
        data = new int[size][length];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    count++;
                }
            }
        }
        System.out.println("Mock: there are " + count + " occurrence " + "of " + number + " in generated data.");
    }

    /**
     * 获取行
     * @param row
     * @return
     */
    public int[] getRow(int row) {
        if (row >= 0 && row < data.length) {
            return data[row];
        }
        return null;
    }
}
