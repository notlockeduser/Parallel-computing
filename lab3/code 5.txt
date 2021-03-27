import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    static int SIZE = 50000;

    public static long[] getRandomArray(long[] array) {
        long randMin = 0L, randMax = 50L;
        for (int i = 0; i < SIZE; i++) {
            array[i] = ((long) (randMin + (Math.random() * randMax)));
        }
        // System.out.println(array.toString());
        return array;
    }

    public static long[] getNumberArray(long[] array) {
        for (int i = 0; i < SIZE; i++) {
            array[i] = i;
        }
        // System.out.println(array.toString());
        return array;
    }


    public static void main(String[] args) {
        long array1[] = new long[SIZE];
        long array2[] = new long[SIZE];
        long arrayAdd[] = new long[SIZE];

        getRandomArray(array1);
        getRandomArray(array2);
        getNumberArray(arrayAdd);

        AtomicLong result1 = new AtomicLong();
        AtomicLong result2 = new AtomicLong();

        LongStream.of(arrayAdd).parallel().forEachOrdered(i -> {
            long oldValue;
            long newValue;
            long Value;
            while (true){
                Value = array1[(int) i] * array2[(int) i];
                if (Value % 2 == 0){
                    oldValue = result1.get();
                    newValue = oldValue + Value;
                    if (result1.compareAndSet(oldValue, newValue)) break;
                } else {
                    oldValue = result2.get();
                    newValue = oldValue + Value;
                    if (result2.compareAndSet(oldValue, newValue)) break;
                }
            }
        });

        System.out.println("Result 1 = " + result1.get());
        System.out.println("Result 2 = " + result2.get());
    }
}