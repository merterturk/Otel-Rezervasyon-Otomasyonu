package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.cj.protocol.Resultset;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
public class FrmGirisYap extends JFrame {

	private JPanel contentPane;
	private JTextField txtmuskulad;
	private JTextField txtyonkulad;
	private JTextField txtpersonelkulad;
	private JTextField txtrootad;
	private JPasswordField txtmussifre;
	private JPasswordField txtyonsifre;
	private JPasswordField txtpersonelsifre;
	private JPasswordField txtrootsifre;
	public static String rootad;
	public static String rootsifre;
	
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGirisYap frame = new FrmGirisYap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
		});
	}
	


	public  static String yonID;
	public static String yonadi;
	public static String yonsoyad;
	public static String yonisim;
	public static String persID;
	public static String persad;
	public static String persoyad;
	public static String persisim;
	public static String OtelID;
	public static String musID;
	public static String musad;
	public static String mussoyad;
	
	
	
	
	/**
	 * Create the frame.
	 */
	public FrmGirisYap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		panel.setBounds(0, 0, 787, 67);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblgiris = new JLabel("G\u0130R\u0130\u015E YAP");
		lblgiris.setBounds(348, 21, 111, 26);
		lblgiris.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel.add(lblgiris);
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			FrmAnasayfa frm = new FrmAnasayfa();
			frm.setVisible(true);
			FrmGirisYap.this.setVisible(false);
			}
		});
		btngeri.setBounds(698, 27, 89, 40);
		panel.add(btngeri);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 78, 198, 140);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(175, 238, 238));
		panel_2.setBounds(0, 0, 198, 37);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblRootGiri = new JLabel("M\u00FC\u015Fteri Giri\u015Fi");
		lblRootGiri.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblRootGiri.setHorizontalAlignment(SwingConstants.CENTER);
		lblRootGiri.setBounds(56, 0, 74, 26);
		panel_2.add(lblRootGiri);
		
		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 AD:");
		lblKullancAd.setBounds(10, 48, 74, 14);
		panel_1.add(lblKullancAd);
		
		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setBounds(10, 78, 74, 14);
		panel_1.add(lblifre);
		
		txtmuskulad = new JTextField();
		txtmuskulad.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtmuskulad.setBounds(94, 44, 96, 20);
		panel_1.add(txtmuskulad);
		txtmuskulad.setColumns(10);
		
		
		JButton btnMusterigiris = new JButton("Giri\u015F Yap");
		//Müþteri Giriþ Ýþlemi
		btnMusterigiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kulad=txtmuskulad.getText();
				String sifre=txtmussifre.getText();	
				
			if(kulad.isEmpty()|| sifre.isEmpty()) { //Textboxlar Boþsa Hata ver
				JOptionPane optionPane = new JOptionPane("Lütfen Alanlarýn tümünü doldurunuz!",JOptionPane.WARNING_MESSAGE);
				JDialog dialog = optionPane.createDialog("HATA");
				dialog.setAlwaysOnTop(true); // to show top of all other application
				dialog.setVisible(true); // to visible the dialog
			}
			
			else { //Veritabanýndaki Kullanýcýadi ve þifreyle textboxtakileri kontrol ettirme
				Connection cnn=null;	
			    cnn=Baglanti.baglan();
				PreparedStatement sorguifadesi=null;
				ResultSet getirilen=null;
			     String sql;
			 sql="Select * from musteri where MusteriKad=? and Musterisifre=? and Durumu='1' ";	
			try {
			 sorguifadesi=cnn.prepareStatement(sql);
			 sorguifadesi.setString(1,txtmuskulad.getText().trim());
			 sorguifadesi.setString(2, txtmussifre.getText().trim());
			 getirilen=sorguifadesi.executeQuery();
			 if(!getirilen.next()) { //Eþleþme yoksa Hata ver 
				 JOptionPane optionPane = new JOptionPane("Kullanýcý Adý veya Þifre Hatalý",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("HATA");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); // to visible the dialog
			 }
			 else {//Eþleþtiyse Müþteriyi Forma Aktar
				 musID=getirilen.getString("MusteriID");
				 musad=getirilen.getString("MusteriAD");
				 mussoyad=getirilen.getString("MusteriSoyad");
			
				 JOptionPane optionPane = new JOptionPane("Kullanýcý Adý ve Giriþi Doðru",JOptionPane.NO_OPTION);
					JDialog dialog = optionPane.createDialog("Baþarýlý");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); // to visible the dialog
					FrmMusteriAnasayfa frm = new FrmMusteriAnasayfa();
					 frm.setVisible(true);
					 FrmGirisYap.this.setVisible(false);
			 }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
			}
		});
		btnMusterigiris.setBounds(70, 103, 128, 37);
		panel_1.add(btnMusterigiris);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(249, 78, 188, 140);
		contentPane.add(panel_1_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(0, 206, 209));
		panel_2_1.setBounds(0, 0, 188, 37);
		panel_1_1.add(panel_2_1);
		
		JLabel lblYneticiGirii = new JLabel("Y\u00F6netici Giri\u015Fi");
		lblYneticiGirii.setHorizontalAlignment(SwingConstants.CENTER);
		lblYneticiGirii.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblYneticiGirii.setBounds(56, 0, 74, 26);
		panel_2_1.add(lblYneticiGirii);
		
		JLabel lblKullancAd_1 = new JLabel("Kullan\u0131c\u0131 AD:");
		lblKullancAd_1.setBounds(0, 48, 74, 14);
		panel_1_1.add(lblKullancAd_1);
		
		JLabel lblifre_1 = new JLabel("\u015Eifre:");
		lblifre_1.setBounds(10, 78, 74, 14);
		panel_1_1.add(lblifre_1);
		
		txtyonkulad = new JTextField();
		txtyonkulad.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtyonkulad.setColumns(10);
		txtyonkulad.setBounds(82, 45, 96, 20);
		panel_1_1.add(txtyonkulad);
		
		JButton btnYöneticiGiris = new JButton("Giri\u015F Yap");
		//Yönetici Giriþi
		btnYöneticiGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kulad=txtyonkulad.getText();
				String sifre=txtyonsifre.getText();
				if(kulad.isEmpty() ||sifre.isEmpty()) { //TEXTBOX boþsa hata ver.
					JOptionPane optionPane = new JOptionPane("Lütfen Alanlarýn tümünü doldurunuz!",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("HATA");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); // to visible the dialog
				}
				else {//Veritabanýndaki Kullanýcý adý ve þifreyle textboxtakiler eþleþiyormu kontrol et
					Connection cnn=null;	
				    cnn=Baglanti.baglan();
					PreparedStatement sorguifadesi=null;
					ResultSet getirilen=null;
				     String sql;
				 sql="Select * from yönetici where KullaniciAdi=? and Sifre=? and Durumu='1' ";	
				try {
				 sorguifadesi=cnn.prepareStatement(sql);
				 sorguifadesi.setString(1,txtyonkulad.getText().trim());
				 sorguifadesi.setString(2, txtyonsifre.getText().trim());
				 getirilen=sorguifadesi.executeQuery();
				 if(!getirilen.next()) { //Eþleþmiyorsa Hata ver
					 JOptionPane optionPane = new JOptionPane("Kullanýcý Adý veya Þifre Hatalý",JOptionPane.WARNING_MESSAGE);
						JDialog dialog = optionPane.createDialog("HATA");
						dialog.setAlwaysOnTop(true); // to show top of all other application
						dialog.setVisible(true); // to visible the dialog
				 }
				 else { //Eþleþiyorsa Yönetici Formuna aktar
					yonID=getirilen.getString("ID");
					yonadi=getirilen.getString("AD");
					yonsoyad=getirilen.getString("Soyad");
					yonisim=yonadi+" "+yonsoyad;
					
					
					 JOptionPane optionPane = new JOptionPane("Kullanýcý Adý ve Giriþi Doðru",JOptionPane.NO_OPTION);
						JDialog dialog = optionPane.createDialog("Baþarýlý");
						dialog.setAlwaysOnTop(true); // to show top of all other application
						dialog.setVisible(true); // to visible the dialog
						FrmYoneticiAnasayfa frm = new FrmYoneticiAnasayfa();
						frm.setVisible(true);
						FrmGirisYap.this.setVisible(false);
				 }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
		
			}
		});
		btnYöneticiGiris.setBounds(70, 103, 118, 37);
		panel_1_1.add(btnYöneticiGiris);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_2.setLayout(null);
		panel_1_2.setBounds(514, 78, 188, 140);
		contentPane.add(panel_1_2);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBackground(new Color(0, 191, 255));
		panel_2_2.setBounds(0, 0, 188, 37);
		panel_1_2.add(panel_2_2);
		
		JLabel lblPersonelGirii = new JLabel("Personel Giri\u015Fi");
		lblPersonelGirii.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonelGirii.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPersonelGirii.setBounds(56, 0, 74, 26);
		panel_2_2.add(lblPersonelGirii);
		
		JLabel lblKullancAd_2 = new JLabel("Kullan\u0131c\u0131 AD:");
		lblKullancAd_2.setBounds(0, 48, 74, 14);
		panel_1_2.add(lblKullancAd_2);
		
		JLabel lblifre_2 = new JLabel("\u015Eifre:");
		lblifre_2.setBounds(0, 78, 74, 14);
		panel_1_2.add(lblifre_2);
		
		txtpersonelkulad = new JTextField();
		txtpersonelkulad.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtpersonelkulad.setColumns(10);
		txtpersonelkulad.setBounds(82, 45, 96, 20);
		panel_1_2.add(txtpersonelkulad);
		
		JButton btnPersonelGiriþ = new JButton("Giri\u015F Yap");
		//Personel Giriþ
		btnPersonelGiriþ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kulad=txtpersonelkulad.getText();
				String sifre=txtpersonelsifre.getText();
				if(kulad.isEmpty()|| sifre.isEmpty()) { //Textboxlar boþsa hata ver
					JOptionPane optionPane = new JOptionPane("Lütfen Alanlarýn tümünü doldurunuz!",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("HATA");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); // to visible the dialog
				}
					else {//Veritabanýndaki kullanýcý adý ve þifreyle eþleþiyormu kontrol et
						Connection cnn=null;	
					    cnn=Baglanti.baglan();
						PreparedStatement sorguifadesi=null;
						ResultSet getirilen=null;
					     String sql;
					 sql="Select * from personel where KullaniciAdi=? and Sifre=? and Durumu='1' ";	
					try {
					 sorguifadesi=cnn.prepareStatement(sql);
					 sorguifadesi.setString(1,txtpersonelkulad.getText().trim());
					 sorguifadesi.setString(2, txtpersonelsifre.getText().trim());
					 getirilen=sorguifadesi.executeQuery();
					 if(!getirilen.next()) {//Eþleþmiyorsa hata ver
						 JOptionPane optionPane = new JOptionPane("Kullanýcý Adý veya Þifre Hatalý",JOptionPane.WARNING_MESSAGE);
							JDialog dialog = optionPane.createDialog("HATA");
							dialog.setAlwaysOnTop(true); // to show top of all other application
							dialog.setVisible(true); // to visible the dialog
					 }
					
					 else { //Eþleþiyorsa Personel Formuna aktar
						 persID=getirilen.getString("PersonelID");
							persad=getirilen.getString("Adi");
							persoyad=getirilen.getString("Soyadi");
							persisim=persad+" "+persoyad;
                       OtelID=getirilen.getString("OtelID");	
						 
						 JOptionPane optionPane = new JOptionPane("Kullanýcý Adý ve Giriþi Doðru",JOptionPane.NO_OPTION);
							JDialog dialog = optionPane.createDialog("Baþarýlý");
							dialog.setAlwaysOnTop(true); // to show top of all other application
							dialog.setVisible(true);
							FrmPersonelAnasayfa frm = new FrmPersonelAnasayfa();
							frm.setVisible(true);
							FrmGirisYap.this.setVisible(false);
													
							// to visible the dialog
					 }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnPersonelGiriþ.setBounds(71, 103, 117, 37);
		panel_1_2.add(btnPersonelGiriþ);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_3.setLayout(null);
		panel_1_3.setBounds(249, 259, 188, 140);
		contentPane.add(panel_1_3);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.setLayout(null);
		panel_2_3.setBackground(new Color(124, 252, 0));
		panel_2_3.setBounds(0, 0, 188, 37);
		panel_1_3.add(panel_2_3);
		
		JLabel lblRootGirii = new JLabel("Root Giri\u015Fi");
		lblRootGirii.setHorizontalAlignment(SwingConstants.CENTER);
		lblRootGirii.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblRootGirii.setBounds(56, 0, 97, 26);
		panel_2_3.add(lblRootGirii);
		
		JLabel lblKullancAd_3 = new JLabel("Kullan\u0131c\u0131 AD:");
		lblKullancAd_3.setBounds(0, 48, 74, 14);
		panel_1_3.add(lblKullancAd_3);
		
		JLabel lblifre_3 = new JLabel("\u015Eifre:");
		lblifre_3.setBounds(0, 78, 74, 14);
		panel_1_3.add(lblifre_3);
		
		txtrootad = new JTextField();
		txtrootad.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtrootad.setColumns(10);
		txtrootad.setBounds(82, 45, 96, 20);
		panel_1_3.add(txtrootad);
		String rootad;
		JButton btnRootGiris = new JButton("Giri\u015F Yap");
		//Root Giriþ
		btnRootGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rootad=txtrootad.getText();
				String rootsifre=txtrootsifre.getText();
				if(rootad.isEmpty()||rootsifre.isEmpty()) { //Textboxlar boþsa hata ver
					JOptionPane optionPane = new JOptionPane("Lütfen Alanlarýn tümünü doldurunuz!",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("HATA");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); // to visible the dialog
				}
				 
					else { //Veritabanýndaki kullanýcý adý ve þifreyle eþleþiyormu
						Connection cnn=null;	
					    cnn=Baglanti.baglan();
						PreparedStatement sorguifadesi=null;
						ResultSet getirilen=null;
					     String sql;
					 sql="Select * from root where KullaniciAdi=? and Sifre=? and Durumu='1' ";	
					try {
					 sorguifadesi=cnn.prepareStatement(sql);
					 sorguifadesi.setString(1,txtrootad.getText().trim());
					 sorguifadesi.setString(2, txtrootsifre.getText().trim());
					 getirilen=sorguifadesi.executeQuery();
					 if(!getirilen.next()) { //Eþleþmiyorsa hata ver
						 JOptionPane optionPane = new JOptionPane("Kullanýcý Adý veya Þifre Hatalý",JOptionPane.WARNING_MESSAGE);
							JDialog dialog = optionPane.createDialog("HATA");
							dialog.setAlwaysOnTop(true); // to show top of all other application
							dialog.setVisible(true); // to visible the dialog
					 }
					 else { //Eþleþiyorsa Root Formuna Aktar
						 JOptionPane optionPane = new JOptionPane("Kullanýcý Adý ve Giriþi Doðru",JOptionPane.NO_OPTION);
							JDialog dialog = optionPane.createDialog("Baþarýlý");
							dialog.setAlwaysOnTop(true); // to show top of all other application
							dialog.setVisible(true); // to visible the dialog
							FrmRootAnasayfa frm = new FrmRootAnasayfa();
							frm.setVisible(true);
						   FrmGirisYap.this.setVisible(false);
							
					 }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.print(e1.getMessage());
					}
						
					}
					
				}
			
		});
		btnRootGiris.setBounds(59, 103, 119, 37);
		panel_1_3.add(btnRootGiris);
		Image img5 = new ImageIcon(this.getClass().getResource("/key.png")).getImage();
		btnMusterigiris.setIcon(new ImageIcon(img5));
		
		txtmussifre = new JPasswordField();
		txtmussifre.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtmussifre.setEchoChar('*');
		txtmussifre.setBounds(94, 74, 96, 20);
		panel_1.add(txtmussifre);
		btnYöneticiGiris.setIcon(new ImageIcon(img5));
		
		txtyonsifre = new JPasswordField();
		txtyonsifre.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtyonsifre.setEchoChar('*');
		txtyonsifre.setBounds(82, 75, 96, 20);
		panel_1_1.add(txtyonsifre);
		btnRootGiris.setIcon(new ImageIcon(img5));
		
		txtrootsifre = new JPasswordField();
		txtrootsifre.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtrootsifre.setEchoChar('*');
		txtrootsifre.setBounds(82, 75, 96, 20);
		panel_1_3.add(txtrootsifre);
		btnPersonelGiriþ.setIcon(new ImageIcon(img5));
		
		txtpersonelsifre = new JPasswordField();
		txtpersonelsifre.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtpersonelsifre.setEchoChar('*');
		txtpersonelsifre.setBounds(82, 75, 96, 20);
		panel_1_2.add(txtpersonelsifre);
		Image img4 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
     	btngeri.setIcon(new ImageIcon(img4));
     	
     	Image img6 = new ImageIcon(this.getClass().getResource("/enter.png")).getImage();
     	lblgiris.setIcon(new ImageIcon(img6));
     	
     	
	}
}
