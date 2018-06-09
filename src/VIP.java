import java.awt.Desktop;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.sql.*;


public class VIP
{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	Connection co=null;
	public VIP()
	{
		initialize();
		co=SqlConn.dbCon();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	public static boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 407, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSource = new JLabel("Source ID");
		lblSource.setBounds(77, 134, 55, 16);
		frame.getContentPane().add(lblSource);
		
		JLabel lblDestination = new JLabel("Destination ID");
		lblDestination.setBounds(77, 161, 90, 16);
		frame.getContentPane().add(lblDestination);
		
		JLabel lblDeparture = new JLabel("Departure time");
		lblDeparture.setBounds(77, 189, 90, 16);
		frame.getContentPane().add(lblDeparture);
		
		textField = new JTextField();
		textField.setBounds(207, 123, 122, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(207, 150, 122, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(207, 178, 60, 28);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(207, 210, 122, 34);
		 
		frame.getContentPane().add(datePicker);
		
		JButton btnRequestRoute = new JButton("Request route");
		btnRequestRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String t1=textField.getText();
				String t2=textField_1.getText();
				String t3=textField_2.getText();
				String t4=textField_3.getText();
				java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
				java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
				
				String s="insert into requests values(?,?,?,?,?,?)";
				try {
				PreparedStatement pst=co.prepareStatement(s);
				pst.setString(1,t1);
				pst.setString(2,t2);
				if(Integer.parseInt(t3)>24 || Integer.parseInt(t3)<0 || Integer.parseInt(t4)>59 || Integer.parseInt(t4)<0)
					throw(new Exception());
				pst.setString(3,t3);
				pst.setString(4,t4);
				pst.setDate(5,sqlDate);
				pst.setString(6,"VIP");
				
				pst.executeUpdate();
				pst.close();
				}catch(Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invalid Time");
				}
			}
		});
		btnRequestRoute.setBounds(149, 256, 106, 28);
		frame.getContentPane().add(btnRequestRoute);
		
		JButton btnGetPath = new JButton("Get Path");
		btnGetPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				PathMap p=new PathMap();
			}
		});
		btnGetPath.setBounds(257, 256, 90, 28);
		frame.getContentPane().add(btnGetPath);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					co.close();
					
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				Home hh=new Home();
			}
		});
		btnBack.setBounds(57, 256, 90, 28);
		frame.getContentPane().add(btnBack);
		
		JLabel lblEnterYourSource = new JLabel("Enter your source, destination and departure time.");
		lblEnterYourSource.setBounds(32, 25, 273, 16);
		frame.getContentPane().add(lblEnterYourSource);
		
		JLabel lblRequestForA = new JLabel("Request for a route then wait a little and click Get Path.");
		lblRequestForA.setBounds(32, 41, 301, 16);
		frame.getContentPane().add(lblRequestForA);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(269, 178, 60, 28);
		frame.getContentPane().add(textField_3);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(77, 217, 55, 16);
		frame.getContentPane().add(lblDate);
		
		JButton btnGetId = new JButton("Get ID");
		btnGetId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					openWebpage(new URL("http://akhilchilakalast.shinyapps.io/player"));
				} catch (MalformedURLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGetId.setBounds(65, 97, 90, 28);
		frame.getContentPane().add(btnGetId);
		frame.setVisible(true);
	}
}
