package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViehmarkDatenMain {

	public static void main(String[] args) {
		String testQuery = "SELECT sum_quantity as menge FROM total_quantity";// WHERE ItemID = 82 GROUP BY ItemID";
		DbCon dbcon = new DbCon();
		try {
		dbcon.connect();
		ResultSet rs = dbcon.executeSelect(testQuery);

		ArrayList<Integer> timeLine = new ArrayList<Integer>();
		
		while(rs.next()) {
			timeLine.add(rs.getInt("menge"));
		}
		
		System.out.println("suggested Period:");
		System.out.println(JaPerCalc.getSuggestedPeriod(timeLine, 3, 13, 1, 1));
		
		
		dbcon.disconnect();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
