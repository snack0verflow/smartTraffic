import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RoadClosed
{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					RoadClosed window = new RoadClosed();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection co=null;
	public RoadClosed()
	{
		co=SqlConn.dbCon();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 388, 283);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSelectRoadTo = new JLabel("Select road to repair");
		lblSelectRoadTo.setBounds(34, 88, 116, 14);
		frame.getContentPane().add(lblSelectRoadTo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(154, 78, 147, 28);
		frame.getContentPane().add(comboBox);
		
		JButton btnRequestSchedule = new JButton("Request Schedule");
		btnRequestSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nn=comboBox.getSelectedIndex();
				String s="";
				switch(nn)
				{
					
				}
				String ss="insert into roadwork values(?)";
				try
				{
					PreparedStatement pst=co.prepareStatement(ss);
					pst.setString(1,s);
					pst.executeUpdate();
					pst.close();
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		try
		{
			co.close();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnRequestSchedule.setBounds(91, 206, 130, 28);
		frame.getContentPane().add(btnRequestSchedule);
		
		JButton btnGetSchedule = new JButton("Get Schedule");
		btnGetSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGetSchedule.setBounds(225, 206, 138, 28);
		frame.getContentPane().add(btnGetSchedule);
		
		JLabel lblSmarttrafficConstructionModule = new JLabel("SmartTraffic construction module");
		lblSmarttrafficConstructionModule.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		lblSmarttrafficConstructionModule.setBounds(24, 31, 298, 36);
		frame.getContentPane().add(lblSmarttrafficConstructionModule);
		frame.setVisible(true);
		
	}
}
