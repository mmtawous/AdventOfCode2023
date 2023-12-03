import java.util.Scanner;

public class Solve2 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        long sum = 0;
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine().split(":")[1];
            String[] sets = line.split(";");

            long maxR = 0;
            long maxG = 0;
            long maxB = 0;

            for (String s : sets) {
                String[] colors = s.split(",");

                for (String c : colors) {
                    Scanner cScanner = new Scanner(c);
                    int val = cScanner.nextInt();
                    String color = cScanner.next();
                    cScanner.close();

                    if (color.equals("red") && val > maxR) {
                        maxR = val;
                    } else if (color.equals("green") && val > maxG) {
                        maxG = val;
                    } else if (color.equals("blue") && val > maxB) {
                        maxB = val;
                    }
                }
            }

            sum += (maxR * maxG * maxB);
        }

        System.out.println("Answer is: " + sum);

        scnr.close();
    }
}