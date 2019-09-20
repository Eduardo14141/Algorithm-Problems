package main.gui;

import java.util.ArrayList;

public class Response {
    private final ArrayList<Double> array;
    private final double time;
    private final String algorithm_name;

    public Response(ArrayList<Double> array, double time, String algorithm_name) {
        this.array = array;
        this.time = time;
        this.algorithm_name = algorithm_name;
    }

    public ArrayList<Double> getArray() {
        return array;
    }

    public double getTime() {
        return time;
    }

    public String getAlgorithm_name() {
        return algorithm_name;
    }
    
}