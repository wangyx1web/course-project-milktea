import java.util.Scanner;

// 奶茶店管理系统主类
public class MilkTeaShop {
    private static MilkTea[] menu = new MilkTea[20];
    private static int count = 0;
    private static User[] users = new User[100];  // 用户数组
    private static int userCount = 0;  // 用户数量
    private static User currentUser = null;  // 当前登录用户
    private static Scanner scanner = new Scanner(System.in);

    // 主方法，程序入口
    public static void main(String[] args) {
        initData();
        initUsers();

        // 主循环：未登录时显示未登录菜单，登录后显示主菜单
        while (true) {
            if (currentUser == null) {
                showUnloggedMenu();
            } else {
                showMainMenu();
            }
        }
    }

    // 初始化测试数据
    private static void initUsers() {
        // 添加管理员账户
        users[0] = new User("admin", "123456", "admin");
        userCount = 1;
    }

    // 显示未登录菜单
    private static void showUnloggedMenu() {
        System.out.println("\n===== 未登录菜单 =====");
        System.out.println("1. 用户登录");
        System.out.println("2. 顾客注册");
        System.out.println("3. 员工注册");
        System.out.println("4. 退出系统");
        System.out.print("请选择操作：");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: login(); break;
            case 2: register("customer"); break;
            case 3: register("employee"); break;
            case 4:
                System.out.println("感谢使用！");
                System.exit(0);
            default:
                System.out.println("输入错误，请重新选择！");
        }
    }

    // 用户登录
    private static void login() {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username) && users[i].checkPassword(password)) {
                currentUser = users[i];
                String userType = currentUser.type.equals("customer") ? "顾客" :
                        currentUser.type.equals("admin") ? "管理员" : "员工";
                System.out.println("登录成功！欢迎，" + userType + " " + username);
                return;
            }
        }
        System.out.println("用户名或密码错误！");
    }

    // 用户注册
    private static void register(String type) {
        if (userCount >= users.length) {
            System.out.println("用户数量已达上限！");
            return;
        }

        System.out.print("请输入用户名：");
        String username = scanner.nextLine();

        // 检查用户名是否已存在
        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username)) {
                System.out.println("用户名已存在，请重新选择！");
                return;
            }
        }

        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        users[userCount] = new User(username, password, type);
        userCount++;
        System.out.println("注册成功！" + (type.equals("customer") ? "顾客" : "员工") + " " + username);
    }

    // 显示主菜单（登录后）
    private static void showMainMenu() {
        while (currentUser != null) {
            System.out.println("\n===== 奶茶店管理系统 =====");
            String userType = currentUser.type.equals("customer") ? "顾客" :
                    currentUser.type.equals("admin") ? "管理员" : "员工";
            System.out.println("当前用户：" + currentUser.username + " (" + userType + ")");

            if (currentUser.type.equals("admin")) {
                // 管理员菜单
                System.out.println("\n请选择功能：");
                System.out.println("1. 添加奶茶");
                System.out.println("2. 查看菜单");
                System.out.println("3. 查找奶茶");
                System.out.println("4. 删除奶茶");
                System.out.println("5. 修改奶茶");
                System.out.println("6. 按价格排序");
                System.out.println("7. 统计信息");
                System.out.println("8. 查看所有用户");
                System.out.println("9. 退出登录");
                System.out.println("10. 退出系统");
                System.out.print("请输入：");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: addMilkTea(); break;
                    case 2: showMenu(); break;
                    case 3: findMilkTea(); break;
                    case 4: deleteMilkTea(); break;
                    case 5: updateMilkTea(); break;
                    case 6: sortByPrice(); break;
                    case 7: showStatistics(); break;
                    case 8: showAllUsers(); break;
                    case 9:
                        currentUser = null;
                        System.out.println("已退出登录！");
                        break;
                    case 10:
                        System.out.println("感谢使用！");
                        System.exit(0);
                    default:
                        System.out.println("输入错误，请重新选择！");
                }
            } else if (currentUser.type.equals("employee")) {
                // 员工菜单
                System.out.println("\n请选择功能：");
                System.out.println("1. 添加奶茶");
                System.out.println("2. 查看菜单");
                System.out.println("3. 查找奶茶");
                System.out.println("4. 删除奶茶");
                System.out.println("5. 修改奶茶");
                System.out.println("6. 按价格排序");
                System.out.println("7. 统计信息");
                System.out.println("8. 退出登录");
                System.out.println("9. 退出系统");
                System.out.print("请输入：");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: addMilkTea(); break;
                    case 2: showMenu(); break;
                    case 3: findMilkTea(); break;
                    case 4: deleteMilkTea(); break;
                    case 5: updateMilkTea(); break;
                    case 6: sortByPrice(); break;
                    case 7: showStatistics(); break;
                    case 8:
                        currentUser = null;
                        System.out.println("已退出登录！");
                        break;
                    case 9:
                        System.out.println("感谢使用！");
                        System.exit(0);
                    default:
                        System.out.println("输入错误，请重新选择！");
                }
            } else {
                // 顾客菜单
                System.out.println("\n请选择功能：");
                System.out.println("1. 查看菜单");
                System.out.println("2. 查找奶茶");
                System.out.println("3. 按价格排序");
                System.out.println("4. 统计信息");
                System.out.println("5. 退出登录");
                System.out.println("6. 退出系统");
                System.out.print("请输入：");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: showMenu(); break;
                    case 2: findMilkTea(); break;
                    case 3: sortByPrice(); break;
                    case 4: showStatistics(); break;
                    case 5:
                        currentUser = null;
                        System.out.println("已退出登录！");
                        break;
                    case 6:
                        System.out.println("感谢使用！");
                        System.exit(0);
                    default:
                        System.out.println("输入错误，请重新选择！");
                }
            }
        }
    }

    // 初始化测试数据
    private static void initData() {
        menu[0] = new MilkTea("1", "永民手作", 19.0);
        count = 1;
    }

    // 添加奶茶到菜单
    private static void addMilkTea() {
        if (count >= menu.length) {
            System.out.println("菜单已满！");
            return;
        }

        System.out.print("输入奶茶编号：");
        String id = scanner.nextLine();
        System.out.print("输入奶茶名称：");
        String name = scanner.nextLine();
        System.out.print("输入价格：");
        double price = scanner.nextDouble();
        scanner.nextLine();

        menu[count] = new MilkTea(id, name, price);
        count++;
        System.out.println("添加成功！");
    }

    // 显示所有奶茶菜单
    private static void showMenu() {
        if (count == 0) {
            System.out.println("菜单为空！");
            return;
        }

        System.out.println("\n===== 奶茶菜单 =====");
        for (int i = 0; i < count; i++) {
            System.out.println((i+1) + ". " + menu[i]);
        }
    }

    // 根据编号查找奶茶
    private static void findMilkTea() {
        System.out.print("输入要查找的奶茶编号：");
        String targetId = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (menu[i].id.equals(targetId)) {
                System.out.println("找到：" + menu[i]);
                return;
            }
        }
        System.out.println("未找到该奶茶！");
    }

    // 删除奶茶
    private static void deleteMilkTea() {
        if (count == 0) {
            System.out.println("菜单为空！");
            return;
        }

        System.out.print("输入要删除的奶茶编号：");
        String targetId = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (menu[i].id.equals(targetId)) {
                for (int j = i; j < count - 1; j++) {
                    menu[j] = menu[j + 1];
                }
                count--;
                System.out.println("删除成功！");
                return;
            }
        }
        System.out.println("未找到该奶茶！");
    }

    // 修改奶茶信息
    private static void updateMilkTea() {
        if (count == 0) {
            System.out.println("菜单为空！");
            return;
        }

        System.out.print("输入要修改的奶茶编号：");
        String targetId = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (menu[i].id.equals(targetId)) {
                System.out.println("当前信息：" + menu[i]);
                System.out.print("输入新名称（直接回车不修改）：");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    menu[i].name = name;
                }
                System.out.print("输入新价格（直接回车不修改）：");
                String priceStr = scanner.nextLine();
                if (!priceStr.isEmpty()) {
                    menu[i].price = Double.parseDouble(priceStr);
                }
                System.out.println("修改成功！");
                return;
            }
        }
        System.out.println("未找到该奶茶！");
    }

    // 按价格排序
    private static void sortByPrice() {
        if (count == 0) {
            System.out.println("菜单为空！");
            return;
        }

        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (menu[j].price > menu[j + 1].price) {
                    MilkTea temp = menu[j];
                    menu[j] = menu[j + 1];
                    menu[j + 1] = temp;
                }
            }
        }
        System.out.println("排序完成！");
        showMenu();
    }

    // 显示统计信息
    private static void showStatistics() {
        if (count == 0) {
            System.out.println("菜单为空！");
            return;
        }

        double total = 0;
        double maxPrice = menu[0].price;
        double minPrice = menu[0].price;

        for (int i = 0; i < count; i++) {
            total += menu[i].price;
            if (menu[i].price > maxPrice) {
                maxPrice = menu[i].price;
            }
            if (menu[i].price < minPrice) {
                minPrice = menu[i].price;
            }
        }

        double avgPrice = total / count;

        System.out.println("\n===== 统计信息 =====");
        System.out.println("奶茶总数：" + count);
        System.out.println("平均价格：" + String.format("%.2f", avgPrice) + "元");
        System.out.println("最高价格：" + maxPrice + "元");
        System.out.println("最低价格：" + minPrice + "元");
    }

    // 显示所有用户（仅管理员可用）
    private static void showAllUsers() {
        if (userCount == 0) {
            System.out.println("暂无用户！");
            return;
        }

        System.out.println("\n===== 所有用户列表 =====");
        System.out.println("总用户数：" + userCount);
        System.out.println("----------------------------------------");
        for (int i = 0; i < userCount; i++) {
            String userType = users[i].type.equals("customer") ? "顾客" :
                    users[i].type.equals("admin") ? "管理员" : "员工";
            System.out.println((i + 1) + ". 用户名：" + users[i].username +
                    " | 类型：" + userType);
        }
        System.out.println("----------------------------------------");
    }
}

