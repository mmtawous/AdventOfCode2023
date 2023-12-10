import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        int sum = 0;

        while (scnr.hasNextLine()) {
            String[] line = scnr.nextLine().split(" ");

            List<Integer> nums = new ArrayList<>(line.length);
            List<Integer> ends = new ArrayList<>(line.length);
            

            for (int i = 0; i < line.length; i++) {
                nums.add(Integer.parseInt(line[i]));
            }

            List<Integer> temp = new ArrayList<>(nums);

            boolean finished = false;
            while (!finished) {
                for (int i = 0; i < temp.size() - 1; i++) {
                    temp.set(i, temp.get(i + 1) - temp.get(i));
                }
                temp.remove(temp.size() - 1);
                ends.add(temp.get(temp.size() - 1));

                finished = true;
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i) != 0) {
                        finished = false;
                        break;
                    }
                } 
            }

            int accum = ends.get(ends.size() - 2);
            for (int i = ends.size() - 3; i >= 0; i--) {
                accum += ends.get(i);
            }

            sum += accum + nums.get(nums.size() - 1);
        }
       

        System.out.println("Answer is: " + sum);

        scnr.close();
    }
}