package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPersonelAnasayfa extends JFrame {

	private JPanel contentPane;
    private JLabel lblotel;
    private JLabel lblisim;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPersonelAnasayfa frame = new FrmPersonelAnasayfa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static String otelID;
	Connection cnn=null;	
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	public FrmPersonelAnasayfa() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cnn=Baglanti.baglan();
				String sorgu="SELECT OtelAdi,a.OtelID FROM `personel` AS a INNER JOIN otel AS b ON a.`OtelID`=b.OtelID where PersonelID=?";
				 try {
					
					sorguifadesi=cnn.prepareStatement(sorgu);
					 sorguifadesi.setString(1,FrmGirisYap.persID);
					 getirilen=sorguifadesi.executeQuery();
					 if(getirilen.next()) {
						 String ad=getirilen.getString("OtelAdi");
						 lblotel.setText("OtelADI:"+ad);
						 otelID=getirilen.getString("OtelID");
						
					 }
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 164, 96));
		panel.setBounds(0, 0, 575, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAnasayfa = new JLabel("ANASAYFA");
		lblAnasayfa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblAnasayfa.setBounds(146, 11, 180, 52);
		panel.add(lblAnasayfa);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(358, 0, 3, 74);
		panel.add(panel_1);
		
		lblisim = new JLabel("");
		lblisim.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblisim.setBounds(370, 0, 188, 45);
		panel.add(lblisim);
	    lblisim.setText("Hoþgeldin"+" "+FrmGirisYap.persisim);
	
	 lblotel = new JLabel("");
	lblotel.setFont(new Font("Times New Roman", Font.BOLD, 15));
	lblotel.setBounds(370, 29, 205, 45);
	panel.add(lblotel);
	
	JButton btnRezervasyon = new JButton("Rezervasyon \u0130\u015Flemleri");
	btnRezervasyon.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FrmPersonelRezervasyon frm = new FrmPersonelRezervasyon();
			frm.setVisible(true);
			FrmPersonelAnasayfa.this.setVisible(false);
		}
	});
	btnRezervasyon.setFont(new Font("Times New Roman", Font.BOLD, 13));
	btnRezervasyon.setBounds(10, 123, 201, 64);
	contentPane.add(btnRezervasyon);
	
	JButton btnOdemeBilgi = new JButton("\u00D6deme \u0130\u015Flemleri");
	btnOdemeBilgi.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FrmPersonelOdeme frm = new FrmPersonelOdeme();
			frm.setVisible(true);
			FrmPersonelAnasayfa.this.setVisible(false);
		}
	});
	btnOdemeBilgi.setFont(new Font("Times New Roman", Font.BOLD, 13));
	btnOdemeBilgi.setBounds(315, 123, 195, 64);
	contentPane.add(btnOdemeBilgi);
	
	Image img6 = new ImageIcon(this.getClass().getResource("/money (1).png")).getImage();
 	Image img7 = new ImageIcon(this.getClass().getResource("/reception.png")).getImage();
	btnRezervasyon.setIcon(new ImageIcon(img7));
	btnOdemeBilgi.setIcon(new ImageIcon(img6));
	Image img8 = new ImageIcon(this.getClass().getResource("/home.png")).getImage();
 	lblAnasayfa.setIcon(new ImageIcon(img8));
	}
}
