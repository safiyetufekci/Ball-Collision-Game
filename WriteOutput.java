import java.io.FileWriter;
import java.io.IOException;

public class WriteOutput {
    public static void write(String text){ // to print to directly output.txt
        FileWriter writer = null;
        try {
            writer = new FileWriter("output.txt",true);
            writer.write(text);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}
