package countdownlatch;

/**
 * 视频会议参与者线程
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 15:15
 */
public class Participant implements Runnable{

    private Videoconference videoconference;

    private String name;

    public Participant(Videoconference videoconference, String name) {
        this.videoconference = videoconference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random()*10);
        try {
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        videoconference.arrive(name);
    }
}
