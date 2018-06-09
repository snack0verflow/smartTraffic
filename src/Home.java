import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home
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
					Home window = new Home();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection co=null;
	/**
	 * Create the application.
	 */
	public Home()
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
		frame.setBounds(100, 100, 407, 244);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblWelcomeTo = new JLabel("Welcome! to SmartTraffic  v1.0");
		lblWelcomeTo.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
		lblWelcomeTo.setBounds(19, 18, 356, 43);
		frame.getContentPane().add(lblWelcomeTo);
		
		JLabel lblChooseRole = new JLabel("Choose role:");
		lblChooseRole.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblChooseRole.setBounds(85, 95, 97, 20);
		frame.getContentPane().add(lblChooseRole);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(194, 93, 102, 26);
		frame.getContentPane().add(comboBox);
		
		comboBox.addItem("Hospital");
		comboBox.addItem("VIP");
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nn=comboBox.getSelectedIndex();
				switch(nn)
				{
					case 0:
						frame.dispose();
						Hospital h=new Hospital();
						break;
					case 1:
						frame.dispose();
						VIP n=new VIP();
				}
			}
		});
		btnProceed.setBounds(285, 160, 90, 28);
		frame.getContentPane().add(btnProceed);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnExit.setBounds(183, 160, 90, 28);
		frame.getContentPane().add(btnExit);
	}
}
