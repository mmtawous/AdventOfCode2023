import java.util.Scanner;

public class Day15p1 {

    public static final int SIZE = 100;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        String[] line = scnr.nextLine().split(",");

        int sum = 0;
        for (String s : line) {
            int curr = 0;

            for (char c : s.toCharArray()) {
                curr += c;
                curr *= 17;
                curr %= 256;
            }

            sum += curr;
        }

        System.out.println("Answer is: " + sum);

        scnr.close();
    }
}