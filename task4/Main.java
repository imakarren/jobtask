import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args)  {
        try {
            ArrayList<Interval> result = mostBusyTime(fileToSortedArray(args[0]));
            for (int i = 0; i < result.size(); i++)
                System.out.print(result.get(i).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Interval> mostBusyTime(ArrayList<Interval> arrayList) {
        boolean swap = true;
        while (swap) {
            swap = false;
            int count = arrayList.size();
        for (int i = 0; i <= count - 2; i++) {
            if (arrayList.get(i).intersection(arrayList.get(i + 1))) {
                swap = true;
                if (arrayList.get(i).getEnd().compareTo(arrayList.get(i + 1).getEnd()) < 0)
                    arrayList.add(new Interval(arrayList.get(i + 1).getStart(), arrayList.get(i).getEnd()));
                else
                    arrayList.add(new Interval(arrayList.get(i + 1).getStart(), arrayList.get(i + 1).getEnd()));
            }
        }
        if (swap)
            for (int i = 0; i < count; i++)
                arrayList.remove(0);
        }
        for (int i = 0; i < arrayList.size() - 1; i++)
            if (arrayList.get(i).getEnd().compareTo(arrayList.get(i+1).getStart()) == 0) {
                arrayList.get(i).setEnd(arrayList.get(i + 1).getEnd());
                arrayList.remove(i+1);
                i--;
            }
        return arrayList;
    }

    private static ArrayList<Interval> fileToSortedArray (String path) throws IOException {

        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int counter = 0;
        String[] splitLine;
        ArrayList<Interval> array = new ArrayList();
        int count = 0;
        while (bufferedReader.ready()) {
            splitLine = bufferedReader.readLine().split(" ");
            Interval interval = new Interval(splitLine[0], splitLine[1]);
            array.add(interval);
        }
        Collections.sort(array);
        return array;
    }
}
