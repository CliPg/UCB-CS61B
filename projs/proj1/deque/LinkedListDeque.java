package deque;

public class LinkedListDeque<T> implements Deque<T>{
    public class Node<T>{
        public Node prev;
        public T item;
        public Node next;

        public Node(){
            prev = null;
            next = null;
        }

        public Node(Node p, Node n, T i){
            prev = p;
            next = n;
            item = i;
        }

        public Node(T i){
            prev = null;
            next = null;
            item = i;
        }

    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node<>();
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other){
        if(!other.isEmpty()) {
            sentinel = new Node<>();
            size = 0;
            Node p = other.sentinel.next;
            while (p.next != other.sentinel.next){
                T i = (T)p.item;
                this.addLast(i);
                p = p.next;
            }
            T i = (T)p.item;
            this.addLast(i);
        }
    }

    public void addFirst(T item){
        if(isEmpty()){
            Node n = new Node(item);
            n.prev = n;
            n.next = n;
            sentinel.next = n;
        }else {
            Node first = sentinel.next;
            Node n = new Node(first.prev,first,item);
            sentinel.next = n;
            first.prev = n;
            if(first.next == first){
                first.next = n;
            }
        }
        size++;
    }

    public void addLast(T item){
        if(isEmpty()){
            Node n = new Node(item);
            n.prev = n;
            n.next = n;
            sentinel.next = n;
        }else {
            Node last = sentinel.next.prev;
            Node n = new Node(last,last.next,item);
            last.next = n;
            sentinel.next.prev = n;
        }
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p = sentinel.next;
        while(p.next != sentinel.next){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }else if(size == 1){
            T i = (T)sentinel.next.item;
            sentinel.next = null;
            size--;
            return i;
        }else {
            Node p = sentinel.next;
            T i = (T)p.item;
            Node first = p.next;
            Node last = p.prev;
            sentinel.next = first;
            first.prev = last;
            last.next = first;
            size--;
            return i;
        }
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }else if(size == 1){
            T i = (T)sentinel.next.item;
            sentinel.next = null;
            size--;
            return i;
        }else {
            Node p = sentinel.next.prev;
            T i = (T)p.item;
            Node first = sentinel.next;
            Node last = p.prev;
            first.prev = last;
            last.next = first;
            size--;
            return i;
        }
    }

    public T get(int index){
        if(index >= size || index < 0){
            return null;
        }
        Node p = sentinel.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return (T)p.item;
    }

    public T getRecursive(int index){
        if(index == 0){
            return (T)sentinel.next.item;
        }
        removeFirst();
        return getRecursive(index-1);
    }
}
