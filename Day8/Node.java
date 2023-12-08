public class Node {
    private String name;
    private Node left, right;

    public Node(String name, Node left, Node right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getName() {
        return name;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        String result = "[" + "name=" + name + ", left=" + left.getName() + ", right=" + right.getName() + "]";
        return result;
    }
}