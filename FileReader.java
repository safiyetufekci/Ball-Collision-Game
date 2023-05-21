import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class FileReader {
        public static String[][] fileReader (String file_name) { // txt to array

        List<String> infos = null; // initializing list
        String[][] array = null; // initializing array
        try {
            infos = Files.readAllLines(Paths.get(file_name)); // now whole text is in the infos line by line
            int row = infos.size();
            int column =0;

            for(String i : infos) {
                if (column ==0 || column > i.length()-i.replace(" ","").length()+1) { // how many columns do we need
                    column = i.length()-i.replace(" ","").length()+1; // dropping space characters to not counts number of columns
                }
                else {
                    break;
                }
            }
            array = new String[row][column]; // decide 2D array's sizes
            for(int x=0; x<infos.size();x++) {
                String[] data2 = infos.get(x).split(" "); // split the letters by space characters
                for(int y=0; y<data2.length ; y++) {
                    array[x][y] = data2[y]; // filling array
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }
}