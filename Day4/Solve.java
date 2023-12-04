import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scnr = new Scanner(System.in);
        int sum = 0;

        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            line = line.split(":")[1];
            String[] data = line.split("\\|");
            
            Scanner winning = new Scanner(data[0]);
            Scanner nums = new Scanner(data[1]);

            HashSet<Integer> winningSet = new HashSet<Integer>();
            HashSet<Integer> numSet = new HashSet<Integer>();

            while (true) {
                boolean reading = false;
                if (winning.hasNextInt()) {
                    winningSet.add(winning.nextInt());
                    reading = true;
                }

                if (nums.hasNextInt()) {
                    numSet.add(nums.nextInt());
                    reading = true;
                }

                if (!reading)
                    break;

            }

            int cnt = 0;
            for (Integer i : numSet) {
                if (winningSet.contains(i))
                    cnt++;
            }

            if (cnt > 0)
                sum += Math.pow(2, cnt - 1);

        }

        System.out.println("Answer is: " + sum);

        scnr.close();
    }

}