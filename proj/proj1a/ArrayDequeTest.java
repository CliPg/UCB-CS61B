

/**
 * @author CliPg
 */
public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<String> array = new ArrayDeque<>();
        array.addFirst("1");
        array.addFirst("2");
        array.addFirst("3");
        array.addFirst("4");
        array.addFirst("5");
        array.addLast("666");
        System.out.println(array.get(6));
        System.out.println(array.removeFirst());
        System.out.println(array.removeFirst());
        System.out.println(array.removeLast());
        System.out.println(array.removeFirst());
        System.out.println(array.removeLast());
        System.out.println(array.removeLast());
        array.printDeque();
        array.isEmpty();
    }
}
