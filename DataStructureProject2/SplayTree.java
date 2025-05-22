public class SplayTree<T extends Comparable<T>> implements DataStructure<T> {
    private class Node {
        T key;
        Node left, right;

        Node(T key) {
            this.key = key;
        }
    }

    private Node root;

    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    private Node splay(Node root, T key) { // splay the tree to bring the key to the root
        if (root == null || root.key.equals(key)) return root;

        if (key.compareTo(root.key) < 0) { 
            if (root.left == null) return root;
            if (key.compareTo(root.left.key) < 0) { // left left case
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            } else if (key.compareTo(root.left.key) > 0) { // left right case
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null) // right rotate the left child
                    root.left = leftRotate(root.left);
            }
            return (root.left == null) ? root : rightRotate(root);
        } else {
            if (root.right == null) return root;
            if (key.compareTo(root.right.key) > 0) { // right right case
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            } else if (key.compareTo(root.right.key) < 0) { // right left case
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null) // left rotate the right child
                    root.right = rightRotate(root.right);
            }
            return (root.right == null) ? root : leftRotate(root);
        }
    }

    @Override
    public void insert(T key) { // insert the key into the tree
        if (root == null) {
            root = new Node(key);
            return;
        }
        root = splay(root, key);
        if (root.key.equals(key)) return;

        Node newNode = new Node(key);
        if (key.compareTo(root.key) < 0) { // insert to left
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        } else { // insert to right
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }
        root = newNode;
    }

    @Override
    public void delete(T key) { // delete the key from the tree
        if (root == null) return;
        root = splay(root, key);
        if (!root.key.equals(key)) return;

        if (root.left == null) { // if no left child, just set root to right child
            root = root.right;
        } else { // if has left child, splay the left subtree and attach right child
            Node temp = root.right;
            root = root.left;
            root = splay(root, key);
            root.right = temp;
        }
    }

    @Override
    public boolean contains(T key) {
        root = splay(root, key);
        return root != null && root.key.equals(key);
    }
}
