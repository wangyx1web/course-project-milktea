import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<MilkTea> cart = new ArrayList<>(); // 购物车
    private String memberLevel = "普通会员"; // 会员等级
    private double totalConsume = 0.0; // 累计消费

    // 构造方法：复用父类构造
    public Customer(String userId, String username, String password) {
        super(userId, username, password);
    }

    // 加购奶茶
    public void addToCart(MilkTea milkTea, int num) {
        if (milkTea.getStock() >= num) {
            for (int i = 0; i < num; i++) {
                cart.add(milkTea);
            }
            System.out.println("成功添加" + num + "杯【" + milkTea.getName() + "】到购物车！");
        } else {
            System.out.println("库存不足！当前库存：" + milkTea.getStock());
        }
    }

    // 结算购物车
    public double checkout(MilkTeaService milkTeaService) {
        if (cart.isEmpty()) {
            System.out.println("购物车为空！");
            return 0.0;
        }
        double total = 0.0;
        // 计算总价+扣减库存
        for (MilkTea tea : cart) {
            total += tea.getPrice();
            milkTeaService.reduceStock(tea.getTeaId(), 1);
        }
        // 更新累计消费和会员等级
        totalConsume += total;
        if (totalConsume >= 500) {
            memberLevel = "VIP会员";
        } else if (totalConsume >= 200) {
            memberLevel = "高级会员";
        }
        // 清空购物车
        cart.clear();
        System.out.println("结算成功！本次消费：" + total + "元，累计消费：" + totalConsume + "元，会员等级：" + memberLevel);
        return total;
    }

    // Getter
    public List<MilkTea> getCart() {
        return cart;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public double getTotalConsume() {
        return totalConsume;
    }
}

