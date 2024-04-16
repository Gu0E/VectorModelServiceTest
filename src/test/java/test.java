import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.List;

/**
 * @author Gu0E
 * @program QueryToReference
 * @description
 * @since 2024/4/11 20:56
 */
public class test {
}

class NThreadTest {
    public static void main(String[] args) {
        try {
            TestN.test(5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class textTest {
    public static void main(String[] args) throws IOException {
        List<String> lines = Utils.readTxt(Utils.getPath());
        List<String> texts = Utils.getTexts(lines);
        for(String line : texts) {
            if(!line.equals(texts.get(0)))continue;
            System.out.println(line);
        }
//        String body = Utils.setRequestBody(texts.get(0), Utils.getType());
//        String result = PostService.OKPost(body, Utils.getUrl());
//        System.out.println(JSON.parseObject(result).getString("success").equals("true"));
//        System.out.println(result);
    }
}