package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class FrmKayitOl extends JFrame {

	private JPanel contentPane;
	private JTextField txtad;
	private JTextField txtsoyad;
	private JTextField txtTC;
	private JTextField txttelefon;
	private JTextField txtkulad;
	private JTextField txtsifre;
	static Connection cnn;
	static PreparedStatement myStat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmKayitOl frame = new FrmKayitOl();
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
	void temizle() {
		txtad.setText(null);
		txtkulad.setText(null);
		txtsifre.setText(null);
		txtsoyad.setText(null);
		txtTC.setText(null);
		txttelefon.setText(null);
	}
	public FrmKayitOl() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 349, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblKayit = new JLabel("M\u00DC\u015ETER\u0130 KAYIT");
		lblKayit.setBounds(98, 30, 152, 24);
		lblKayit.setFont(new Font("Times New Roman", Font.BOLD, 15));
     	panel.add(lblKayit);
		Image img = new ImageIcon(this.getClass().getResource("/staff.png")).getImage();
		lblKayit.setIcon(new ImageIcon(img));
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmAnasayfa frm = new FrmAnasayfa();
				frm.setVisible(true);
				FrmKayitOl.this.setVisible(false);
			}
		});
		btngeri.setBounds(260, 0, 89, 33);
		panel.add(btngeri);
		
		JLabel lblNewLabel = new JLabel("AD:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 97, 40, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblSoyad = new JLabel("SOYAD:");
		lblSoyad.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblSoyad.setBounds(10, 137, 60, 32);
		contentPane.add(lblSoyad);
		
		JLabel lblTc = new JLabel("TC:");
		lblTc.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTc.setBounds(10, 174, 60, 32);
		contentPane.add(lblTc);
		
		JLabel lblTelefon = new JLabel("TELEFON:");
		lblTelefon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTelefon.setBounds(10, 217, 74, 32);
		contentPane.add(lblTelefon);
		
		JLabel lblKullancAd = new JLabel("KULLANICI ADI:");
		lblKullancAd.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblKullancAd.setBounds(10, 260, 104, 32);
		contentPane.add(lblKullancAd);
		
		JLabel lblifre = new JLabel("\u015E\u0130FRE:");
		lblifre.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblifre.setBounds(10, 303, 74, 32);
		contentPane.add(lblifre);
		
		JButton btnmüsterikayitol = new JButton("KAYIT OL");
		btnmüsterikayitol.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnmüsterikayitol.setBounds(104, 356, 133, 36);
		contentPane.add(btnmüsterikayitol);
		Image img3 = new ImageIcon(this.getClass().getResource("/insert.png")).getImage();
		btnmüsterikayitol.setIcon(new ImageIcon(img3));
		
		txtad = new JTextField();
		txtad.setBounds(124, 103, 96, 20);
		contentPane.add(txtad);
		txtad.setColumns(10);
		
		txtsoyad = new JTextField();
		txtsoyad.setColumns(10);
		txtsoyad.setBounds(124, 143, 96, 20);
		contentPane.add(txtsoyad);
		
		txtTC = new JTextField();
		txtTC.setColumns(10);
		txtTC.setBounds(124, 180, 96, 20);
		contentPane.add(txtTC);
		
		txttelefon = new JTextField();
		txttelefon.setColumns(10);
		txttelefon.setBounds(124, 223, 96, 20);
		contentPane.add(txttelefon);
		
		txtkulad = new JTextField();
		txtkulad.setColumns(10);
		txtkulad.setBounds(124, 264, 96, 20);
		contentPane.add(txtkulad);
		
		txtsifre = new JTextField();
		txtsifre.setColumns(10);
		txtsifre.setBounds(124, 309, 96, 20);
		contentPane.add(txtsifre);
		
		Image img4 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
         	btngeri.setIcon(new ImageIcon(img4));
         	
         	
         	btnmüsterikayitol.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String ad=txtad.getText();
    				String soyad=txtsoyad.getText();
    				String tc=txtTC.getText();
    				String telefon=txttelefon.getText();
    				String kulad=txtkulad.getText();
    				String sifre=txtsifre.getText();
    				String sql_sorgu;
    				if(ad.isEmpty()|| soyad.isEmpty() || tc.isEmpty() || telefon.isEmpty() || kulad.isEmpty() || sifre.isEmpty())
    				{
    					JOptionPane optionPane = new JOptionPane("Lütfen Alanlarýn tümünü doldurunuz!",JOptionPane.WARNING_MESSAGE);
    					JDialog dialog = optionPane.createDialog("HATA");
    					dialog.setAlwaysOnTop(true); // to show top of all other application
    					dialog.setVisible(true); // to visible the dialog
    				}
    				else {
    					
    					cnn=Baglanti.baglan();
    					sql_sorgu="INSERT INTO `musteri`(`MusteriAD`,`MusteriSoyad`,`MusteriTC`,`MusteriTel`,`MusteriKad`,`Musterisifre`) VALUES(?,?,?,?,?,?)"; 
                          try {
							myStat=cnn.prepareStatement(sql_sorgu);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
                       try {
						myStat.setString(1,ad);
						myStat.setString(2,soyad);
	                    myStat.setString(3,tc);
	                    myStat.setString(4,telefon);
	                    myStat.setString(5,kulad);
	                    myStat.setString(6,sifre);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                       try {
						int row=myStat.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                      
    					
    					JOptionPane optionPane = new JOptionPane("Kayýt Eklendi",JOptionPane.NO_OPTION);
    					JDialog dialog = optionPane.createDialog("Baþarýlý");
    					dialog.setAlwaysOnTop(true); // to show top of all other application
    					dialog.setVisible(true); // 
    					temizle();
    				}
    			}
    		});
 	}
}
