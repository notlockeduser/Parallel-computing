public class Main {

    static int deleted = 0;
    static int inter = 0;
    static int processToGenerate = 20;

    public static void main(String[] args) throws InterruptedException {
        int queueCapacity = 10;
        //int processToGenerate = 20;
        System.out.println("\nОбъем очереди " + queueCapacity + "\nЗадач будет сгенерировано " + processToGenerate *2 + "\n");
        QueueClass queue = new QueueClass(queueCapacity);

        CPU1 cpu1 = new CPU1(queue);
        CPU2 cpu2 = new CPU2(queue);

        Process1 process1 = new Process1(queue, processToGenerate, cpu1, cpu2);
        Process2 process2 = new Process2(queue, processToGenerate, cpu1, cpu2);

        new Thread(process1).start();
        new Thread(process2).start();

        cpu1.start();
        cpu2.start();
    }
}


