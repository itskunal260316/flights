package travel_globe;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.lti.Flight.model.Flight;

public class ReservationDao {
	
	public  List<Flight>  Search(String Depart_From, String Destination) {
		String df = Depart_From;
		String des = Destination;
		Connection con = null;
		PreparedStatement ps = null; // pre compiled sql statement
		Statement stmt = null;
		ResultSet rs = null;
		

		//System.out.println(empno + empname + salary);
		try {
			//why not FileInputStream is=new FileInputStream("dev-db.properties") ??
			InputStream is=this.getClass().getClassLoader().getResourceAsStream("dev-db.properties");
			//the prop files will be loaded only once in the memory
			
			Properties dbProps=new Properties();         ///Extends hash table class It takes the form of key value pair
			
			dbProps.load(is);
					//loading properties from the properties class 
			
			String driverClassName= dbProps.getProperty("driverClassName");
			Class.forName(driverClassName);
			String url=dbProps.getProperty("url");
			String username=dbProps.getProperty("username");
			String password=dbProps.getProperty("password");
			con = DriverManager.getConnection(url,username,password);
			String sql = " select * from " 
			+ "Flights where Depart_From=? and Destination=?";
			
			

			
			System.out.println("Connected....");
			
			ps=con.prepareStatement(sql);
			ps.setString(1,df);
			ps.setString(2,des);
			
			rs = ps.executeQuery();
			
			List<Flight> fl=new ArrayList<Flight>();
			while(rs.next()) {
				Flight fly=new Flight();
				 fly.setFlightNo(rs.getInt("FlightNo"));
				fly.setDepart_From(rs.getString("Depart_From"));
				fly.setDestination(rs.getString("Destination"));
				fly.setAirline(rs.getString("Airline")); 
				
				fl.add(fly);

			}		return fl;

}catch(ClassNotFoundException e1)
		{
	e1.printStackTrace();
} 
catch (SQLException e) {
	e.printStackTrace();
	// TODO: handle exception
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 

finally
{
	try {
		con.close();
		rs.close();
		ps.close();
	} catch (Exception ignored) {
		// TO0DO: handle exception
		
	}
}
		return null;
		

}
}