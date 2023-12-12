import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day7p2 {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        List<String> hands = new ArrayList<String>();
        HashMap<String, Integer> bids = new HashMap<String, Integer>();

        while (scnr.hasNextLine()) {
            String[] line = scnr.nextLine().split(" ");

            hands.add(line[0]);
            bids.put(line[0], Integer.parseInt(line[1]));
        }

        Collections.sort(hands, new HandCompare());

        for (int i = 0; i < hands.size(); i++) {
            String s = hands.get(i);
            int eval1 = HandCompare.evaluateHand(s);

            if (eval1 == 8) {
                System.out.println("Five of a kind: " + s);
            } else if (eval1 == 7) {
                System.out.println("Four of a kind: " + s);
            } else if (eval1 == 6) {
                System.out.println("Full house: " + s);
            } else if (eval1 == 5) {
                System.out.println("Three of a kind: " + s);
            } else if (eval1 == 4) {
                System.out.println("Two pair: " + s);
            } else if (eval1 == 3) {
                System.out.println("One pair: " + s);
            } else {
                System.out.println("High: " + s);
            }
        }

        int result = 0;

        for (int i = 0; i < hands.size(); i++) {
            int bid = bids.get(hands.get(i));

            result += bid * (i + 1);
        }

        System.out.println("Answer is: " + result);

        scnr.close();
    }

    private static class HandCompare implements Comparator<String> {
        private static final String ORDER = "J23456789TQKA";

        @Override
        public int compare(String o1, String o2) {
            int eval1 = evaluateHand(o1);
            int eval2 = evaluateHand(o2);

            if (eval1 == eval2) {
                for (int i = 0; i < o1.length(); i++) {
                    char card1 = o1.charAt(i);
                    char card2 = o2.charAt(i);

                    int index1 = ORDER.indexOf(card1);
                    int index2 = ORDER.indexOf(card2);

                    // Compare based on the index in the ORDER string
                    int comparison = Integer.compare(index1, index2);
                    if (comparison != 0) {
                        return comparison;
                    }
                }

                return 0;
            } else {
                return Integer.compare(eval1, eval2);
            }

        }

        public static int evaluateHand(String hand) {
            char[] cards = hand.toCharArray();

            HashMap<Character, Integer> map = new HashMap<>();

            int jCnt = 0;
            int maxCnt = 1;
            char maxCh = cards[0];
            for (int c = 0; c < cards.length; c++) {
                Integer f = map.get(cards[c]);
                char ch = cards[c];

                if (ch == 'J')
                    jCnt++;

                else {
                    if (maxCh == 'J') {
                        maxCh = ch;
                        maxCnt = 1;
                    }

                    if (f == null)
                        map.put(ch, 1);
                    else {
                        map.put(ch, f + 1);

                        if (f + 1 > maxCnt) {
                            maxCnt = f + 1;
                            maxCh = ch;
                        }
                    }
                }
            }

            for (int i = 0, j = jCnt; i < cards.length && j != 0; i++) {
                if (cards[i] == 'J') {
                    cards[i] = maxCh;
                    j--;
                }
            }

            // If maxCh was J then we must have had "JJJJJ"
            if (maxCh == 'J')
                return 8;

            // Update the most common freq
            map.put(maxCh, map.get(maxCh) + jCnt);

            Integer[] arr = map.values().toArray(new Integer[5]);

            int[] freq = new int[5];

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    break;
                } else {
                    freq[i] = arr[i];
                }
            }

            Arrays.sort(freq);

            if (freq[0] == 0 && freq[1] == 0 && freq[2] == 0 && freq[3] == 0 && freq[4] == 5) {
                return 8; // Five of a kind
            } else if (freq[0] == 0 && freq[1] == 0 && freq[2] == 0 && freq[3] == 1 && freq[4] == 4) {
                return 7; // Four of a kind
            } else if (freq[0] == 0 && freq[1] == 0 && freq[2] == 0 && freq[3] == 2 && freq[4] == 3) {
                return 6; // Full house
            } else if (freq[0] == 0 && freq[1] == 0 && freq[2] == 1 && freq[3] == 1 && freq[4] == 3) {
                return 5; // Three of a kind
            } else if (freq[0] == 0 && freq[1] == 0 && freq[2] == 1 && freq[3] == 2 && freq[4] == 2) {
                return 4; // Two pair
            } else if (freq[0] == 0 && freq[1] == 1 && freq[2] == 1 && freq[3] == 1 && freq[4] == 2) {
                return 3; // One pair
            } else {
                return 2; // High card
            }
        }

    }

}