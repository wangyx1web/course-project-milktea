// 奶茶类
class MilkTea {
    String id;
    String name;
    double price;

    // 构造方法
    MilkTea(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // 返回奶茶信息字符串
    public String toString() {
        return "编号：" + id + " | 名称：" + name + " | 价格：" + price + "元";
    }
}