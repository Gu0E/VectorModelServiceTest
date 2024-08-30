import java.io.IOException;
import java.util.Scanner;

public class RunTest {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入本次希望测试的线程数:");
        Scanner in = new Scanner(System.in);
        int threadsCnt = in.nextInt();
        FixResult result = TestN.test(threadsCnt);
        System.out.printf("%s 并发结果：成功 %d 个，失败 %d 个\n", threadsCnt, result.cnt, result.total - result.cnt);
        System.out.println(threadsCnt + " 并发平均用时: " + result.ave + " ms");
//        String[] times = Utils.getThreadCount().split(",");
//        FixResult[] results = new FixResult[times.length];
//        for (int i = 0; i < times.length; i++) {
//            int curr = Integer.parseInt(times[i]);
//            System.out.println("----- 第 " + (i + 1) + " 轮开始执行 -----");
//            results[i] = TestN.test(curr);
//            System.out.println("----- 第 " + (i + 1) + " 轮执行完毕 -----");
//            System.out.println();
//        }
//        for (int i = 0; i < results.length; i++) {
//            System.out.printf("%s 并发结果：成功 %d 个，失败 %d 个\n", times[i], results[i].cnt, results[i].total - results[i].cnt);
//            System.out.println(times[i] + " 并发平均用时: " + results[i].ave + " ms");
//        }
    }
}
