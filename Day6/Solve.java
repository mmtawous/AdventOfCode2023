import java.util.Scanner;

public class Solve {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        int[] times = new int[4];

        scnr.next();

        for (int i = 0; i < 4; i++) {
            times[i] = scnr.nextInt();
        }

        int[] dists = new int[4];

        scnr.next();

        for (int i = 0; i < 4; i++) {
            dists[i] = scnr.nextInt();
        }

        long result = 1;
        for (int i = 0; i < 4; i++) {
            int cnt = 0;
            for (int j = 0; j < times[i]; j++) {
                if ((times[i] - j) * j > dists[i]) {
                    cnt++;
                }
            }
            result *= cnt;
        }

        System.out.println("Answer is: " + result);

        scnr.close();
    }

}