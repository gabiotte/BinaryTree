public class Tree<TYPE extends Comparable> {
    private Node<TYPE> raiz;

    public Tree() {
        this.raiz = null;
    }

    public Node<TYPE> getRoot() {
        return raiz;
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

    public void remove(TYPE data) {
        raiz = removeNode(raiz, data);
    }

    private Node<TYPE> removeNode(Node<TYPE> currentNode, TYPE data) {
        if (currentNode == null) {
            return null;
        }
        if (data.compareTo(currentNode.getData()) < 0) {
            currentNode.setLeft(removeNode(currentNode.getLeft(), data));
        } else if (data.compareTo(currentNode.getData()) > 0) {
            currentNode.setRight(removeNode(currentNode.getRight(), data));
        } else {
            // Encontramos o nó

            // Nenhum filho:
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
               return null;
            }

            // Um filho:
            if (currentNode.getLeft() != null) {
                return currentNode.getLeft();
            } else if (currentNode.getRight() != null) {
                return currentNode.getRight();
            }

            // Dois filhos:
            // Encontrando o menor valor:
            Node<TYPE> minNode = findMin(currentNode.getRight());
            currentNode.setData(minNode.getData());
            currentNode.setRight(removeNode(currentNode.getRight(), minNode.getData()));
        }

        return currentNode;
    }

    private Node<TYPE> findMin(Node<TYPE> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

}
