import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Solve {

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
        private static final String ORDER = "23456789TJQKA";

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

            if (isFiveOfAKind(cards)) {
                return 8; // Five of a kind
            } else if (isFourOfAKind(cards)) {
                return 7; // Four of a kind
            } else if (isFullHouse(cards)) {
                return 6; // Full house
            } else if (isThreeOfAKind(cards)) {
                return 5; // Three of a kind
            } else if (isTwoPair(cards)) {
                return 4; // Two pair
            } else if (isOnePair(cards)) {
                return 3; // One pair
            } else {
                return 2; // High card
            }
        }

        private static boolean isFiveOfAKind(char[] cards) {
            HashSet<Character> char_set = new HashSet<>();

            // Inserting character of String into set
            for (int c = 0; c < cards.length; c++) {
                char_set.add(cards[c]);
            }

            // If length of set is equal to len of String
            // then it will have unique characters
            return char_set.size() == 1;
        }

        private static boolean isFourOfAKind(char[] cards) {
            HashMap<Character, Integer> map = new HashMap<>();

            // Inserting character of String into set
            for (int c = 0; c < cards.length; c++) {
                if (map.get(cards[c]) == null)
                    map.put(cards[c], 1);
                else
                    map.put(cards[c], map.get(cards[c]) + 1);
            }

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

            return freq[0] == 0 && freq[1] == 0 && freq[2] == 0 && freq[3] == 1 && freq[4] == 4;
        }

        private static boolean isFullHouse(char[] cards) {
            HashMap<Character, Integer> map = new HashMap<>();

            // Inserting character of String into set
            for (int c = 0; c < cards.length; c++) {
                if (map.get(cards[c]) == null)
                    map.put(cards[c], 1);
                else
                    map.put(cards[c], map.get(cards[c]) + 1);
            }

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

            return freq[0] == 0 && freq[1] == 0 && freq[2] == 0 && freq[3] == 2 && freq[4] == 3;
        }

        private static boolean isThreeOfAKind(char[] cards) {
            HashMap<Character, Integer> map = new HashMap<>();

            // Inserting character of String into set
            for (int c = 0; c < cards.length; c++) {
                if (map.get(cards[c]) == null)
                    map.put(cards[c], 1);
                else
                    map.put(cards[c], map.get(cards[c]) + 1);
            }

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

            return freq[0] == 0 && freq[1] == 0 && freq[2] == 1 && freq[3] == 1 && freq[4] == 3;

        }

        private static boolean isTwoPair(char[] cards) {
            HashMap<Character, Integer> map = new HashMap<>();

            // Inserting character of String into set
            for (int c = 0; c < cards.length; c++) {
                if (map.get(cards[c]) == null)
                    map.put(cards[c], 1);
                else
                    map.put(cards[c], map.get(cards[c]) + 1);
            }

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

            return freq[0] == 0 && freq[1] == 0 && freq[2] == 1 && freq[3] == 2 && freq[4] == 2;
        }

        private static boolean isOnePair(char[] cards) {
            HashSet<Character> char_set = new HashSet<>();

            // Inserting character of String into set
            for (int c = 0; c < cards.length; c++) {
                char_set.add(cards[c]);
            }

            // If length of set is equal to len of String
            // then it will have unique characters
            return char_set.size() == 4;
        }

    }

}