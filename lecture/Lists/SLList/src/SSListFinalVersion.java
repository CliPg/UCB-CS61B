public class SSListFinalVersion {
    public static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public SSListFinalVersion(){
        sentinel = new IntNode(114514,null);
        size = 0;
    }

    public SSListFinalVersion(int x){
        sentinel = new IntNode(114514,null);
        sentinel.next = new IntNode(x,null);
        size = 1;
    }

    public void addFirst(int x){
        sentinel.next = new IntNode(x,sentinel.next);
        size++;
    }

    public int getFirst(){
        return sentinel.next.item;
    }

    public void addLast(int x) {
        size++;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    public int size(){
        return size;
    }





}
