package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public int fillCount() {
        return 0;
    }

    @Override
    public void enqueue(T x) {

    }

    @Override
    public T dequeue() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
