public class AVLTree<T extends Comparable<T>> implements DataStructure<T> {
    private class Node {
        T key;
        Node left, right;
        int height;

        Node(T key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root;

    private int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    private int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    private Node rightRotate(Node y) { 
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    private Node insert(Node node, T key) { // insert a node
        if (node == null) return new Node(key);
        if (key.compareTo(node.key) < 0) // insert in left subtree
            node.left = insert(node.left, key);
        else if (key.compareTo(node.key) > 0) // insert in right subtree
            node.right = insert(node.right, key);
        else return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        
        if (balance > 1 && key.compareTo(node.left.key) < 0) // left left case
            return rightRotate(node);
        if (balance < -1 && key.compareTo(node.right.key) > 0) // right right case
            return leftRotate(node);
        if (balance > 1 && key.compareTo(node.left.key) > 0) { // left right case
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) < 0) { // right left case
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private Node delete(Node root, T key) {
        if (root == null) return root;
        if (key.compareTo(root.key) < 0)
            root.left = delete(root.left, key);
        else if (key.compareTo(root.key) > 0)
            root.right = delete(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;
                if (temp == null) return null;
                root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    private boolean contains(Node node, T key) {
        if (node == null) return false;
        if (key.equals(node.key)) return true;
        return key.compareTo(node.key) < 0 ? contains(node.left, key) : contains(node.right, key);
    }

    @Override
    public void insert(T key) {
        root = insert(root, key);
    }

    @Override
    public void delete(T key) {
        root = delete(root, key);
    }

    @Override
    public boolean contains(T key) {
        return contains(root, key);
    }
}
