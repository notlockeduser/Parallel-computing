class threadCalculate extends Thread {

    double[] vector;
    int startIndex;
    int endIndex;
    double min;
    double max;

    public threadCalculate(double[] vector, int startIndex, int endIndex) { //конструктор класу, приймає дані для обчислень
        this.vector = vector;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    @Override
    public void run() { //обрахунки, що здійснюватимуться в зазначеному потоці
        min = vector[startIndex];
        max = vector[startIndex];
        for (int i = startIndex + 1; i < endIndex; i++) {
            if (vector[i] > max)
                max = vector[i];
            if (vector[i] < min)
                min = vector[i];
        }
    }
}