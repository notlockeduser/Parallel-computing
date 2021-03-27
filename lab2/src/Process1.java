class Process1 implements Runnable {

    QueueClass queue;
    CPU1 cpu1;
    CPU2 cpu2;
    int generateNumber;

    Process1(QueueClass q, int gN, CPU1 cpu1, CPU2 cpu2) {
        this.queue = q;
        this.generateNumber = gN;
        this.cpu1 = cpu1;
        this.cpu2 = cpu2;
    }

    private void destroy() throws InterruptedException {
        Main.deleted++;
        System.out.println("PRO 1 : Задача 1 удалена // CPU 1 занят, CPU 2 занят // deleted = " + Main.deleted);
    }

    public void run() {
        long generateDelay;
        for (int i = 0; i < generateNumber; i++) {
            int randMin = 10;
            int randMax = 40; // rand = [10,50]
            generateDelay = randMin + (int) (Math.random() * randMax);
            try {
                Thread.sleep(generateDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("PRO 1 : Задача 1 была сгенерирована за (" + generateDelay + ")");
                if (!cpu1.checkWork()) {
                    cpu1.setData("1");
                    System.out.println("PRO 1 : Задача 1 пошла на CPU 1");
                } else if (cpu1.checkWork() && cpu1.data.equals("1")) { // перехожим на 2 CPU с проверкой
                    if (cpu2.checkWork()) {
                        destroy();
                    } else {
                        cpu2.setData("1");
                        System.out.println("PRO 1 : Задача 1 пошла на CPU 2 // CPU 1 занят");
                    }

                } else if (cpu1.checkWork() && cpu1.data.equals("2")) {
                    System.out.println("---");
                    queue.put(cpu1.getData());
                    cpu1.interrupt();
                    Main.inter++;
                    //  Thread.currentThread().interrupt();
                    cpu1.setData("1");
                    System.out.println("PRO 1 : Задача 1  пошла на CPU 1, Задача 2 прервана c CPU 2 пошла в очередь // interrupt = " + Main.inter);
                    System.out.println("---");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PRO 1 : задачи закончились" + queue.getMaxSize());
        try {
            Thread.sleep(3300);
            System.out.println("\n\n\n-----------------------------------\nМаксимальная длина очереди = " + queue.getMaxSize());
            System.out.println("Процент уничтоженых задач для первого потока = " +(double) Main.deleted/Main.processToGenerate*100);
            System.out.println("Процент прерваных задач для второго потока = " +(double) Main.inter/Main.processToGenerate*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
