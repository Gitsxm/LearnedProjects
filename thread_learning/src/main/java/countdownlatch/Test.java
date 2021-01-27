package countdownlatch;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 15:18
 */
public class Test {
    public static void main(String[] args) {
        //设置这是十个人的视频会议
        Videoconference videoconference = new Videoconference(10);
        Thread thread = new Thread(videoconference);
        //会议等待开始
        thread.start();
        //弄10个参与人
        Participant[] participants = new Participant[10];
        for (int i = 0; i < 10; i++) {
            participants[i] = new Participant(videoconference, "person" + i);
            Thread t = new Thread(participants[i]);
            t.start();
        }
    }
}
