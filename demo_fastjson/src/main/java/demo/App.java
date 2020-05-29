package demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Dog dog = new Dog("huahua",2);
        //对象转字符串
        String str = JSON.toJSONString(dog);
        System.out.println("对象 转 字符串："+str);
        //字符串转对象
        Dog dog1 = JSON.parseObject(str,Dog.class);
        System.out.println("字符串 转 对象："+dog1);
        //字符串转JSONObject
        JSONObject object = JSONObject.parseObject(str);
        System.out.println("字符串 转 JSONObject:"+object);
        //JsonObject 转字符串
        String jsonStr = object.toJSONString();
        System.out.println("JsonObject 转 字符串："+jsonStr);
        //JSONObject 转 map
        Map<String,Object> map = (Map<String,Object>)object;
        System.out.println("JSONObject 转 map："+ map.get("name"));
        //map转字符串
        String jsonStr2 = JSON.toJSONString(map);
        System.out.println("map 转 字符串："+ jsonStr2);
        //map 转 JsonObject
        JSONObject m2obj = new JSONObject(map);
        System.out.println("map 转 JsonObject"+ m2obj);
        //数组转jsonArray
        Dog dog2 = new Dog("kaka",3);
        Object[] dogs = {dog,dog2};
        JSONArray jarr = (JSONArray) JSONArray.toJSON(dogs);
        System.out.println("数组 转 jsonObject："+ jarr);
        //JsonArray 转数组
        Object[] j2objs = JSONObject.toJavaObject(jarr,Object[].class);
        System.out.println("JsonArray 转 数组："+ j2objs[1]);
        //JsonArr 转 List
        List<Dog> dogList = JSONArray.parseArray(jarr.toJSONString(),Dog.class);
        System.out.println("JsonArr 转 List："+ dogList.get(0));
        //list 转 jsonarray
        List<Dog> list = new ArrayList<>();
        list.add(dog1);
        list.add(dog2);
        JSONArray dogArray = JSONArray.parseArray(JSON.toJSONString(list));
        System.out.println("list 转 JsonArray：" + dogArray);
        //字符串类型数组 转 josnArray
        String jarrStr = "[{\"name\":\"huahua\",\"age\":2},{\"name\":\"kaka\",\"age\":3}]";
        JSONArray s2jarr = JSONArray.parseArray(jarrStr);
        System.out.println("字符串类型数组 转 josnArray：" + s2jarr);
    }
}
