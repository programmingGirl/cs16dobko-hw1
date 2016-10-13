package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.IllegalFormatException;

public class TemperatureSeriesAnalysis {
    double [] temperatureSeries;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
    }

    public double average() {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        double sum = 0;
        for (double teemperature: temperatureSeries){
            sum += teemperature;
        }
        return sum/temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        double mean;
        double sum = 0;
        for (double teemperature: temperatureSeries){
            sum += teemperature;
        }
        mean = sum/temperatureSeries.length;
        for (int i=0; i< temperatureSeries.length; i++){
            temperatureSeries[i] = temperatureSeries[i] -mean;
            temperatureSeries[i] *= temperatureSeries[i];
        }
        double m;
        double s=0;
        for (double temp: temperatureSeries){
            s += temp;
        }
        m = s / temperatureSeries.length;
        return Math.sqrt(m);
    }

    public double min() {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        double min = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length; i++){
            if (temperatureSeries[i] < min) {
                min = temperatureSeries[i];
            }
        }
        return min;
    }

    public double max() {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        double max = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length; i++){
            if (temperatureSeries[i] > max) {
                max = temperatureSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        if (temperatureSeries.length == 1){
            return temperatureSeries[0];
        }
        int counterNeg = 0;
        for (double i: temperatureSeries){
            if (i<0){
                counterNeg += 1;
            }
        }
        double[] negative = new double[counterNeg];
        double[] positive = new double[temperatureSeries.length-counterNeg];
        int c = 0;
        int c_ = 0;
        for (int j = 0; j< temperatureSeries.length; j++){
            if (temperatureSeries[j]<0){
                negative[c] = temperatureSeries[j];
                c += 1;
            }
            else{
                positive[c_] = temperatureSeries[j];
                c_ += 1;
            }
        }
        //System.out.println(Arrays.toString(negative));
        //System.out.println(Arrays.toString(positive));
        double min = positive[0];
        for (int i = 0; i < positive.length; i++) {
            if (positive[i] < min) {
                min = positive[i];
            }
        }
        double max = negative[0];
        for (int i = 0; i < negative.length; i++){
            if (negative[i] > max) {
                max = negative[i];
            }
        }
        if (Math.abs(max) < min){
            return max;
        }
        else{
            return min;
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        Arrays.sort(temperatureSeries);
        double[] diffArray = new double[temperatureSeries.length];
        for (int i = 0; i < temperatureSeries.length; i++) {
            diffArray[i] = Math.abs(tempValue - temperatureSeries[i]);
        }
        double minValue = diffArray[0];
        for (double element : diffArray) {
            if (minValue > element) {
                minValue = element;
            }

        }
        double result = 0;
        for (int j = 0; j < diffArray.length; j ++) {
            if ( diffArray[j] == minValue) {
                result = temperatureSeries[j];
            }
        }
        return result;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        int c = 0;
        for (int i =0; i< temperatureSeries.length; i++){
            if (temperatureSeries[i] < tempValue){
                c += 1;
            }
        }
        double [] ans = new double[c];
        int k = 0;
        for (int i =0; i< temperatureSeries.length; i++) {
            if (temperatureSeries[i] < tempValue) {
                ans[k] = temperatureSeries[i];
                k += 1;
            }
        }

        return ans;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Array is empty");
        }
        int c = 0;
        for (int i =0; i< temperatureSeries.length; i++){
            if (temperatureSeries[i] > tempValue){
                c += 1;
            }
        }
        double [] ans = new double[c];
        int k = 0;
        for (int i =0; i< temperatureSeries.length; i++) {
            if (temperatureSeries[i] > tempValue) {
                ans[k] = temperatureSeries[i];
                k += 1;
            }
        }
        return ans;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }
        int freeSpace = 0;

        for (double temperature: temperatureSeries) {
            if (temperature == 0.0) {
                freeSpace += 1;
            }
        }
        int newArrayLength = temperatureSeries.length;
        while (freeSpace < temps.length) {
            freeSpace += newArrayLength;
            newArrayLength += newArrayLength;
        }

        double[] newArray = new double[newArrayLength];
        for (int i = 0; i < temperatureSeries.length; i++) {
            newArray[i] = temperatureSeries[i];
        }

        for (int j = 0; j < temps.length; j++) {
            newArray[j + temperatureSeries.length] = temps[j];
        }
        int result = 0;
        for (double element: newArray) {
            result += element;
        }
        return result;
    }
}
