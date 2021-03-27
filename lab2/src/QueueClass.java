import java.util.LinkedList;
import java.util.Queue;

class QueueClass {

    private Queue<String> queue = new LinkedList<>();
    private int capacity;
    private int maxSize = 0;

    public QueueClass(int capacity) {
        this.capacity = capacity;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public boolean queueIsEmpty() {
        return queue.isEmpty();
    }

    public synchronized void put(String element) throws InterruptedException {
//        while (queue.size() == capacity) {
//            System.out.println("QUEUE : очередь полная. Ожидание");
//            wait();
//        }
        queue.add(element);

        if (queue.size() > maxSize)
            maxSize = queue.size();

        System.out.println("QUEUE : Пришла задача " + element + "; Размер очереди: " + queue.size());
       // notify(); // notifyAll() for multiple CPU/CPUProcess threads
    }

    public synchronized String get() throws InterruptedException {
//        while (queue.isEmpty()) {
//            System.out.println("QUEUE : очередь пустая. Ожидание");
//            wait();
//        }
        String item = queue.remove();
        System.out.println("QUEUE : Задача 2 удалена. Размер очереди = [" + queue.size() + "]");
       // notify(); // notifyAll() for multiple CPU/CPUProcess threads
        return item;
    }


}
