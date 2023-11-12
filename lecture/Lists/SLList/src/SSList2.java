public class SSList2 {
    public class SLList {
     /* IntNode declaration omitted. */
        private IntNode first;
        private int size;

        public SLList(int x) {
            first = new IntNode(x, null);
            size = 1;
        }

        public void addFirst(int x) {
            first = new IntNode(x, first);
            size += 1;
        }

        public int size() {
            return size;
        }

    }
}
