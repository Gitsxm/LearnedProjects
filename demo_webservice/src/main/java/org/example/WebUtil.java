package org.example;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/2 16:01
 */
public class WebUtil {
    public static void main(String[] args){

        String url = "http://222.190.111.125:6789/Services/NewWebService.asmx";// 提供接口的地址
        String soapaction = "http://tempuri.org/"; // 域名，这是在server定义的--不知道的可以问接口提供方，他们一并提供这个

        String pwd = "jiekou";
        String username = "jiekou";
        String code = "5436";
        Service service = new Service();
        try{
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(url);
            call.setOperationName(new QName(soapaction, "GetPointInfo")); // 设置要调用哪个方法
            call.addParameter(new QName(soapaction, "UserName"), // 设置要传递的参数--要和接口方提供的参数名一致
                    org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter(new QName(soapaction, "Pwd"), // 设置要传递的参数
                    org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter(new QName(soapaction, "PointCode"), // 设置要传递的参数
                    org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(new QName(soapaction, "GetPointInfo"), String.class); // 要返回的数据类型（自定义类型，我这边接口提供方给我返回的是json字符串，所以我用string类型接收。这个地方一定要设置好，不然各种报错很崩溃）

            call.setUseSOAPAction(true);
            call.setSOAPActionURI(soapaction + "GetPointInfo");

            String v = (String) call.invoke(new Object[] { pwd, username,code});// 调用方法并传递参数-传递的参数和设置的参数要对应，顺序不能搞错了

            System.out.print(v);//打印结果（我设置的接收格式为json字符串，这边直接打印出来）
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
