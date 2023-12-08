import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Solve2 {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        String moves = scnr.nextLine();

        scnr.nextLine(); // Skip empty line

        HashMap<String, Node> nodes = new HashMap<>();
        HashSet<Node> starting = new HashSet<>();
        HashSet<String> ending = new HashSet<>();

        // Read in nodes
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();

            String[] parts = line.split(" = ");

            String name = parts[0];

            String[] leftAndRight = parts[1].replace("(", "").replace(")", "").split(", ");

            String left = leftAndRight[0];
            String right = leftAndRight[1];

            // Add to starting or ending
            if (name.charAt(2) == 'Z') {
                ending.add(name);
            }

            // Find left and right nodes. Create them if needed.
            Node leftNode;
            if (nodes.get(left) != null) {
                leftNode = nodes.get(left);
            } else {
                leftNode = new Node(left, null, null);
                nodes.put(left, leftNode);
            }

            Node rightNode;
            if (nodes.get(right) != null) {
                rightNode = nodes.get(right);
            } else {
                rightNode = new Node(right, null, null);
                nodes.put(right, rightNode);
            }

            // If the node already exists then set its left and right
            if (nodes.get(name) != null) {
                Node node = nodes.get(name);

                node.setLeft(leftNode);
                node.setRight(rightNode);
            } else {
                Node node = new Node(name, leftNode, rightNode);
                nodes.put(name, node);

                if (name.charAt(2) == 'A') {
                    starting.add(node);
                }
            }
        }

        // Traverse the graph.
        Node[] currNodesArr = new Node[starting.size()];
        currNodesArr = starting.toArray(currNodesArr);

        List<Node> currNodes = new ArrayList<>(Arrays.asList(currNodesArr));
        List<Long> cycleSize = new ArrayList<>(currNodes.size());

        long steps = 0;
        while (!currNodes.isEmpty()) {
            if (moves.charAt((int) (steps % moves.length())) == 'L') {
                for (int i = 0; i < currNodes.size(); i++) {
                    currNodes.set(i, currNodes.get(i).getLeft());
                }
            } else {
                for (int i = 0; i < currNodes.size(); i++) {
                    currNodes.set(i, currNodes.get(i).getRight());
                }
            }
            steps++;

            // Check if a starting node is done. If so add it to map and remove from list.
            for (int i = 0; i < currNodes.size(); i++) {
                if (ending.contains(currNodes.get(i).getName())) {
                    cycleSize.add(steps);
                    currNodes.remove(i);
                }
            }
        }

        long result = -1;

        System.out.println(cycleSize.toString());

        for (Long i : cycleSize) {
            if (result == -1) {
                result = i;
            } else {
                result = result * i / gcd(result, i);
            }
        }

        System.out.println("Answer is: " + result);

        scnr.close();
    }

    // Stolen courtesy of Baeldung:
    // https://www.baeldung.com/java-greatest-common-divisor
    static long gcd(long n1, long n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }
}