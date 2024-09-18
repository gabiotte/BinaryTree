public class Node<TYPE> {
    private TYPE data;
    private Node<TYPE> right;
    private Node<TYPE> left;

    public Node(TYPE data) {
        this.data = data;
        right = null;
        left = null;
    }

    // Getters e setters:
    public TYPE getData() {
        return data;
    }
    public void setData(TYPE data) {
        this.data = data;
    }

    public Node<TYPE> getLeft() {
        return left;
    }

    public void setLeft(Node<TYPE> left) {
        this.left = left;
    }

    public Node<TYPE> getRight() {
        return right;
    }

    public void setRight(Node<TYPE> right) {
        this.right = right;
    }
}
