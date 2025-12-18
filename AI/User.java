import java.util.Date;

public class User {
    private String userId;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private Date createTime;

    public User() {
        this.createTime = new Date();
    }

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.createTime = new Date();
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    // 业务方法
    /**
     * 用户登录验证
     * @param inputPassword 输入的密码
     * @return 验证结果
     */
    public boolean login(String inputPassword) {
        return this.password != null && this.password.equals(inputPassword);
    }

    /**
     * 更新用户个人信息
     * @param phoneNumber 新的联系电话
     * @param email 新的邮箱地址
     */
    public void updateProfile(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    /**
     * 验证密码是否正确
     * @param inputPassword 输入的密码
     * @return 验证结果
     */
    public boolean validatePassword(String inputPassword) {
        return this.password != null && this.password.equals(inputPassword);
    }

}
