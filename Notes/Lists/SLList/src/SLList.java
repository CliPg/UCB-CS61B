public class SLList {
    public IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    /** Adds an item to the end of the list.
     * In this method,however,when "first" points at "null",this program may crash
     * */
    public void addLast(int x) {
        IntNode p = first;

        /* Advance p to the end of the list. */
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }
    /**
     * The advanced version is as follows
     */



    /** Returns the size of the list starting at IntNode p.
     * However,when our statistics are huge,this method maybe a waste of time.
     * Therefore,we can establish a new variable "size" to store the number of our statistics
     * so that we can access it more quickly.
     * reference SLList2*/
    public int size(IntNode p) {
        if (p.next == null) {
            return 1;
        }

        return 1 + size(p.next);
    }
}
