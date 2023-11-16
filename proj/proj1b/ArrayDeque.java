/**
 * @author CliPg
 */
public class ArrayDeque<T> implements Deque<T>{
    private T [] arrayDeque;
    private int size;
    private int first;
    private int last;
    private int length;
    private int countFirst;
    private int countLast;

    public ArrayDeque(){
        arrayDeque = (T[])new Object[8];
        size = 0;
        first = 3;
        last = 4;
        length = 8;
        countFirst = 0;
        countLast = 0;
    }

    public void enlargeArray() {
        T[] newArray = (T[]) new Object[length * 2];
        int newFirst = first+1;
        int newLast = last-1;
        int i = length / 2;
            for (int count1 = 0; count1 < countFirst; count1++) {
                if (newFirst > length - 1) {
                    newFirst = 0;
                }
                newArray[i] = arrayDeque[newFirst];
                newFirst++;
                i++;
            }
        int j = length/2+length-1;
            for (int count2 = 0; count2 < countLast; count2++) {
                if (newLast < 0) {
                    newLast = length-1;
                }
                newArray[j] = arrayDeque[newLast];
                newLast--;
                j--;
            }
        arrayDeque = newArray;
        first = length/2-1;
        last = length/2+length;
        length *= 2;
        }

    @Override
    public void addFirst(T item){
        if (first > 0 && size != length){
            arrayDeque[first] = item;
            first--;
            size++;
            countFirst++;
        }else if (first == 0 && size != length){
            arrayDeque[first] = item;
            first = length - 1;
            size++;
            countFirst++;
        }else if (size == length){
            enlargeArray();
            arrayDeque[first] = item;
            first--;
            size++;
            countFirst++;
        }
    }

    @Override
    public void addLast(T item){
        if (last < length-1 && size != length){
            arrayDeque[last] = item;
            last++;
            size++;
            countLast++;
        }else if (last == length-1 && size != length){
            arrayDeque[last] = item;
            last = 0;
            size++;
            countLast++;
        }else if (size == length){
            enlargeArray();
            arrayDeque[last] = item;
            last++;
            size++;
            countLast++;
        }
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        int i = 0;
        int p =first;
        while (i < size){
            if (p == length - 1) {
                p = -1;
            }
            System.out.print(arrayDeque[p+1] + " ");
            p++;
            i++;
        }
        System.out.println("\n");
    }

    @Override
    public T removeFirst(){
        T item;
        if (first + 1 == length) {
            first = -1;
        }
        item = arrayDeque[first+1];
        arrayDeque[first+1] = null;
        first++;
        size--;
        return item;
    }

    @Override
    public T removeLast(){
        T item;
        if (last == 0){
            last = length;
        }
        item = arrayDeque[last-1];
        arrayDeque[last-1] = null;
        last--;
        size--;
        return item;
    }

    @Override
    public T get(int index){
        int p = first;
        if (index > size){
            return null;
        }
        for (int i = 0; i <= index; i++) {
            if (p != length-1){
                p++;
            }else {
                p = 0;
            }
        }
        return arrayDeque[p];
    }
}
