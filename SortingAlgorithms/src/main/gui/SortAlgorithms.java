package main.gui;

import java.util.ArrayList;
import java.util.Arrays;

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
    protected Response quickSort(ArrayList<Double> array){
        long start_time = System.nanoTime();
        array = quickSorting(array);
        return new Response(array, System.nanoTime() - start_time, "Ordenamiento por ordenamiento rápido");
    }
    private ArrayList<Double> quickSorting (ArrayList<Double> array){
        if(array.size() > 0){
            ArrayList<Double> left = new ArrayList<>();
            ArrayList<Double> right = new ArrayList<>();
            double pivot = array.get(0);
            if(array.size() > 1){
                for(int i = 1; i < array.size(); i++){
                    if(array.get(i) < pivot)
                        left.add(array.get(i));
                    else
                        right.add(array.get(i));
                }
                left = quickSorting(left);
                right = quickSorting(right);
            }
            left.add(pivot);
            left.addAll(right);
            array = left;
        }
        return array;
    }
    protected Response mergeSort(ArrayList<Double> array){
        long start_time = System.nanoTime();
        array = mergeSorting(array);
        return new Response(array, System.nanoTime() - start_time, "Ordenamiento por ordenamiento rápido");
    }
    private ArrayList<Double> mergeSorting (ArrayList<Double> array){
        int middle = Math.floorDiv(array.size(), 2);
        
        ArrayList<Double> left = new ArrayList<>();
        ArrayList<Double> right = new ArrayList<>();        
        left.addAll(array.subList(0, middle));
        right.addAll(array.subList(middle, array.size()));
        
        if(left.size() > 1)
            left = mergeSorting(left);
        if(right.size() > 1)
            right = mergeSorting(right);
        return mergeHalves(left, right);
    }
    private ArrayList<Double> mergeHalves (ArrayList<Double> left_array, ArrayList<Double> right_array){
        ArrayList<Double> temp = new ArrayList<>();
        int index_a =0, index_b = 0;
        
        while(index_a < left_array.size() && index_b < right_array.size()){
            if(left_array.get(index_a) > right_array.get(index_b)){
                temp.add(right_array.get(index_b));
                index_b ++;
            }else{
                temp.add(left_array.get(index_a));
                index_a ++;
            }
        }
        temp.addAll(left_array.subList(index_a, left_array.size()));
        temp.addAll(right_array.subList(index_b, right_array.size()));
        return temp;
    }
}