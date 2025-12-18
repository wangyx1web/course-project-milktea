public class MilkTea {
    private String teaId; // 奶茶编号
    private String name; // 名称
    private double price; // 价格
    private String taste; // 口味
    private int stock; // 库存

    // 构造方法
    public MilkTea(String teaId, String name, double price, String taste, int stock) {
        this.teaId = teaId;
        this.name = name;
        this.price = price;
        this.taste = taste;
        this.stock = stock;
    }

    // Get/Set方法
    public String getTeaId() { return teaId; }
    public void setTeaId(String teaId) { this.teaId = teaId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getTaste() { return taste; }
    public void setTaste(String taste) { this.taste = taste; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // 重写toString：方便展示
    @Override
    public String toString() {
        return "奶茶编号：" + teaId + " | 名称：" + name + " | 价格：" + price + "元 | 口味：" + taste + " | 库存：" + stock;
    }
}
