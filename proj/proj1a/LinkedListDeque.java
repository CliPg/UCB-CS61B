
/**
 * @author 77507
 */
public class LinkedListDeque<T> {
    public class Node{
        public Node prev;
        public T item;
        public Node next;
        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null,null,null);
        size = 0;
    }

    public LinkedListDeque(T item){
        sentinel = new Node(null,null,null);
        sentinel.next = new Node(null,item,null);
        size = 1;
    }

    public void addFirst(T item){
        //node = 0
        if (sentinel.next == null){
            sentinel.next = new Node(null,item,null);
            size++;
        }
        // node >1

        else if (sentinel.next.prev != null){
            sentinel.next = new Node(sentinel.next.prev,item,sentinel.next);
            sentinel.next.prev.next = sentinel.next;
            sentinel.next.next.prev = sentinel.next;
            size++;
        }
        // node = 1

        else {
            sentinel.next = new Node(null,item,sentinel.next);
            sentinel.next.next.next = sentinel.next;
            sentinel.next.next.prev = sentinel.next;
            sentinel.next.prev = sentinel.next.next;
            size++;
        }
    }

    public void addLast(T item){
        // node = 0
        if (sentinel.next == null){
            sentinel.next = new Node(null,item,null);
            size++;
        }
        // node >1
        else if (sentinel.next.prev != null){
            sentinel.next.prev = new Node(sentinel.next.prev,item,sentinel.next);
            sentinel.next.prev.prev.next = sentinel.next.prev;
            size++;
        }
        //node = 1
        else {
            sentinel.next.next = new Node(sentinel.next,item,sentinel.next);
            sentinel.next.prev = sentinel.next.next;
            size++;
        }
    }

    public boolean isEmpty(){
        return sentinel.next == null;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p = sentinel.next;
        do {
            System.out.println(p.item);
            p = p.next;
        }while (p != sentinel.next);
    }

    public T removeFirst(){
        if (sentinel.next == null){
            return null;
        }else if (sentinel.next.next == null){
            size--;
            T item = sentinel.next.item;
            sentinel.next = null;
            return item;
        }else {
            size--;
            T item = sentinel.next.item;
            sentinel.next.next.prev = sentinel.next.prev;
            sentinel.next.prev.next = sentinel.next.next;
            sentinel.next = sentinel.next.next;
            return item;
        }
    }

    public T removeLast(){
        if (sentinel.next == null){
            return null;
        }else if (sentinel.next.prev == null){
            size--;
            T item = sentinel.next.item;
            sentinel.next = null;
            return item;
        }else {
            size--;
            T item = sentinel.next.prev.item;
            sentinel.next.prev = sentinel.next.prev.prev;
            sentinel.next.prev.next = sentinel.next;
            return item;
        }
    }

    public T get(int index){
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursiveHelp(Node node, int index){
        if (index == 0){
            return node.item;
        }else {
            return getRecursiveHelp(node.next, index-1);
        }
    }
    public T getRecursive(int index){
        if (index < 0 || index >= size){
            return null;
        }else {
            return getRecursiveHelp(sentinel.next,index);
        }
    }
}
