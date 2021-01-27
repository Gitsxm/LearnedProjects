package forkjoin.updateprice;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 使用 fork/join 更新商品价格示例-随机生成商品列表
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 11:29
 */
public class ProductListGenerator {
    public List<Product> generate(int size){
        List<Product> products = new ArrayList<>();
        for (int i=0;i<size;i++){
            Product product = new Product();
            product.setName("product"+i);
            product.setPrice(10);
            products.add(product);
        }
        return products;
    }
}
