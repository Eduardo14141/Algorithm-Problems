package main.gui;

import java.util.ArrayList;

public class SortAlgorithms {
    protected Response selectionSort(ArrayList<Double> array){
        double current_number;
        int position;
        long start_time = System.nanoTime();
        for (int i = 0; i < array.size(); i++) {
            current_number = array.get(i);
            position = i;
            for (int j = i; j < array.size(); j++) {
                if(current_number > array.get(j)){
                    current_number = array.get(j);
                    position = j;
                }
            }
            array.set(position, array.get(i));
            array.set(i, current_number);
        }
        return new Response(array, System.nanoTime() - start_time, "Ordenamiento por selección");
    }
    protected Response bubbleSort(ArrayList<Double> array){
        long start_time = System.nanoTime();
        boolean has_changes;
        double current, next;
        do{
            has_changes = false;
            for (int i = 0; i < array.size() - 1; i++) {
                current = array.get(i);
                next = array.get(i + 1); 
                if(current > next){
                    array.set(i, next);
                    array.set(i + 1, current);
                    has_changes = true;
                }
            }
        }while (has_changes);
        return new Response(array, System.nanoTime() - start_time, "Ordenamiento por el método burbuja");
    }
    protected Response ShellSort(ArrayList<Double> array){
        long start_time = System.nanoTime();
        int step = (int) Math.floor(array.size() / 2);
        double current, next;
        boolean has_changes;
        do{
            do{
                has_changes = false;
                for (int i = 0; i < array.size() - step; i++) {
                    current = array.get(i);
                    next = array.get(i + step); 
                    if(current > next){
                        array.set(i, next);
                        array.set(i + step, current);   
                        has_changes = true;
                    }
                }
            }while(has_changes);
            step = (int) Math.floor(step / 2);
        }while(step >= 1);
        return new Response(array, System.nanoTime() - start_time, "Ordenamiento por el método Shell");
    }
}