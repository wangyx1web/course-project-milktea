import java.util.List;


public interface UserService {
    boolean register(User user);
    // 登录
    User login(String username, String password);
    // 更新用户信息
    boolean updateUserInfo(String username, String newPhone, String newEmail);
    // 查询所有用户
    List<User> getAllUsers();
}

