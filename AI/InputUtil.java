import java.util.Scanner;

public class InputUtil {
    private static Scanner sc = new Scanner(System.in);

    // 读取字符串（处理换行符问题）
    public static String inputString(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        // 处理空输入
        while (input.isEmpty()) {
            System.out.print("输入不能为空！" + prompt);
            input = sc.nextLine().trim();
        }
        return input;
    }

    // 读取整数
    public static int inputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效数字！");
            }
        }
    }

    // 读取小数
    public static double inputDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效小数！");
            }
        }
    }

    // 关闭Scanner
    public static void close() {
        sc.close();
    }
}
