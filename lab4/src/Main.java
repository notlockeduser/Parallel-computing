import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    private static int SIZE = 10;

    private static ArrayList<Integer> getRandom(int SIZE, int index) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            int randMin = 0;
            int randMax = 50;
            array.add(randMin + (int) (Math.random() * randMax));
        }
        System.out.println("input        -- array " + index + " = " + array);
        return array;
    }

    public static void main(String[] args) {
        // заполняем ArrayList`ы рандомными значениями от 0 до 50
        final ArrayList<Integer> a1 = getRandom(SIZE, 1);
        final ArrayList<Integer> a2 = getRandom(SIZE, 2);
        // создаём итераторы для каждого массива для возможности изменения внутри лямбда функции
        Iterator<Integer> iterator1 = a1.iterator();
        Iterator<Integer> iterator2 = a2.iterator();

        CompletableFuture<ArrayList<Integer>> firstFuture, secondFuture, resultFuture;

        // асинхронно выполняем код лямбда-функции для данного фьючера
        // вычисляем сумму и находим среднее значение
        // находим элементы которые меньше или равны среднему и удаляем (с помощью итератора),
        // получая массив с данными которые больше среднего
        // далее сортируем
        firstFuture = CompletableFuture.supplyAsync(() -> a1).thenApplyAsync(first -> {
            int sum = 0, average = 0;
            for (Integer element : a1) {
                sum += element;
            }
            average = sum / a1.size();
            while (iterator1.hasNext()) {
                Integer item = iterator1.next();
                if (item <= average) {
                    iterator1.remove();
                }
            }
            Collections.sort(first);
            System.out.println("intermediate -- array 1  = " + first + " average = " + average);
            return first;
        });

        // асинхронно выполняем код лямбда-функции для данного фьючера
        // вычисляем сумму и находим среднее значение
        // находим элементы которые больше или равны среднему и удаляем (с помощью итератора),
        // получая массив с данными которые меньше среднего
        // далее сортируем
        secondFuture = CompletableFuture.supplyAsync(() -> a2).thenApplyAsync(second -> {
            int sum = 0, average = 0;
            for (Integer element : a2) {
                sum += element;
            }
            average = sum / a2.size();
            while (iterator2.hasNext()) {
                Integer item = iterator2.next();
                if (item >= average) {
                    iterator2.remove();
                }
            }
            Collections.sort(second);
            System.out.println("intermediate -- array 2  = " + second + " average = " + average);
            return second;
        });

        // находим разность массивов (множеств),
        // проверяя есть ли данный элемент в другом массиве
        // если нет, то добавляем
        resultFuture = firstFuture.thenCombine(secondFuture, (first, second) -> {
            ArrayList<Integer> a3 = new ArrayList<>(first);
            for (Integer i : a1) {
                if (!a2.contains(i))
                    a3.add(i);
            }
            return a3;
        });
        // выводим получившийся массив, при этом ославливая возможные ошибки
        try {
            System.out.println("Result: " + resultFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
