class Process2 implements Runnable {

    QueueClass queue;
    CPU1 cpu1; CPU2 cpu2;
    int generateNumber;


    Process2(QueueClass q, int gN, CPU1 cpu1, CPU2 cpu2) {
        this.queue = q;
        this.generateNumber = gN;
        this.cpu1 = cpu1;
        this.cpu2 = cpu2;
    }

    public void run() {
        long generateDelay;
        for (int i = 0; i < generateNumber; i++) {
            int randMin = 10;
            int randMax = 50; // rand = [10,50]
            generateDelay = randMin + (int) (Math.random() * randMax);
            try {
                Thread.sleep(generateDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("PRO 2 : Задача 2 была сгенерирована за (" + generateDelay + ")");
                if (!cpu2.checkWork()) {
                    cpu2.setData("2");
                    System.out.println("PRO 2 : Задача 2 пошла на CPU 2");
                } else {
                    System.out.println("PRO 2 : Задача 2 пошла на очередь");
                    queue.put("2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PRO 2 : задачи закончились" + queue.getMaxSize());
    }
}
