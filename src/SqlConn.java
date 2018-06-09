import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
public class SqlConn
{
	Connection con=null;
	public static Connection dbCon()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://192.168.2.2:3306/straffic?useSSL=false&"+"user=system&password=manager");
			//JOptionPane.showMessageDialog(null, "connected");
			return con;
		}
		catch(Exception e) {e.printStackTrace();
		return null;}
	}
}