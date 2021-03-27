class CPU2 extends Thread {

    QueueClass queue;
    String data = "";
    private boolean isWork = false;

    public boolean checkWork() {
        if (isWork) return true;
        return false;
    }

    public String getData(){
        return data;
    }

    public void setData(String newData){
        data = newData;
    }

    CPU2(QueueClass q) {
        this.queue = q;
    }

    @Override
    public void run() {
        long processingTime;
        while (true) {
            int randMin = 20;
            int randMax = 100; // rand = [20,100]
            processingTime = randMin + (int) (Math.random() * randMax);
            try {
                if (data != "") {
                    isWork = true;
                    System.out.println("CPU 2 : Начал работу напрямую - задача " + data + "; Время работы " + processingTime);
                    Thread.sleep(processingTime);
                    setData("");
                    isWork = false;
                }
//                 else if (!queue.queueIsEmpty()) {
//                    isWork = true;
//                    System.out.println("CPU 2 : Начал работу с очереди  " + processingTime);
//                    data = queue.get();
//                    Thread.sleep(processingTime);
//                    data = "";
//                    isWork = false;
//                    System.out.println("CPU 2 : Processed end   ");
                //               }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
