// 用户类
class User {
    String username;  // 用户名
    String password;  // 密码
    String type;      // 用户类型：customer（顾客）或 employee（员工）

    // 构造方法
    User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    // 验证密码
    boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    // 返回用户信息字符串
    public String toString() {
        String typeName = type.equals("customer") ? "顾客" :
                type.equals("admin") ? "管理员" : "员工";
        return "用户名：" + username + " | 类型：" + typeName;
    }
}
