import java.io.IOException;

public class RunTest {
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 3 ; i++) {
//            System.out.println();
            System.out.println("----- 第 "+ (i + 1) +" 轮开始执行 -----");
            TestN.test(2);
            System.out.println("----- 第 "+ (i + 1) +" 轮执行完毕 -----");
            System.out.println();
        }
    }
}
