import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000};

        for (int size : sizes) {
            System.out.println("\n|===| Testing " + size + " elements |===|");

            Integer[] data = generateRandomArray(size);
            Integer[] searchKeys = Arrays.copyOfRange(data, 0, size / 10);
            Integer[] deleteKeys = Arrays.copyOfRange(data, size / 10, size / 5);

            // AVL Tree
            AVLTree<Integer> avl = new AVLTree<>();
            PerformanceTester.test("AVL Tree", avl, data, searchKeys, deleteKeys);

            // Splay Tree
            SplayTree<Integer> splay = new SplayTree<>();
            PerformanceTester.test("Splay Tree", splay, data, searchKeys, deleteKeys);

            // Hash Table (Chaining)
            HashTableChaining<Integer> chaining = new HashTableChaining<>(size);
            PerformanceTester.test("Hash Table (Chaining)", chaining, data, searchKeys, deleteKeys);

            // Hash Table (Quadratic Probing)
            HashTableQuadraticProbing<Integer> probing = new HashTableQuadraticProbing<>(size);
            PerformanceTester.test("Hash Table (Quadratic Probing)", probing, data, searchKeys, deleteKeys);
        }
    }

    private static Integer[] generateRandomArray(int size) {
        Random rand = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < size) {
            set.add(rand.nextInt(size * 10));
        }
        return set.toArray(new Integer[0]);
    }
}
