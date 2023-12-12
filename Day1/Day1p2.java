import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day1p2 {
    static String[] dictA = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
    static String[] dictB = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "1", "2", "3",
            "4",
            "5",
            "6", "7", "8", "9" };

    static Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        long sum = 0;
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            int calib = getCalibration(line);
            System.out.println(line + " : " + calib);
            sum += calib;
        }

        System.out.println("Answer is: " + sum);
        scnr.close();
    }

    public static int getCalibration(String line) {
        int firstIdx = -1;
        String first = null;

        int secondIdx = -1;
        String second = null;

        for (int i = 0; i < dictB.length; i++) {
            int f = line.indexOf(dictB[i]);
            int l = line.lastIndexOf(dictB[i]);
            if (f != -1) {
                if (firstIdx == -1 || f < firstIdx) {
                    firstIdx = f;
                    first = dictB[i];
                }

                if (secondIdx == -1 || l > secondIdx) {
                    secondIdx = l;
                    second = dictB[i];
                }
            }
        }

        // Make a two digit num
        int firstI = map.get(first) == null ? Integer.parseInt(first) : map.get(first);
        int secondI = map.get(second) == null ? Integer.parseInt(second) : map.get(second);

        return (firstI * 10) + secondI;
    }
}