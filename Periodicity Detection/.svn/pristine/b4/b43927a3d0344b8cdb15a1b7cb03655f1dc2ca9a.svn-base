package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class JaPerCalcMain {

	/**
	 * @param args
	 */
	public static int numberOfPeriods = 12;
	
	public static void main(String[] args) {
		
		String testQuery = "SELECT distinct(itemID) as itemID FROM train WHERE ItemID = 82 GROUP BY ItemID";// WHERE ItemID = 82 GROUP BY ItemID";
		DbCon dbcon = new DbCon();
		try {
		dbcon.connect();
		ResultSet rs = dbcon.executeSelect(testQuery);
		
			while(rs.next()) {
				int currentItemID = rs.getInt("itemID");
				System.out.println("processing ItemID:" + rs.getInt("itemID"));
				String getDataQuery = "SELECT day, quantity FROM train WHERE itemID = " + currentItemID + " ORDER BY day ASC";
				
				ResultSet getData = dbcon.executeSelect(getDataQuery);
				ArrayList<Integer> observations = new ArrayList<Integer>();
				ArrayList<Integer> observations2 = new ArrayList<Integer>();
				while(getData.next()) {
					observations.add(getData.getInt("quantity"));
					observations2.add(getData.getInt("quantity"));
				}
				
//				System.out.println("getting rdy to sumItUp:");
				JaPerCalcUtil jaPerCalc = new JaPerCalcUtil();
				
				//while loop for get through different assumed periods. 3 - 14
				HashMap<Integer, Double> resultsHashMap = new HashMap<Integer, Double>();
				ArrayList<Integer> sumResults = new ArrayList<Integer>();
				
				for(int estimPer = 3; estimPer <= numberOfPeriods; estimPer++){
					
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
						for (int i=0; i<sumResults.size(); i++) {
							currentOrigComponent.add(observations2.get(i+offset));
						}
						offset = offset + sumResults.size();
						pearson.add(jaPerCalc.getPearson(currentOrigComponent, sumResults));
						
//						System.out.println("currentOrigComponent = " + currentOrigComponent.toString());					
//						System.out.println("current sumResult = " + sumResults.toString());
						System.out.println("found correlation = " + jaPerCalc.getPearson(currentOrigComponent, sumResults));
//						System.out.println("offset =" + offset);
//						System.out.println("size observation = " + observations2.size());
					}

					//now all parts of the observation curve have been correlated with the component
					//now we have to get the average of the pearson correlations
					resultsHashMap.put(estimPer, jaPerCalc.getAverageFromArrayList(pearson));
					System.out.println("putted in hashMap = " + jaPerCalc.getAverageFromArrayList(pearson));
					
					pearson.clear();
					sumResults.clear();
					
				}// finished with all estimPer loops
//				System.out.println("finished all estimPer loops. HashMap: " + resultsHashMap.toString());
				System.out.println("for itemID: " + currentItemID + ", suggested period = " + jaPerCalc.getPeriodOfMaxValue(resultsHashMap));
				resultsHashMap.clear();
				observations.clear();
				observations2.clear();
			}
			
			dbcon.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
