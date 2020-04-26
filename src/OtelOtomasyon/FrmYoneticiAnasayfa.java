package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmYoneticiAnasayfa extends JFrame {

	private JPanel contentPane;
	private JLabel lblotel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmYoneticiAnasayfa frame = new FrmYoneticiAnasayfa();
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
	Connection cnn=null;	
   
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	public static String otelID;
   
	public FrmYoneticiAnasayfa() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cnn=Baglanti.baglan();
				String sorgu="Select OtelID,OtelAdi from Otel where YöneticiID=?";
				 try {
					
					sorguifadesi=cnn.prepareStatement(sorgu);
					 sorguifadesi.setString(1,FrmGirisYap.yonID);
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
		setBounds(100, 100, 639, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(233, 150, 122));
		panel.setBounds(0, 0, 623, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblanasayfa = new JLabel("ANASAYFA");
		lblanasayfa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblanasayfa.setBounds(154, 22, 134, 31);
		panel.add(lblanasayfa);
		Image img8 = new ImageIcon(this.getClass().getResource("/home.png")).getImage();
     	lblanasayfa.setIcon(new ImageIcon(img8));
     	
     	JLabel lblisim = new JLabel("Ho\u015Fgeldiniz");
     	lblisim.setFont(new Font("Times New Roman", Font.BOLD, 14));
     	lblisim.setBounds(391, 0, 232, 24);
     	panel.add(lblisim);
     	
     	JPanel panel_1 = new JPanel();
     	panel_1.setBackground(new Color(255, 255, 255));
     	panel_1.setBounds(380, 0, 2, 64);
     	panel.add(panel_1);
     	panel_1.setLayout(null);
     	lblisim.setText("Hoþgeldin"+" "+FrmGirisYap.yonisim);
     	
     	lblotel = new JLabel("");
     	lblotel.setFont(new Font("Times New Roman", Font.BOLD, 14));
     	lblotel.setBounds(391, 36, 232, 24);
     	panel.add(lblotel);
     	lblotel.setText("OTEL ADI:");
     	
     	JButton btnPersonel = new JButton("Personel \u0130\u015Flemleri");
     	btnPersonel.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     		FrmYoneticiPersonel frm = new FrmYoneticiPersonel();
     		frm.setVisible(true);
     		FrmYoneticiAnasayfa.this.setVisible(false);
     			
     		}
     	});
     	btnPersonel.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	btnPersonel.setBounds(36, 118, 201, 64);
     	contentPane.add(btnPersonel);
     	
     	JButton btnOda = new JButton("Oda \u0130\u015Flemleri");
     	btnOda.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			FrmYoneticiOda frm = new FrmYoneticiOda();
     			frm.setVisible(true);
     			FrmYoneticiAnasayfa.this.setVisible(false);
     		}
     	});
     	btnOda.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	btnOda.setBounds(361, 118, 195, 64);
     	contentPane.add(btnOda);
     	
     	JButton btnRezervasyon = new JButton("Rezervasyon \u0130\u015Flemleri");
     	btnRezervasyon.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			FrmYoneticiRezervasyon frm = new FrmYoneticiRezervasyon();
     			frm.setVisible(true);
     			FrmYoneticiAnasayfa.this.setVisible(false);
     		}
     	});
     	btnRezervasyon.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	btnRezervasyon.setBounds(36, 243, 201, 64);
     	contentPane.add(btnRezervasyon);
     	
     	JButton btnOdemeBilgi = new JButton("\u00D6deme \u0130\u015Flemleri");
     	btnOdemeBilgi.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			FrmYoneticiOdeme frm = new FrmYoneticiOdeme();
     			frm.setVisible(true);
     			FrmYoneticiAnasayfa.this.setVisible(false);
     			}

     	});
     	btnOdemeBilgi.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	btnOdemeBilgi.setBounds(361, 243, 195, 64);
     	contentPane.add(btnOdemeBilgi);
     	Image img5 = new ImageIcon(this.getClass().getResource("/waiter.png")).getImage();
     	btnPersonel.setIcon(new ImageIcon(img5));
     	Image img6 = new ImageIcon(this.getClass().getResource("/money (1).png")).getImage();
     	Image img7 = new ImageIcon(this.getClass().getResource("/reception.png")).getImage();
     	Image img11 = new ImageIcon(this.getClass().getResource("/room.png")).getImage();
     	
     	btnOda.setIcon(new ImageIcon(img11));
     	btnOdemeBilgi.setIcon(new ImageIcon(img6));
     	btnRezervasyon.setIcon(new ImageIcon(img7));
     	
	}
}
