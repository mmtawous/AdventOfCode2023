import java.util.Scanner;

public class Day6p2 {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        scnr.next();

        String line = scnr.nextLine().replace(" ", "");
        long time = Long.parseLong(line);

        scnr.next();

        line = scnr.nextLine().replace(" ", "");
        long dist = Long.parseLong(line);

        int cnt = 0;
        for (int i = 0; i < time; i++) {
            if ((time - i) * i > dist) {
                cnt++;
            }
        }

        System.out.println("Answer is: " + cnt);

        scnr.close();
    }

}