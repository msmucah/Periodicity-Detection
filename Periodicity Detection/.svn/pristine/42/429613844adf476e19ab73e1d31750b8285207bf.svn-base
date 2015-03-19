package main;

import java.util.ArrayList;
import java.util.HashMap;

public class JaPerCalc {
	
	public static int getSuggestedPeriod(ArrayList<Integer> entryTimeLine, int estimPerLow, int estimPerUp, int print , int showConfidence) {

		int returnValue = 0;
		
		//this value is used to compute the confidence at the end
		double sumPearsonCorr = 0;
		
//			for (int i = 0; i <= entryTimeLine.size();i++) {
				ArrayList<Integer> observations = new ArrayList<Integer>();
				ArrayList<Integer> observations2 = new ArrayList<Integer>();
				for (int ii = 0; ii < entryTimeLine.size();ii++) {
					observations.add(entryTimeLine.get(ii));
					observations2.add(entryTimeLine.get(ii));
				}
				
//				System.out.println("getting rdy to sumItUp:");
				JaPerCalcUtil jaPerCalc = new JaPerCalcUtil();
				
				//while loop for get through different assumed periods
				HashMap<Integer, Double> resultsHashMap = new HashMap<Integer, Double>();
				
				//this second hashMap is used for confidence calculation
				HashMap<Integer, Double> avgHashMap = new HashMap<Integer, Double>();
				ArrayList<Integer> sumResults = new ArrayList<Integer>();
				
				for(int estimPer = estimPerLow; estimPer <= estimPerUp; estimPer++){
					
					for(int offSet = 0; offSet <= estimPer-1; offSet++){
						int currentSum = jaPerCalc.sumItup(observations, estimPer, offSet);
//						System.out.println("estimPer = "  + estimPer + ", offSet = " + offSet +", currentSum =" + currentSum);
						sumResults.add(currentSum);
					}
//					System.out.println("sumResults = " + sumResults.toString());
					
					//now we got the sums for one estimated period (this is called component with length sumResults.length()), now compare this componends with parts of the origninal curve
					ArrayList<Double> pearson = new ArrayList<Double>();
					int numberOfSwaps = (int)observations2.size()/sumResults.size();
					int offset = 0;	
					for(int swap = 0; swap < numberOfSwaps; swap++) {
					
						//now cut parts from the current observation and correlate it with the component 
						ArrayList<Integer> currentOrigComponent = new ArrayList<Integer>();
						for (int iii=0; iii<sumResults.size(); iii++) {
							currentOrigComponent.add(observations2.get(iii+offset));
						}
						offset = offset + sumResults.size();
						pearson.add(jaPerCalc.getPearson(currentOrigComponent, sumResults));
						
//						System.out.println("currentOrigComponent = " + currentOrigComponent.toString());					
//						System.out.println("current sumResult = " + sumResults.toString());
//						System.out.println("found correlation = " + jaPerCalc.getPearson(currentOrigComponent, sumResults));
//						System.out.println("offset =" + offset);
//						System.out.println("size observation = " + observations2.size());
					}

					//now all parts of the observation curve have been correlated with the component
					//now we have to get the average of the pearson correlations
					double avgPearson = jaPerCalc.getAverageFromArrayList(pearson);
					
					resultsHashMap.put(estimPer, avgPearson);
					avgHashMap.put(estimPer, avgPearson);
					sumPearsonCorr = sumPearsonCorr + avgPearson;
//					System.out.println("putted in hashMap = " + jaPerCalc.getAverageFromArrayList(pearson));
					pearson.clear();
					sumResults.clear();
					
				}// finished with all estimPer loops
//				System.out.println("finished all estimPer loops. HashMap: " + resultsHashMap.toString());
				System.out.println("timeline: " + entryTimeLine.toString());
				System.out.println("suggested period = " + jaPerCalc.getPeriodOfMaxValue(resultsHashMap));
				returnValue = (int) jaPerCalc.getPeriodOfMaxValue(resultsHashMap);
				
				
				//confidence calculation
				if (showConfidence == 1) {
					System.out.println("confidence: ");
					jaPerCalc.getConfidence(avgHashMap, sumPearsonCorr/(estimPerUp-estimPerLow));
					}
				
				if (print == 1) {
					//now print hashmap 
					System.out.println("-------------------------------------------------------------------------------------");
					System.out.println("following correlations have been found:");
					for (int i = estimPerLow; i <= estimPerUp; i++) {
						System.out.println("for periodicity = " + i + " average Pearson correlation = " + resultsHashMap.get(i));
					}
					System.out.println("-------------------------------------------------------------------------------------");
				}
				resultsHashMap.clear();
				observations.clear();
				observations2.clear();

			return returnValue;
	}
}
