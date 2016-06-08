import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kong on 2016/3/15.
 */
public class AddArea {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("e://area.txt"), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(isr);
        String lineTxt = null;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            String[] word = lineTxt.split("\t");
            if (word.length == 1) {
                if (word[0].equals("")) continue;
                System.out.println(word[0]);
            } else if (word.length > 1) {
                for (int i = 0; i < word.length; i++) {
                    System.out.println(word[i]);
                }
            }
        }
        isr.close();
    }
}
