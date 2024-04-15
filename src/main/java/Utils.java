import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 第一步：取txt文件的每一行组成List<String> lines
 * 第二步：拼接lines的每一行的name+paths+chapterTitle+paragraphs组成String text，返回List<String> texts
 * 第三步：取texts的每一行与type组成JSON字符串
 * 第四步：post过去，返回result字符串
 * 第五步：result字符串取success和向量为空的作为成功，其余的不记录
 */

public class Utils {
    private static final Properties properties;//读配置路径

    static {
        properties = new Properties();
        InputStream inputStream = Utils.class.getClassLoader()
                .getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取本地txt的每一行作为query
     */
    public static List<String> readTxt(String filePath) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filePath));
            String oneLine;
            while ((oneLine = reader.readLine()) != null) {
                lines.add(oneLine);
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 切出JSON字符串中的question作为新列表
     */
    public static List<String> getTexts(List<String> strings) {
        List<String> texts = new ArrayList<>();
        for (String line : strings) {
            StringBuilder sb = new StringBuilder();
            JSONObject js = JSON.parseObject(line);
            String name = js.getString("name");
            //该写法会报警告
            // List<String> paths = (List<String>) js.get("paths");
            //没有警告的写法
            String paths = js.getString("paths");
            List<String> pathList = JSONArray.parseArray(paths, String.class);

            String chapterTitle = js.getString("chapterTitle");

            String paragraphs = js.getString("paragraphs");
            List<String> paragraphList = JSONArray.parseArray(paragraphs, String.class);

            sb.append(name);
            if (paths != null && !paths.isEmpty()) {
                String tmp = String.join(" ", pathList);
                sb.append(" ").append(tmp);
            }
            if (chapterTitle != null && !chapterTitle.isEmpty()) {
                sb.append(" ").append(chapterTitle);
            }
            if (paragraphs != null && !paragraphs.isEmpty()) {
                String tmp = String.join(" ", paragraphList);
                sb.append(" ").append(tmp);
            }
            texts.add(sb.toString());
        }
        return texts;
    }

    /**
     * 检查result是否有效
     */
    public static boolean valid(JSONObject result) {
        String success = result.getString("success");
        String embedding = result.getString("embedding");
        if (success == null || success.equals("false")) return false;
        return embedding != null && !embedding.isEmpty();
    }

    /**
     * 请求body存入哈希表
     */
    public static Map<String, String> getParamMap(String text, String type) {
        Map<String, String> param = new HashMap<>();
        param.put("text", text);
        param.put("type", type);
        return param;
    }

    /**
     * 哈希表设置请求body
     */
    public static String setRequestBody(String text, String type) {
        Map<String, String> param = getParamMap(text, type);
        return JSON.toJSONString(param);
    }

    public static String getUrl() {
        return properties.getProperty("Post.url");
    }

    public static String getPath() {
        return properties.getProperty("txt.path");
    }

    public static String getType() {
        return properties.getProperty("TYPE");
    }

    public static double fixAverage(List<Long> results) {
        long sum = 0L;
        int cnt = 0;
        for (Long result : results) {
            if (result != null) {
                sum += result;
                cnt++;
            }
        }
        return (double) sum / cnt;
    }

    public static int countNull(List<Long> results) {
        int cnt = 0;
        for (Long result : results) {
            if (result == null) cnt++;
        }
        return cnt;
    }
}



