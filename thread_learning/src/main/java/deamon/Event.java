package deamon;

import lombok.Data;

import java.util.Date;

/**
 * 守护线程事件
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/14 17:05
 */
@Data
public class Event {
    private Date time;
    private String event;
}
