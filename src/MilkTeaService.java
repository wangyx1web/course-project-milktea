import java.util.List;



    // 奶茶管理接口：定义核心功能，后续可灵活替换实现
     interface MilkTeaService {
        // 添加奶茶
        boolean addMilkTea(MilkTea milkTea);
        // 修改奶茶信息
        boolean updateMilkTea(String teaId, double newPrice, int newStock);
        // 删除奶茶
        boolean deleteMilkTea(String teaId);
        // 查询所有奶茶
        List<MilkTea> getAllMilkTea();
        // 模糊搜索奶茶（按名称/口味）
        List<MilkTea> searchMilkTea(String keyword);
        // 扣减库存
        boolean reduceStock(String teaId, int num);
    }




