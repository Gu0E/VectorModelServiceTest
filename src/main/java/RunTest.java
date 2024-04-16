import java.io.IOException;

public class RunTest {
    public static void main(String[] args) throws IOException {
        String[] times = Utils.getThreadCount().split(",");
        for (int i = 0; i < times.length; i++) {
            int curr = Integer.parseInt(times[i]);
            System.out.println("----- 第 " + (i + 1) + " 轮开始执行 -----");
            TestN.test(curr);
            System.out.println("----- 第 " + (i + 1) + " 轮执行完毕 -----");
            System.out.println();
        }
    }
}
