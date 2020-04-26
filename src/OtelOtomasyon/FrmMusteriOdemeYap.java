package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FrmMusteriOdemeYap extends JFrame {

	private JPanel contentPane;
	private JTextField txtfiyat;
	private JTextField txtkartno;
	private JTextField txtcvc;
	private JTextField txtsonkullanmatarihi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMusteriOdemeYap frame = new FrmMusteriOdemeYap();
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
	public FrmMusteriOdemeYap() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				int fiyathesapla=FrmMusteriRezervasyonYap.gun*FrmMusteriRezervasyonYap.toplamfiyat;
				String toplam=Integer.toString(fiyathesapla);
				txtfiyat.setText(toplam);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(50, 205, 50));
		panel.setBounds(0, 0, 557, 50);
		contentPane.add(panel);
		
		JLabel lblodemeyap = new JLabel("\u00D6DEME \u0130\u015ELEM\u0130");
		lblodemeyap.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblodemeyap.setBounds(161, 11, 286, 28);
		panel.add(lblodemeyap);
		
		JLabel lblNewLabel = new JLabel("F\u0130YAT:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(0, 61, 93, 23);
		contentPane.add(lblNewLabel);
		
		txtfiyat = new JTextField();
		txtfiyat.setEnabled(false);
		txtfiyat.setBounds(148, 61, 114, 20);
		contentPane.add(txtfiyat);
		txtfiyat.setColumns(10);
		
		JComboBox cmbodemeturu = new JComboBox();
		cmbodemeturu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cmbodemeturu.getSelectedItem()=="Kredi Kartý") {
					txtcvc.setEnabled(true);
					txtkartno.setEnabled(true);
					txtsonkullanmatarihi.setEnabled(true);
				}
				else {
					txtcvc.setEnabled(false);
					txtkartno.setEnabled(false);
					txtsonkullanmatarihi.setEnabled(false);
				}
				
			}
		});
		cmbodemeturu.setModel(new DefaultComboBoxModel(new String[] {"\u00D6deme T\u00FCr\u00FCn\u00FC Se\u00E7iniz.", "Nakit", "Kredi Kart\u0131", "EFT", "Havale"}));
		cmbodemeturu.setBounds(148, 92, 136, 22);
		contentPane.add(cmbodemeturu);
		
		JLabel lbldemeTr = new JLabel("\u00D6DEME T\u00DCR\u00DC:");
		lbldemeTr.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbldemeTr.setBounds(0, 95, 122, 23);
		contentPane.add(lbldemeTr);
		
		JLabel lblKartNo = new JLabel("KART NO:");
		lblKartNo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblKartNo.setBounds(0, 139, 93, 23);
		contentPane.add(lblKartNo);
		
		txtkartno = new JTextField();
		txtkartno.setEnabled(false);
		txtkartno.setColumns(10);
		txtkartno.setBounds(152, 141, 114, 20);
		contentPane.add(txtkartno);
		
		JLabel lblCvc = new JLabel("CVC:");
		lblCvc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCvc.setBounds(0, 179, 93, 23);
		contentPane.add(lblCvc);
		
		txtcvc = new JTextField();
		txtcvc.setEnabled(false);
		txtcvc.setColumns(10);
		txtcvc.setBounds(152, 181, 114, 20);
		contentPane.add(txtcvc);
		
		JLabel lblSonKullanmaTarihi = new JLabel("Son Kullanma Tarihi:");
		lblSonKullanmaTarihi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSonKullanmaTarihi.setBounds(0, 221, 143, 23);
		contentPane.add(lblSonKullanmaTarihi);
		
		txtsonkullanmatarihi = new JTextField();
		txtsonkullanmatarihi.setEnabled(false);
		txtsonkullanmatarihi.setColumns(10);
		txtsonkullanmatarihi.setBounds(152, 223, 114, 20);
		contentPane.add(txtsonkullanmatarihi);
		
		JButton btnodemeyap = new JButton("\u00D6deme Yap");
		btnodemeyap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			LocalDate tarih=LocalDate.now();
				 try {
		    		  cnn=Baglanti.baglan();
						String sorgu="INSERT INTO `ödeme`(`OtelID`,`OdaID`,`MusteriID`,`Tutar`,`OdemeTuru`,`OdemeTarihi`,`Kartno`,`Cvc`,`KartSonKullanmaTarih`) VALUES(?,?,?,?,?,?,?,?,?)";
						 
						 	    sorguifadesi=cnn.prepareStatement(sorgu);
								sorguifadesi.setString(1,FrmMusteriRezervasyonYap.seciliOtelID);
								sorguifadesi.setString(2,FrmMusteriRezervasyonYap.odaID);
				                sorguifadesi.setString(3,FrmGirisYap.musID);
				                sorguifadesi.setString(4,txtfiyat.getText());
				                sorguifadesi.setString(5,(String) cmbodemeturu.getSelectedItem());
				                sorguifadesi.setString(6,DateTimeFormatter.ofPattern("yyyy-MM-dd").format(tarih));
				                sorguifadesi.setString(7,txtkartno.getText());
				                sorguifadesi.setString(8,txtcvc.getText());
				                sorguifadesi.setString(9,txtsonkullanmatarihi.getText());
				                int row=sorguifadesi.executeUpdate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane optionPane = new JOptionPane("Ödemeniz Baþarýyla Gerçekleþti",JOptionPane.NO_OPTION);
							JDialog dialog = optionPane.createDialog("Baþarýlý");
							dialog.setAlwaysOnTop(true); // to show top of all other application
							dialog.setVisible(true); // 
							FrmMusteriAnasayfa frm = new FrmMusteriAnasayfa();
							frm.setVisible(true);
							FrmMusteriOdemeYap.this.setVisible(false);
							}
		
					
			
		});
		btnodemeyap.setBounds(152, 265, 114, 23);
		contentPane.add(btnodemeyap);
		
		Image img10 = new ImageIcon(this.getClass().getResource("/para.png")).getImage();
	 	lblodemeyap.setIcon(new ImageIcon(img10));
	}
}
