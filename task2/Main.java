package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        float[][] vert = fileToMass(args[0]);
        float[][] point = fileToMass(args[1]);
        for (int i = 0; i < point.length; i++)
            System.out.print(pointPosition(vert, point[i][0],point[i][1]) + "\n");
    }

    private static int pointPosition (float[][] vert, float pointX, float pointY) {
        int i, j, count = 0;
        for (j = 0, i = 3; j < 4; i = j++) {
            if ( (pointX == vert[i][0]) & (pointY == vert[i][1]) )
                return 0;
            else if ( isPointOnBorder(vert, pointX, pointY))
                return 1;
            else if ( ((vert[j][0]-vert[i][0])*(pointY-vert[i][1]) - (vert[j][1]-vert[i][1])*(pointX-vert[i][0])) > 0 )
                count++;
        }
        if (count != 4 & count != 0)
            return 3;
        return 2;
    }

    private static boolean isPointOnBorder (float[][] vert, float pointX, float pointY) {
        int i, j, count = 0, count0 = 0;
        for (j = 0, i = 3; j < 4; i = j++)
            if ( ((vert[j][0]-vert[i][0])*(pointY-vert[i][1]) - (vert[j][1]-vert[i][1])*(pointX-vert[i][0])) == 0 )
                count0++;
            else if ( ((vert[j][0]-vert[i][0])*(pointY-vert[i][1]) - (vert[j][1]-vert[i][1])*(pointX-vert[i][0])) > 0 )
                count++;
        if (count0 == 1 && (count == 3 | count == 0) )
            return true;

        return false;
    }

    private static float[][] fileToMass (String path) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int counter = 0;
        String[] splitLine;
        float[][] buf = new float[100][2];

        while (bufferedReader.ready()) {
             splitLine = bufferedReader.readLine().split(" ");

             for (int j = 0; j < 2; j++)
                 buf[counter][j] = Float.parseFloat(splitLine[j]);

             counter++;
        }

        if (counter < buf.length) {
            buf = Arrays.copyOf(buf,counter);
        }

        return buf;
    }
}
