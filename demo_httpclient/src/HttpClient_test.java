
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @ClassName HttpClient_test
 * @Description TODO
 * @Author MGG
 * @Date 2018/12/24 0024 22:02
 * @Version 1.0
 */
public class HttpClient_test {
    //HttpGet get = new HttpGet("http://localhost:8800/getPerson?id=10010");
    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet get = new HttpGet("http://222.190.111.125:6789/Services/NewWebService.asmx/" +
//                "GetNoise?UserName=jiekou&Pwd=jiekou&PointCode=5436&BeginTime=2020-05-01&EndTime2020-06-01&TimeType=hour");
        HttpGet get = new HttpGet("http://ws.webxml.com.cn/WebServices/TrainTimeWebService.asmx/getStationAndTimeDataSetByLikeTrainCode?TrainCode=D6525&UserID=");
        CloseableHttpResponse response = httpClient.execute(get);
        String strResult = "";
        if (response != null) {
            System.out.println(response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(response.getEntity());
            } else if (response.getStatusLine().getStatusCode() == 400) {
                strResult = "Error Response: " + response.getStatusLine().toString();
            } else if (response.getStatusLine().getStatusCode() == 500) {
                strResult = "Error Response: " + response.getStatusLine().toString();
            } else {
                strResult = "Error Response: " + response.getStatusLine().toString();
            }
        } else {

        }
        System.out.println(strResult);
    }
}
