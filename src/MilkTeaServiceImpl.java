import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MilkTeaServiceImpl implements MilkTeaService {
    // 存储奶茶数据（ID为key，奶茶对象为value）
    private final Map<String, MilkTea> milkTeaMap = new HashMap<>();


    @Override
    public boolean addMilkTea(MilkTea milkTea) {
        if (milkTeaMap.containsKey(milkTea.getTeaId())) {
            System.out.println("奶茶编号已存在！");
            return false;
        }
        milkTeaMap.put(milkTea.getTeaId(), milkTea);
        System.out.println("添加成功：" + milkTea.getName());
        return true;
    }

    @Override
    public boolean updateMilkTea(String teaId, double newPrice, int newStock) {
        MilkTea tea = milkTeaMap.get(teaId);
        if (tea == null) {
            System.out.println("奶茶不存在！");
            return false;
        }
        tea.setPrice(newPrice);
        tea.setStock(newStock);
        System.out.println("修改成功：" + tea);
        return true;
    }

    @Override
    public boolean deleteMilkTea(String teaId) {
        if (milkTeaMap.remove(teaId) == null) {
            System.out.println("奶茶不存在！");
            return false;
        }
        System.out.println("删除成功！");
        return true;
    }

    @Override
    public List<MilkTea> getAllMilkTea() {
        return new ArrayList<>(milkTeaMap.values());
    }

    @Override
    public List<MilkTea> searchMilkTea(String keyword) {
        List<MilkTea> result = new ArrayList<>();
        for (MilkTea tea : milkTeaMap.values()) {
            if (tea.getName().contains(keyword) || tea.getTaste().contains(keyword)) {
                result.add(tea);
            }
        }
        return result;
    }

    @Override
    public boolean reduceStock(String teaId, int num) {
        MilkTea tea = milkTeaMap.get(teaId);
        if (tea == null || tea.getStock() < num) {
            return false;
        }
        tea.setStock(tea.getStock() - num);
        return true;
    }
}
