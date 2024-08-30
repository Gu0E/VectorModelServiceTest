import javax.rmi.CORBA.Util;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ListToTxt {
    public static void main(String[] args) {
        List<String> texts = Utils.getTexts(Utils.readTxt(Utils.getPath()));
        try {
            FileWriter writer = new FileWriter("texts.txt");
            for(String data : texts) {
                String s = data.replaceAll("\n"," ");
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
