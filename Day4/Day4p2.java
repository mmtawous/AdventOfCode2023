import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day4p2 {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int sum = 0;

        int cardNum = 1;
        HashMap<Integer, Integer> copies = new HashMap<Integer, Integer>();

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

            winning.close();
            nums.close();

            int cnt = 0;
            for (Integer i : numSet) {
                if (winningSet.contains(i))
                    cnt++;
            }

            int copiesOfCurr = copies.get(cardNum) == null ? 1 : copies.get(cardNum) + 1;

            for (int i = 1; i <= cnt; i++) {

                if (copies.get(cardNum + i) == null) {
                    copies.put(cardNum + i, copiesOfCurr);
                } else {
                    copies.put(cardNum + i, copies.get(cardNum + i) + copiesOfCurr);
                }

            }

            System.out.println(copies.toString());
            cardNum++;
        }

        for (Integer i : copies.values()) {
            if (i != null)
                sum += i;
        }

        sum += cardNum;

        System.out.println("Answer is: " + sum);

        scnr.close();
    }

}