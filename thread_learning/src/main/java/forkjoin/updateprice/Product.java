package forkjoin.updateprice;

/**
 * TODO 使用 fork/join 更新商品价格示例-商品
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 11:26
 */
public class Product {
    private String name;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

