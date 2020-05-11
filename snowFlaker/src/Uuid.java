import java.util.UUID;

/**
 * @ClassName Uuid
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/14 9:12
 * @Version 1.0
 */
public class Uuid {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}
