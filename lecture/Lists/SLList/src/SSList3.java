public class SSList3 {
        /* IntNode declaration omitted. */
        private IntNode sentinel;
        private int size;


        public SSList3(){
            this.sentinel = new IntNode(114514,null);
            this.size = 0;
        }

        public SSList3(IntNode sentinel, int size) {
        this.sentinel = sentinel;
        this.size = size;
    }

    public void addLast(int x) {
            size += 1;
            IntNode p = sentinel;
            while (p.next != null) {
                p = p.next;
            }

            p.next = new IntNode(x, null);
        }

}
