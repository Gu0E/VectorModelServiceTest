import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestN {
    public static FixResult test(int n) throws IOException {
        //读取配置文件
        String url = Utils.getUrl();
        String path = Utils.getPath();
        //读取问题列表
        List<String> texts = Utils.getTexts(Utils.readUltimate());
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

        FixResult fixResult = Utils.fixAverage(results);
//        int failed = Utils.countNull(results);
//        System.out.println(results);
//        System.out.println(results.size());
//        System.out.println(texts.size());
//        System.out.printf("执行完毕，成功 %d 个，失败 %d 个\n", fixResult.cnt, texts.size() - fixResult.cnt);
//        System.out.println();
//        System.out.println("平均用时: " + fixResult.ave + " ms");
        return fixResult;
    }
}

