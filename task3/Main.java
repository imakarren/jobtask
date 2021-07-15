import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

          System.out.print(mostBusyInterval(filesToMass(args[0])));

    }

    private static int mostBusyInterval (float[][] data) {
        float temp, max = 0f;
        int maxNum = 0;
        for (int i = 0; i < 16; i++) {
            temp = 0f;
            for (int j = 0; j < 5; j++)
                temp += data[i][j];
            if (temp > max ) {
                max = temp;
                maxNum = i;
                }
            }
        return maxNum + 1;
    }

    private static float[][] filesToMass (String pathFolder) throws IOException {
        String[] path = new String[5];
        float[][] buf = new float[16][5];
        for (int i = 1; i <= 5; i++) {
            path[i-1] = pathFolder + File.separator + "Cash" + i + ".txt";
            File file = new File(path[i-1]);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int counter = 0;
            while (bufferedReader.ready()) {
                buf[counter++][i-1] = Float.parseFloat(bufferedReader.readLine());
            }
        }
        return buf;
    }

}
