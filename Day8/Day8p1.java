import java.util.HashMap;
import java.util.Scanner;

public class Day8p1 {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        String moves = scnr.nextLine();

        scnr.nextLine(); // Skip empty line

        HashMap<String, Node> nodes = new HashMap<>();

        // Read in nodes
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();

            String[] parts = line.split(" = ");

            String name = parts[0];

            String[] leftAndRight = parts[1].replace("(", "").replace(")", "").split(", ");

            String left = leftAndRight[0];
            String right = leftAndRight[1];

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
            }
        }

        // Traverse the graph.

        String currName = "AAA";
        Node currNode = nodes.get("AAA");
        long steps = 0;
        while (!currName.equals("ZZZ")) {
            if (moves.charAt((int) (steps % moves.length())) == 'L') {
                currName = currNode.getLeft().getName();
                currNode = currNode.getLeft();
            } else {
                currName = currNode.getRight().getName();
                currNode = currNode.getRight();
            }
            steps++;
        }

        System.out.println("Answer is: " + steps);

        scnr.close();
    }
}