public class Tree<TYPE extends Comparable> {
    private Node<TYPE> raiz;

    public Tree() {
        this.raiz = null;
    }

    public void add(TYPE data) {
        Node<TYPE> newNode = new Node<TYPE>(data);
        if (raiz == null) {
            this.raiz = newNode;
        } else {
            Node<TYPE> currentNode = this.raiz;
            while(true) {
                // compareTo() retorna 0 se for =, 1 se for >, e -1 se for <
                if (newNode.getData().compareTo(currentNode.getData()) < 0) {
                    if (currentNode.getLeft() != null) {
                        currentNode = currentNode.getLeft();
                    } else {
                        currentNode.setLeft(newNode);
                        break;
                    }
                } else {
                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } else {
                        currentNode.setRight(newNode);
                        break;
                    }
                }
            }
        }
    }
}
