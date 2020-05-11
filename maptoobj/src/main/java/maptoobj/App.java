package maptoobj;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        String str = "10";
        Object obj = ConvertUtils.convert(str, BigDecimal.class);
        System.out.println(obj.getClass());//class java.math.BigDecimal
        Object obj2 = ConvertUtils.convert("100", Integer.class);
        System.out.println(obj2.getClass());//class java.lang.Integer
    }


}
