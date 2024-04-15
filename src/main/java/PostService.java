import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * post方法：传入String body与url，返回HttpResponse响应的内容String
 */
public class PostService {
    public static String OKPost(String body, String url) throws IOException {
        //设置header和body，生成request
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json;charset=UTF-8"), body);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();
        ResponseBody responseBody = response.body();
        if (!response.isSuccessful()) {
            System.out.println("Request failed");
        }
        if (responseBody != null) {
            return responseBody.string();
        } else return null;
    }
    public static String ApachePost(String requestBody, String url) throws Exception {
        String result;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(10000).
                setConnectionRequestTimeout(10000).
                setSocketTimeout(10000).
                build();
        /* HttpPost */
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));

        /* HttpResponse */
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        HttpEntity httpEntity = httpResponse.getEntity();
        result = EntityUtils.toString(httpEntity, "utf-8");

        EntityUtils.consume(httpEntity);

        httpResponse.close();

        return result;
    }
}
