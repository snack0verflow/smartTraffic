import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PathMap
{

	private JFrame frame;

	
	/**
	 * Create the application.
	 */
	Connection co=null;
	public PathMap()
	{
		co=SqlConn.dbCon();
		initialize();
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
		frame.setBounds(100, 100, 328, 278);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);
		
		JButton btnRefresh = new JButton("Open Map");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					openWebpage(new URL("http://akhilchilakalast.shinyapps.io/thomas"));
				} catch (MalformedURLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setBounds(211, 204, 89, 27);
		frame.getContentPane().add(btnRefresh);
		
		JLabel lblOpenMap = new JLabel("1. Open Map");
		lblOpenMap.setBounds(36, 100, 77, 16);
		frame.getContentPane().add(lblOpenMap);
		
		JLabel lblUploadPathcsv = new JLabel("2. Upload path.csv from the current directory.");
		lblUploadPathcsv.setBounds(36, 122, 246, 16);
		frame.getContentPane().add(lblUploadPathcsv);
		
		JLabel lblAlmostThere = new JLabel("Almost there..");
		lblAlmostThere.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		lblAlmostThere.setBounds(23, 37, 151, 27);
		frame.getContentPane().add(lblAlmostThere);
		
		JButton btnGenerateCsv = new JButton("Generate CSV");
		btnGenerateCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String command = "SELECT * FROM path";
				try {
					PreparedStatement pst = co.prepareStatement(command);
					ResultSet rs = pst.executeQuery();
					rs.next();
					String path = rs.getString(1);
					String[] arr = path.split(" ");
					System.out.println(arr);
					/*String path = "1 2 3 4 5 6";
					String[] arr = path.split(" ");*/
					FileWriter fileWriter = null;
					try {
						fileWriter = new FileWriter("test.csv");
						//Write the CSV file header
						fileWriter.append("X,Y");
						//Add a new line separator after the header
						fileWriter.append("\n");
						for(int i = 0; i < arr.length; i += 2) {
							fileWriter.append(arr[i] + "," + arr[i+1] + "\n");
						}
					}
					catch(Exception e1) {
						System.out.println("file error");
					}
					finally {
						try {
							fileWriter.flush();
							fileWriter.close();
						} catch (IOException e2) {
							System.out.println("Error while flushing/closing fileWriter !!!");
							e2.printStackTrace();
						}
					}


				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGenerateCsv.setBounds(105, 204, 106, 28);
		frame.getContentPane().add(btnGenerateCsv);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Hospital v=new Hospital();
			}
		});
		btnNewButton.setBounds(14, 204, 90, 28);
		frame.getContentPane().add(btnNewButton);
		
		String s="select * from path";		
		
//		try
//		{
////			PreparedStatement pst=co.prepareStatement(s);
////			ResultSet rs=pst.executeQuery();
////			String ss=rs.getString(1);
////			System.out.println(ss);
//		} catch (SQLException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
	}
}
