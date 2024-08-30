import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class ExcelTest {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("results.xls");
            writer.write("\t");
            writer.write("AverageTime\t");
            writer.write("Success\t");
            writer.write("Failed\n");
            String[] times = Utils.getThreadCount().split(",");
            int n = 2;
            while (n-- > 0) {
                FixResult[] results = new FixResult[times.length];
                for (int i = 0; i < times.length; i++) {
                    int curr = Integer.parseInt(times[i]);
                    System.out.println("----- 第 " + (i + 1) + " 轮开始执行 -----");
                    results[i] = TestN.test(curr);
                    System.out.println("----- 第 " + (i + 1) + " 轮执行完毕 -----");
                    System.out.println();
                }
                for (int i = 0; i < results.length; i++) {
                    System.out.printf("%s 并发结果：成功 %d 个，失败 %d 个\n", times[i], results[i].cnt, results[i].total - results[i].cnt);
                    System.out.println(times[i] + " 并发平均用时: " + results[i].ave + " ms");
                    writer.write(times[i] + " Threads\t");
                    writer.write(String.format("%.2f",results[i].ave) + "\t");
                    writer.write(results[i].cnt + "\t");
                    writer.write((results[i].total - results[i].cnt) + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
