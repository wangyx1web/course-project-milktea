import java.util.List;

public class MilkTeaShopApp {
    private static MilkTeaService milkTeaService = new MilkTeaServiceImpl();
    private static UserService userService = new UserServiceImpl();
    private static User currentLoginUser = null; // 当前登录用户

    public static void main(String[] args) {
        System.out.println("===== 欢迎使用奶茶店管理系统 =====");
        // 初始化测试数据
        initTestData();

        // 主菜单循环
        while (true) {
            if (currentLoginUser == null) {
                // 未登录菜单
                showUnLoginMenu();
            } else {
                // 已登录菜单（区分顾客/员工）
                showLoginMenu();
            }
        }
    }

    // 初始化测试数据（方便快速测试）
    private static void initTestData() {
        // 添加测试奶茶
        milkTeaService.addMilkTea(new MilkTea("t001", "珍珠奶茶", 12.0, "原味", 50));
        milkTeaService.addMilkTea(new MilkTea("t002", "芋泥奶茶", 15.0, "芋泥", 30));
        // 添加测试员工（店长：用户名admin，密码123456）
        userService.register(new Employee("e001", "admin", "123456", "emp001", "店长"));
        // 添加测试顾客（用户名zhangsan，密码123）
        userService.register(new Customer("c001", "zhangsan", "123"));
    }

    // 未登录菜单
    private static void showUnLoginMenu() {
        System.out.println("\n===== 未登录菜单 =====");
        System.out.println("1. 用户登录  2. 顾客注册  3. 员工注册  4. 退出系统");
        int choice = InputUtil.inputInt("请选择操作：");
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                registerCustomer();
                break;
            case 3:
                registerEmployee();
                break;
            case 4:
                System.out.println("感谢使用，再见！");
                InputUtil.close();
                System.exit(0);
            default:
                System.out.println("输入错误，请重新选择！");
        }
    }

    // 已登录菜单（区分角色）
    private static void showLoginMenu() {
        if (currentLoginUser instanceof Customer) {
            // 顾客菜单
            showCustomerMenu();
        } else if (currentLoginUser instanceof Employee) {
            // 员工菜单
            showEmployeeMenu();
        }
    }

    // 顾客菜单
    private static void showCustomerMenu() {
        Customer customer = (Customer) currentLoginUser;
        System.out.println("\n===== 顾客菜单（当前会员：" + customer.getMemberLevel() + "）=====");
        System.out.println("1. 查看所有奶茶  2. 搜索奶茶  3. 加入购物车  4. 结算购物车  5. 修改个人信息  6. 退出登录");
        int choice = InputUtil.inputInt("请选择操作：");
        switch (choice) {
            case 1:
                showAllMilkTea();
                break;
            case 2:
                searchMilkTea();
                break;
            case 3:
                addToCart(customer);
                break;
            case 4:
                customer.checkout(milkTeaService);
                break;
            case 5:
                updateUserInfo();
                break;
            case 6:
                currentLoginUser = null;
                System.out.println("退出登录成功！");
                break;
            default:
                System.out.println("输入错误！");
        }
    }

    // 员工菜单
    private static void showEmployeeMenu() {
        Employee employee = (Employee) currentLoginUser;
        System.out.println("\n===== 员工菜单（岗位：" + employee.getPosition() + "）=====");
        System.out.println("1. 查看所有奶茶  2. 搜索奶茶  3. 修改个人信息  6. 退出登录");
        // 店长额外显示管理权限
        if (employee.canManageMilkTea()) {
            System.out.println("4. 添加奶茶  5. 修改奶茶  7. 删除奶茶");
        }
        int choice = InputUtil.inputInt("请选择操作：");
        switch (choice) {
            case 1:
                showAllMilkTea();
                break;
            case 2:
                searchMilkTea();
                break;
            case 3:
                updateUserInfo();
                break;
            case 4:
                if (employee.canManageMilkTea()) addMilkTea();
                else System.out.println("无权限！");
                break;
            case 5:
                if (employee.canManageMilkTea()) updateMilkTea();
                else System.out.println("无权限！");
                break;
            case 6:
                currentLoginUser = null;
                System.out.println("退出登录成功！");
                break;
            case 7:
                if (employee.canManageMilkTea()) deleteMilkTea();
                else System.out.println("无权限！");
                break;
            default:
                System.out.println("输入错误！");
        }
    }

    // ========== 核心功能方法 ==========
    // 登录
    private static void login() {
        String username = InputUtil.inputString("请输入用户名：");
        String password = InputUtil.inputString("请输入密码：");
        currentLoginUser = userService.login(username, password);
    }

    // 注册顾客
    private static void registerCustomer() {
        String userId = InputUtil.inputString("请输入顾客ID：");
        String username = InputUtil.inputString("请输入用户名：");
        String password = InputUtil.inputString("请输入密码：");
        Customer customer = new Customer(userId, username, password);
        userService.register(customer);
    }

    // 注册员工
    private static void registerEmployee() {
        String userId = InputUtil.inputString("请输入员工ID：");
        String username = InputUtil.inputString("请输入用户名：");
        String password = InputUtil.inputString("请输入密码：");
        String empId = InputUtil.inputString("请输入工号：");
        String position = InputUtil.inputString("请输入岗位（收银员/店长）：");
        Employee employee = new Employee(userId, username, password, empId, position);
        userService.register(employee);
    }

    // 查看所有奶茶
    private static void showAllMilkTea() {
        List<MilkTea> list = milkTeaService.getAllMilkTea();
        if (list.isEmpty()) {
            System.out.println("暂无奶茶！");
            return;
        }
        System.out.println("\n===== 所有奶茶 =====");
        for (MilkTea tea : list) {
            System.out.println(tea);
        }
    }

    // 搜索奶茶
    private static void searchMilkTea() {
        String keyword = InputUtil.inputString("请输入搜索关键词（名称/口味）：");
        List<MilkTea> list = milkTeaService.searchMilkTea(keyword);
        if (list.isEmpty()) {
            System.out.println("未找到匹配奶茶！");
            return;
        }
        System.out.println("\n===== 搜索结果 =====");
        for (MilkTea tea : list) {
            System.out.println(tea);
        }
    }

    // 顾客加购奶茶
    private static void addToCart(Customer customer) {
        showAllMilkTea();
        String teaId = InputUtil.inputString("请输入要购买的奶茶编号：");
        int num = InputUtil.inputInt("请输入购买数量：");
        // 查找奶茶
        List<MilkTea> list = milkTeaService.searchMilkTea(teaId);
        if (list.isEmpty()) {
            System.out.println("奶茶不存在！");
            return;
        }
        customer.addToCart(list.get(0), num);
    }

    // 店长添加奶茶
    private static void addMilkTea() {
        String teaId = InputUtil.inputString("请输入奶茶编号：");
        String name = InputUtil.inputString("请输入奶茶名称：");
        double price = InputUtil.inputDouble("请输入奶茶价格：");
        String taste = InputUtil.inputString("请输入奶茶口味：");
        int stock = InputUtil.inputInt("请输入库存数量：");
        milkTeaService.addMilkTea(new MilkTea(teaId, name, price, taste, stock));
    }

    // 店长修改奶茶
    private static void updateMilkTea() {
        String teaId = InputUtil.inputString("请输入要修改的奶茶编号：");
        double newPrice = InputUtil.inputDouble("请输入新价格：");
        int newStock = InputUtil.inputInt("请输入新库存：");
        milkTeaService.updateMilkTea(teaId, newPrice, newStock);
    }

    // 店长删除奶茶
    private static void deleteMilkTea() {
        String teaId = InputUtil.inputString("请输入要删除的奶茶编号：");
        milkTeaService.deleteMilkTea(teaId);
    }

    // 修改个人信息
    private static void updateUserInfo() {
        String newPhone = InputUtil.inputString("请输入新手机号：");
        String newEmail = InputUtil.inputString("请输入新邮箱：");
        userService.updateUserInfo(currentLoginUser.getUsername(), newPhone, newEmail);
    }
}
