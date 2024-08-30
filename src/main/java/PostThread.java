import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class PostThread extends Thread {
    //线程共享变量
    private final String url;
    private final List<String> texts;
    private final List<Long> results;
    private final int index;
    private final int total;


    public PostThread(String url, List<String> texts, List<Long> results, int index, int total) {
        this.url = url;
        this.texts = texts;
        this.results = results;
        this.index = index;
        this.total = total;
    }

    @Override
    public void run() {
        for (int i = index; i < texts.size(); i += total) {
            System.out.println(currentThread().getName() + " 提交的是第 "+ i + " 个任务");
            try {
                String requestBody = Utils.setRequestBody(texts.get(i), Utils.getType());
                long startTime = System.currentTimeMillis();
                String result = PostService.OKPost(requestBody, url);
                long endTime = System.currentTimeMillis();
                JSONObject JSONResult = JSON.parseObject(result);
                if (JSONResult !=null && Utils.valid(JSONResult)) {
                    long costTime = endTime - startTime;
                    System.out.println(this.getName() +
                            " 第 "+ i +" 次提交的问题是: \"" + texts.get(i) +
                            "\" \n\t" +
//                            "本次的result是 " + result +
//                            "\" \n\t" +
                            "本次的耗时是 " + costTime + " ms");
//                    synchronized (PostService.class) {
                        results.add(costTime);
//                    }
                } else {
                    System.out.println(this.getName() +
                            "本次提交的问题是: \"" + texts.get(i) +
                            "\" \n\t" +
                            "本次未接收到响应");
                    results.add(null);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("##### " + this.getName() + " 执行完毕 #####");
    }
}
