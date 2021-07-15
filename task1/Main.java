package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        //System.out.print("Введите полный путь к файлу :");
        //Scanner scan = new Scanner(System.in);
        //args[0] = scan.nextLine();
        int[] buf = fileToSortedMass(args[0]);

        String pattern = "#0.00";
        DecimalFormat df = new DecimalFormat(pattern);

        System.out.print(df.format(percentile90(buf)).replace(",",".") + "\n" + df.format(median(buf)).replace(",",".") + "\n" +
                df.format(max(buf)).replace(",",".") + "\n" + df.format(min(buf)).replace(",",".") + "\n" +
                df.format(average(buf)).replace(",","."));


    }

    /*
     *Represents data from a file as an sorted array.
     */
    private static int[] fileToSortedMass (String path) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int counter = 0;
        int[] buf = new int[1000];

        while (bufferedReader.ready()) {
            buf[counter++] = Integer.parseInt(bufferedReader.readLine());
        }

        if (counter < buf.length) {
            buf = Arrays.copyOf(buf,counter);
        }
        Arrays.sort(buf);
        return buf;
    }

    private static double percentile90 (int[] data) {
        double r = 0.9*(data.length-1);
        int n = (int) Math.floor(r);
        double percentile = data[n] + r%1 * (data[n+1] - data[n]);
        return percentile;
    }

    private static double median (int[] data) {
        double median;
        if (data.length%2 == 0)
            median = (data[data.length/2 - 1] + data[data.length/2])/2;
        else median = data[data.length/2];
        return median;
    }

    private static int max (int[] data) {
        int max = data[0];
        for (int i = 1; i < data.length; i++)
            if (data[i] > max)
                max = data[i];
        return max;
    }

    private static int min (int[] data) {
        int min = data[0];
        for (int i = 1; i < data.length; i++)
            if (data[i] < min)
                min = data[i];
        return min;
    }

    private static double average (int[] data) {
        int sum = 0;
        for (int value : data)
            sum += value;
        return (double) sum/data.length;
    }
}
