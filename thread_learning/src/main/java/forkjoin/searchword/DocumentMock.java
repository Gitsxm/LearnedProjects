package forkjoin.searchword;

import java.util.Random;

/**
 * TODO 合并任务结果-生成字符串矩阵
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 14:40
 */
public class DocumentMock {
    private String[] words = {"one", "two", "the", "word", "number", "mock", "list", "map", "set"};

    public String[][] generateDocument(int numberLines, int numberWords, String word) {
        int count = 0;
        String document[][] = new String[numberLines][numberWords];
        Random random = new Random();
        for (int i = 0; i < numberLines; i++) {
            for (int j = 0; j < numberWords; j++) {
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (document[i][j].equals(word)) {
                    count++;
                }
            }
        }
        System.out.println("DocumentMock: The word appears " + count + " times..");
        return document;
    }
}
