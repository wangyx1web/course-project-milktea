public class Employee extends User {
    private String empId; // 工号
    private String position; // 岗位：收银员/店长
    private boolean hasManagePermission; // 是否有奶茶管理权限

    // 构造方法
    public Employee(String userId, String username, String password, String empId, String position) {
        super(userId, username, password);
        this.empId = empId;
        this.position = position;
        // 只有店长有管理权限
        this.hasManagePermission = "店长".equals(position);
    }

    // 校验是否有权限管理奶茶
    public boolean canManageMilkTea() {
        return hasManagePermission;
    }

    // Getter
    public String getEmpId() { return empId; }
    public String getPosition() { return position; }
}
