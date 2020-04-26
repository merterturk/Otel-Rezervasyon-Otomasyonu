package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;

public class FrmMusteriRezervasyonYap extends JFrame {

	private JPanel contentPane;
	private JTextField txtyetiskin;
	private JTextField txtcocuk;
    private JComboBox cmbodalar;
    private JComboBox cmbotelsec;
    private JDateChooser dtgiris;
    private JDateChooser dtcikis;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMusteriRezervasyonYap frame = new FrmMusteriRezervasyonYap();
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
	static int toplamfiyat;
	Connection cnn=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	static String seciliOtelID;
	static String Oteladi;
	static int gun;
	static String odaID;
	public FrmMusteriRezervasyonYap() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String sql="Select OtelID,OtelAdi from otel where Durumu='1' ";
				try {
					cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(sql);
					getirilen=sorguifadesi.executeQuery();
					
					while(getirilen.next()) {
						cmbotelsec.addItem(getirilen.getString("OtelAdi"));
						
						
						//cmbotel.addItem(getirilen.getString("OtelAdi"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 580, 50);
		panel.setLayout(null);
		panel.setBackground(new Color(50, 205, 50));
		contentPane.add(panel);
		
		JLabel lblRezervasyon = new JLabel("REZERVASYON \u0130\u015ELEMLER\u0130\r\n");
		lblRezervasyon.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblRezervasyon.setBounds(144, 11, 286, 28);
		panel.add(lblRezervasyon);
	 	Image img10 = new ImageIcon(this.getClass().getResource("/front-desk.png")).getImage();
	 	lblRezervasyon.setIcon(new ImageIcon(img10));
	 	
	 	JButton btngeri = new JButton("");
	 	btngeri.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			FrmMusteriAnasayfa frm = new FrmMusteriAnasayfa();
    			frm.setVisible(true);
    			FrmMusteriRezervasyonYap.this.setVisible(false);
	 		}
	 	});
	 	btngeri.setBounds(491, 16, 89, 33);
	 	panel.add(btngeri);
	 	Image img15 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
    	btngeri.setIcon(new ImageIcon(img15));
    	
    	JLabel lblNewLabel = new JLabel("Otel:");
    	lblNewLabel.setBounds(10, 61, 70, 23);
    	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
    	contentPane.add(lblNewLabel);
    	
        cmbotelsec = new JComboBox();
        cmbotelsec.setModel(new DefaultComboBoxModel(new String[] {"Otel Se\u00E7imini Yap\u0131n\u0131z"}));
        cmbotelsec.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		 String sql="Select OtelID from Otel where OtelAdi='"+cmbotelsec.getSelectedItem()+"'";
 				
 				try {
 					cnn=Baglanti.baglan();
 					sorguifadesi=cnn.prepareStatement(sql);
 					getirilen=sorguifadesi.executeQuery();
 				
 					if(getirilen.next()) {
 					seciliOtelID=getirilen.getString("OtelID");
 					
 					}
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
 			}
        	
        });
    	cmbotelsec.setBounds(66, 61, 160, 22);
    	contentPane.add(cmbotelsec);
    	
    	JLabel lblGiriTarihi = new JLabel("Giri\u015F Tarihi:");
    	lblGiriTarihi.setBounds(10, 105, 96, 23);
    	lblGiriTarihi.setFont(new Font("Times New Roman", Font.BOLD, 15));
    	contentPane.add(lblGiriTarihi);
    	
    	JLabel lblkTarihi = new JLabel("\u00C7\u0131k\u0131\u015F Tarihi:");
    	lblkTarihi.setBounds(203, 105, 96, 23);
    	lblkTarihi.setFont(new Font("Times New Roman", Font.BOLD, 15));
    	contentPane.add(lblkTarihi);
    	
    	dtgiris = new JDateChooser();
    	dtgiris.setBounds(98, 108, 95, 20);
    	contentPane.add(dtgiris);
    	
    	dtcikis = new JDateChooser();
    	dtcikis.setBounds(289, 105, 95, 20);
    	contentPane.add(dtcikis);
    	
    	JLabel lblOdalar = new JLabel("Odalar:");
    	lblOdalar.setFont(new Font("Times New Roman", Font.BOLD, 15));
    	lblOdalar.setBounds(10, 154, 70, 23);
    	contentPane.add(lblOdalar);
    	
    	 cmbodalar = new JComboBox();
    	 cmbodalar.addItemListener(new ItemListener() {
    	 	public void itemStateChanged(ItemEvent e) {
    	 		 String sql="Select * from oda where OtelID='"+seciliOtelID+"' AND ODANO='"+cmbodalar.getSelectedItem()+"'";
 				
 				try {
 					cnn=Baglanti.baglan();
 					sorguifadesi=cnn.prepareStatement(sql);
 					getirilen=sorguifadesi.executeQuery();
 				 int sayac=0;
 					if(getirilen.next()) {
 						sayac++;
 						if(sayac==1) {
 							odaID=getirilen.getString("ID");
 							toplamfiyat=Integer.parseInt(getirilen.getString("FIYAT"));
 						JOptionPane optionPane = new JOptionPane("ODANO:"+getirilen.getString("ODANO")+"\n"+"ODATURU:"+getirilen.getString("ODATURU")+"\n"+"ODAFÝYAT:"+" "+getirilen.getString("FIYAT")+"TL"+"\n"+"ODABÝLGÝSÝ:"+" "+getirilen.getString("ACÝKLAMA"),JOptionPane.NO_OPTION);
						JDialog dialog = optionPane.createDialog("ODA BÝLGÝLERÝ");
						dialog.setAlwaysOnTop(true); // to show top of all other application
						dialog.setVisible(true); // 
 						}
 						
 			 
 					}
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
 			
    	 		
    	 	}
    	 });
    	 cmbodalar.setModel(new DefaultComboBoxModel(new String[] {"Oda Se\u00E7iniz"}));
    	cmbodalar.setBounds(66, 155, 127, 22);
    	contentPane.add(cmbodalar);
    	SimpleDateFormat giris= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat cikis= new SimpleDateFormat("yyyy-MM-dd");
    	JButton btnOdaListele = new JButton("ODALARI L\u0130STELE");
    	btnOdaListele.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			cmbodalar.removeAllItems();
    			cmbodalar.addItem("Oda seçimini Yapýnýz");
    			String dategiris=giris.format(dtgiris.getDate());
    			String datecikis=cikis.format(dtcikis.getDate());
    			String sql="SELECT * FROM oda AS a WHERE ID NOT IN(SELECT OdaID FROM rezervasyon  WHERE ('"+dategiris+"'<CikisTarih AND '"+datecikis+"'>GirisTarih)) AND OtelID='"+seciliOtelID+"' AND a.Durumu='1' ";
				
				try {
					cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(sql);
					getirilen=sorguifadesi.executeQuery();
				
					while(getirilen.next()) {
					cmbodalar.addItem(getirilen.getString("ODANO"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
    		
    	});
    	btnOdaListele.setBounds(414, 105, 166, 24);
    	contentPane.add(btnOdaListele);
    	
    	JLabel lblYetikinSays = new JLabel("Yeti\u015Fkin Say\u0131s\u0131:");
    	lblYetikinSays.setFont(new Font("Times New Roman", Font.BOLD, 15));
    	lblYetikinSays.setBounds(0, 207, 106, 23);
    	contentPane.add(lblYetikinSays);
    	
    	JLabel lblocukSays = new JLabel("\u00C7ocuk Say\u0131s\u0131:");
    	lblocukSays.setFont(new Font("Times New Roman", Font.BOLD, 15));
    	lblocukSays.setBounds(216, 207, 106, 23);
    	contentPane.add(lblocukSays);
    	
    	txtyetiskin = new JTextField();
    	txtyetiskin.setBounds(110, 209, 96, 20);
    	contentPane.add(txtyetiskin);
    	txtyetiskin.setColumns(10);
    	
    	txtcocuk = new JTextField();
    	txtcocuk.setColumns(10);
    	txtcocuk.setBounds(312, 209, 96, 20);
    	contentPane.add(txtcocuk);
    	
    	JButton btnRezervasyonYap = new JButton("Rezervasyon YAP");
    	btnRezervasyonYap.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			SimpleDateFormat giris=new SimpleDateFormat("yyyy-MM-dd");
    			long ilkgun=dtgiris.getDate().getTime();
    			long songun=dtcikis.getDate().getTime();
    			long fark=songun-ilkgun;
    			gun =(int)fark/(1000*60*60*24);
    			String dategiris=giris.format(dtgiris.getDate());
    			String datecikis=giris.format(dtcikis.getDate());
    			int result = JOptionPane.showConfirmDialog(null,"Rezervasyon Ýþleminizi Onaylýyor musunuz?,Onayladýktan sonra Ödeme Kýsmýna aktarýlacaksýnýz.", "UYARI",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
              if(result==JOptionPane.NO_OPTION) {
             	 
              }
			 
			     
			      if(result==JOptionPane.YES_OPTION) {
			    	  try {
			    		  cnn=Baglanti.baglan();
							String sorgu="INSERT INTO `rezervasyon`(`OtelID`,`OdaID`,`MusteriID`,`GirisTarih`,`CikisTarih`,`YetiskinSayisi`,`CocukSayisi`) VALUES(?,?,?,?,?,?,?)";
							 
							 	sorguifadesi=cnn.prepareStatement(sorgu);
									sorguifadesi.setString(1,seciliOtelID);
									sorguifadesi.setString(2,odaID);
					                sorguifadesi.setString(3,FrmGirisYap.musID);
					                sorguifadesi.setString(4,dategiris);
					                sorguifadesi.setString(5,datecikis);
					                sorguifadesi.setString(6,txtyetiskin.getText());
					                sorguifadesi.setString(7,txtcocuk.getText());
					               
					                
					                int row=sorguifadesi.executeUpdate();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane optionPane = new JOptionPane("Rezarvasyonunuz Baþarýyla Oluþturuldu,ÝYÝ TATÝLLER",JOptionPane.NO_OPTION);
								JDialog dialog = optionPane.createDialog("Baþarýlý");
								dialog.setAlwaysOnTop(true); // to show top of all other application
								dialog.setVisible(true); // 
								FrmMusteriOdemeYap frm = new FrmMusteriOdemeYap();
								frm.setVisible(true);
								FrmMusteriRezervasyonYap.this.setVisible(false);
								
								}
						
			      
			      
			      
			      
    		}
    	});
    	btnRezervasyonYap.setBounds(159, 288, 140, 29);
    	contentPane.add(btnRezervasyonYap);
	}
}
