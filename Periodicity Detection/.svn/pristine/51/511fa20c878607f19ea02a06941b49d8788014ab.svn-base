package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class JaPerCalcUtil {

	public int sumItup(ArrayList<Integer> DataInput, int estimatedPer, int offset) {
		int sum = 0;
		for (int i = 0; i < DataInput.size(); i++) {
	        if (i % estimatedPer == offset) {
	          sum = sum + DataInput.get(i);
	        }
	      }
		return sum;
	}

	
	public double getPearson(ArrayList<Integer> partOfOriginal, ArrayList<Integer> componentPeriod) {
		PearsonsCorrelation pearsonCorr = new PearsonsCorrelation();
		
		//convert array list to array to be able to use correlation method
		double[] partOfOrig = new double[partOfOriginal.size()];
		for (int i = 0;i < partOfOriginal.size(); i++) {
			partOfOrig[i] = partOfOriginal.get(i);
//			System.out.println("partOfOrig = " + partOfOrig[i]);
		}

		
		double[] component = new double[componentPeriod.size()];
		for (int i = 0;i < componentPeriod.size(); i++) {
			component[i] = componentPeriod.get(i);
//			System.out.println("componentPeriod = " + component[i]);
		}
		return pearsonCorr.correlation(partOfOrig, component);
	}
	
	public double getSlope(ArrayList<Integer> input) {
		SimpleRegression simpleReg = new SimpleRegression();
		for(int i=0; i < input.size(); i++){
			double yValue = i+1;
			double xValue = Math.abs((double)input.get(i));
			simpleReg.addData( yValue,xValue);
//			System.out.println("added " + xValue + " and " + yValue);
		}
		return Math.abs(simpleReg.getSlope());
	}
	
	
	public double getRsquare(ArrayList<Integer> input) {
		SimpleRegression simpleReg = new SimpleRegression();
		for(int i=0; i < input.size(); i++){
			double yValue = i+1;
			double xValue = Math.abs((double)input.get(i));
			simpleReg.addData( yValue,xValue);
		}
		return Math.abs(simpleReg.getRSquare());
	}
	
	
	  public static Integer getKeysFromValue(HashMap<Integer,Double> map,Double value){
		    Set ref = map.keySet();
		    Iterator it = ref.iterator();
		    Integer returnInteger = 0;

		    while (it.hasNext()) {
		      Object o = it.next(); 
		      if(map.get(o).equals(value)) { 
		        returnInteger = (Integer) o;
		      } 
		    } 
		    return returnInteger;
		  }
	
	  public double getAverageFromArrayList (ArrayList<Double> entryArrayList) {
		  double sum = 0;
		  for (int i = 0; i <entryArrayList.size();i++) {
			  sum = sum + entryArrayList.get(i);
		  }
		return sum/entryArrayList.size();
	  }
	  
	public double getPeriodOfMaxValue(HashMap<Integer, Double> inputHashMap) {
				Double max_value = Collections.max(inputHashMap.values());
				Integer max_key = getKeysFromValue(inputHashMap, max_value);
				return max_key;
	}
	
	public void getConfidence(HashMap<Integer, Double> entryHashMap, double avgPearson) {
			Double first_value = Collections.max(entryHashMap.values());
			Integer first_key = getKeysFromValue(entryHashMap, first_value);
			entryHashMap.remove(first_key);
			
			Double second_value = Collections.max(entryHashMap.values());
			Integer second_key = getKeysFromValue(entryHashMap, second_value);
			entryHashMap.remove(second_key);
			
			Double third_value = Collections.max(entryHashMap.values());
			Integer third_key = getKeysFromValue(entryHashMap, third_value);
			
			double distance_first = first_value - avgPearson;
			double distance_second = second_value - avgPearson;
			double distance_third = third_value - avgPearson;
			double sum = distance_first + distance_second + distance_third;
			
			
/*			System.out.println("avgPearson = " + avgPearson);
			System.out.println("sum " + sum);
			System.out.println("first " + first_value);
			System.out.println("second " + second_value);
			System.out.println("third " + third_value);
*/			
			System.out.println("first candidate = " + first_key + " confidence = " + first_value/sum);
			System.out.println("second candidate = " + second_key + " confidence = " + second_value/sum);
			System.out.println("third candidate = " + third_key + " confidence = " + third_value/sum);
	}
}
