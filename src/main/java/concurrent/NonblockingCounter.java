package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2014/12/1.
 */
public class NonblockingCounter {
    private AtomicInteger value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (!value.compareAndSet(v, v + 1));
        return v + 1;
    }
}
