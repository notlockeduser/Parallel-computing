public class threadSample {
    public static int SIZE = 1000000;
    public static int NUMBER_THREADS = 2;

    public static void main(String[] args) throws InterruptedException {

        double[] vector = new double[SIZE];
        int rand_min = 1;
        int rand_max = 42;
        for (int i = 0; i < SIZE; i++) { //початкове заповнення векторів випадковими величинами з зазначеного проміжку
            vector[i] = rand_min + (int) (Math.random() * rand_max);
        }

        System.out.println("Size vector: " + SIZE);

        double startTime, finalTime, totalTime = 0;

        // Serial
        double maxSerial = 0, minSerial = 0;

        for (int x = 0; x < 100; x++) {

            startTime = System.nanoTime();
            maxSerial = vector[0];
            minSerial = vector[0];
            for (int i = 1; i < SIZE; i++) {
                if (vector[i] > maxSerial)
                    maxSerial = vector[i];
                if (vector[i] < minSerial)
                    minSerial = vector[i];
            }
            finalTime = (System.nanoTime() - startTime) / 1000000;


            totalTime += finalTime;

        }
        System.out.println("Serial   | min: " + minSerial + " | max: " + maxSerial + " | Time: " + totalTime / 100 + "ms");

        // Parallels
        for (NUMBER_THREADS = 2; NUMBER_THREADS<=10;NUMBER_THREADS++) {
            totalTime = 0;

            double maxParallel = 0, minParallel = 0;

            for (int x = 0; x < 100; x++) {

                startTime = System.nanoTime();

                threadCalculate[] treadArray = new threadCalculate[NUMBER_THREADS];

                for (int i = 0; i < NUMBER_THREADS; i++) { //розбиття на потоки
                    treadArray[i] = new threadCalculate(vector, SIZE / NUMBER_THREADS * i,
                            i == (NUMBER_THREADS - 1) ? SIZE : SIZE / NUMBER_THREADS * (i + 1)); //тернарна умовна операція
                    treadArray[i].start();
                }
                for (int i = 0; i < NUMBER_THREADS; i++) { //очікування завершення усіх потоків
                    treadArray[i].join();
                }

                maxParallel = treadArray[0].getMax();
                minParallel = treadArray[0].getMin();
                for (int i = 0; i < NUMBER_THREADS; i++) { //збір результатів паралельної роботи
                    if (treadArray[i].getMax() > maxParallel)
                        maxParallel = treadArray[i].getMax();
                    if (treadArray[i].getMin() < minParallel)
                        minParallel = treadArray[i].getMin();
                }

                finalTime = (System.nanoTime() - startTime) / 1000000;
                if (x != 0)
                    totalTime += finalTime;
            }

            System.out.println("Number threads: " + NUMBER_THREADS + " | min: " + minParallel + " | max: " + maxParallel + " | Time: " + totalTime / 100 + "ms");
        }
    }
}
