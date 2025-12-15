import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean register(User user) {
        if (userMap.containsKey(user.getUsername())) {
            System.out.println("用户名已存在！");
            return false;
        }
        userMap.put(user.getUsername(), user);
        System.out.println("注册成功！用户名：" + user.getUsername());
        return true;
    }

    @Override
    public User login(String username, String password) {
        User user = userMap.get(username);
        if (user != null && user.login(password)) {
            System.out.println("登录成功！欢迎：" + username);
            return user;
        }
        System.out.println("用户名或密码错误！");
        return null;
    }

    @Override
    public boolean updateUserInfo(String username, String newPhone, String newEmail) {
        User user = userMap.get(username);
        if (user == null) {
            System.out.println("用户不存在！");
            return false;
        }
        user.updateProfile(newPhone, newEmail);
        System.out.println("信息更新成功！");
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    // 扩展：根据用户名获取用户（方便后续强转）
    public User getUserByUsername(String username) {
        return userMap.get(username);
    }
}
