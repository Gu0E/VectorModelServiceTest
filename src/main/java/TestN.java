import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestN {
    public static void test(int n) throws IOException {
        //读取配置文件
        String url = Utils.getUrl();
        String path = Utils.getPath();
        //读取问题列表
        List<String> texts = Utils.getTexts(Utils.readTxt(path));
//        List<String> texts10 = texts.subList(0, 10);
        List<Long> results = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Thread myThread = new PostThread(url, texts, results, i, n);
            myThread.setName("线程" + i);
            threads.add(myThread);
        }
        for (Thread t : threads) t.start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        double ave = Utils.fixAverage(results);
        int failed = Utils.countNull(results);
        System.out.printf("执行完毕，成功 %d 个，失败 %d 个%n",results.size()-failed,failed);
        System.out.println();
        System.out.println("平均用时: " + ave + " ms");
    }
}

