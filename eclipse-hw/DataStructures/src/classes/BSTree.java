package classes;


public class BSTree<E> {
    public class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }

        Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    Node<E> root;

    public BSTree(E elem){
        root = new Node<E>(elem);
    }

    public Node<E> getRoot(){
        return root;
    }

    public void add(Node<E> node){
        // Implement Me!
    }

    public boolean remove(E elem){
        // Implement Me!
        return false;
    }

    public void rotateLeft(){
        // Implement Me!
    }

    public void rotateRight(){

    }

    public void print() {
        print("", root, false);
    }

    public void print(String prefix, Node<E> n, boolean isLeft) {
        if (n != null) {
            System.out.println(prefix + (isLeft ? "|-- " : "\\-- ") + n.data);
            print(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }

    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>(5);
        System.out.println(tree.getRoot().data);
        tree.print();
    }
}
