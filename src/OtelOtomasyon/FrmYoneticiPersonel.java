package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmYoneticiPersonel extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtAD;
	private JTextField txtSoyad;
	private JTextField txtTC;
	private JTextField txtTelefon;
	private JTextField txtGorev;
	private JTextField txtMaas;
	private JTextField txtkulad;
	private JTextField txtSifre;
	private JTextArea txtAdres;
	private JLabel lblpersonelmaastoplam;
    private JLabel lblpersonelmaas;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmYoneticiPersonel frame = new FrmYoneticiPersonel();
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
	int sum=0;
	Connection cnn=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	private JTable table;
	private JTextField txtOtelID;
	 void temizle() {
		 txtAD.setText(null);
		 txtGorev.setText(null);
		 txtkulad.setText(null);
		 txtMaas.setText(null);
		 txtSifre.setText(null);
		 txtSoyad.setText(null);
		 txtTC.setText(null);
		 txtTelefon.setText(null);
		 txtAdres.setText(null);
		 txtID.setText(null);
		 
	 }
	public FrmYoneticiPersonel() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				txtOtelID.setText(FrmYoneticiAnasayfa.otelID);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 558, 46);
		contentPane.add(panel);
		
		JLabel lblpersonel = new JLabel("PERSONEL \u0130\u015ELEMLER\u0130");
		lblpersonel.setForeground(Color.BLACK);
		lblpersonel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblpersonel.setBounds(170, 0, 250, 43);
		panel.add(lblpersonel);
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYoneticiAnasayfa frm = new FrmYoneticiAnasayfa();
				frm.setVisible(true);
				FrmYoneticiPersonel.this.setVisible(false);
			}
		});
		btngeri.setBounds(469, 11, 89, 33);
		panel.add(btngeri);
		
		Image img1 = new ImageIcon(this.getClass().getResource("/waiter.png")).getImage();
     	Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
     	lblpersonel.setIcon(new ImageIcon(img1));
     	btngeri.setIcon(new ImageIcon(img2));
     	
     	JLabel lblId = new JLabel("ID:");
     	lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblId.setBounds(13, 59, 68, 16);
     	contentPane.add(lblId);
     	
     	txtID = new JTextField();
     	txtID.setEnabled(false);
     	txtID.setColumns(10);
     	txtID.setBounds(116, 57, 96, 20);
     	contentPane.add(txtID);
     	
     	JLabel lblNewLabel = new JLabel("AD:");
     	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblNewLabel.setBounds(13, 90, 68, 16);
     	contentPane.add(lblNewLabel);
     	
     	txtAD = new JTextField();
     	txtAD.setColumns(10);
     	txtAD.setBounds(116, 84, 96, 20);
     	contentPane.add(txtAD);
     	
     	JLabel lblSoyad = new JLabel("SOYAD:");
     	lblSoyad.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblSoyad.setBounds(13, 120, 68, 16);
     	contentPane.add(lblSoyad);
     	
     	txtSoyad = new JTextField();
     	txtSoyad.setColumns(10);
     	txtSoyad.setBounds(116, 118, 96, 20);
     	contentPane.add(txtSoyad);
     	
     	JLabel lblTc = new JLabel("TC:");
     	lblTc.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblTc.setBounds(13, 150, 68, 16);
     	contentPane.add(lblTc);
     	
     	txtTC = new JTextField();
     	txtTC.setColumns(10);
     	txtTC.setBounds(116, 148, 96, 20);
     	contentPane.add(txtTC);
     	
     	JLabel lblTelefon = new JLabel("TELEFON:");
     	lblTelefon.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblTelefon.setBounds(10, 180, 104, 16);
     	contentPane.add(lblTelefon);
     	
     	txtTelefon = new JTextField();
     	txtTelefon.setColumns(10);
     	txtTelefon.setBounds(116, 178, 96, 20);
     	contentPane.add(txtTelefon);
     	
     	JLabel lblAdres = new JLabel("ADRES:");
     	lblAdres.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblAdres.setBounds(13, 210, 68, 16);
     	contentPane.add(lblAdres);
     	
     	txtAdres = new JTextArea();
     	txtAdres.setWrapStyleWord(true);
     	txtAdres.setLineWrap(true);
     	txtAdres.setBounds(116, 211, 100, 91);
     	contentPane.add(txtAdres);
     	
     	JLabel lblGrevi = new JLabel("G\u00D6REV\u0130:");
     	lblGrevi.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblGrevi.setBounds(330, 65, 68, 16);
     	contentPane.add(lblGrevi);
     	
     	txtGorev = new JTextField();
     	txtGorev.setColumns(10);
     	txtGorev.setBounds(433, 59, 96, 20);
     	contentPane.add(txtGorev);
     	
     	JLabel lblMaa = new JLabel("MAA\u015E:");
     	lblMaa.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblMaa.setBounds(330, 95, 68, 16);
     	contentPane.add(lblMaa);
     	
     	txtMaas = new JTextField();
     	txtMaas.setColumns(10);
     	txtMaas.setBounds(433, 93, 96, 20);
     	contentPane.add(txtMaas);
     	
     	JLabel lblKullancAd = new JLabel("KULLANICI ADI:");
     	lblKullancAd.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblKullancAd.setBounds(330, 125, 104, 16);
     	contentPane.add(lblKullancAd);
     	
     	txtkulad = new JTextField();
     	txtkulad.setColumns(10);
     	txtkulad.setBounds(433, 123, 96, 20);
     	contentPane.add(txtkulad);
     	
     	JLabel lblifre = new JLabel("\u015E\u0130FRE:");
     	lblifre.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblifre.setBounds(330, 152, 104, 16);
     	contentPane.add(lblifre);
     	
     	txtSifre = new JTextField();
     	txtSifre.setColumns(10);
     	txtSifre.setBounds(433, 150, 96, 20);
     	contentPane.add(txtSifre);
     	
     	JButton btnGuncelle = new JButton("G\u00FCncelle");
     	btnGuncelle.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			String query="UPDATE personel SET Adi=?,Soyadi=?,TC=?,Telefon=?,Adres=?,Görevi=?,Maas=?,KullaniciAdi=?,Sifre=?,OtelID=? where PersonelID=?";
				try {
					sorguifadesi=cnn.prepareStatement(query);
					sorguifadesi.setString(1,txtAD.getText());
					sorguifadesi.setString(2,txtSoyad.getText());
					sorguifadesi.setString(3,txtTC.getText());
					sorguifadesi.setString(4,txtTelefon.getText());
					sorguifadesi.setString(5,txtAdres.getText());
					sorguifadesi.setString(6,txtGorev.getText());
					sorguifadesi.setString(7,txtMaas.getText());
					sorguifadesi.setString(8,txtkulad.getText());
					sorguifadesi.setString(9,txtSifre.getText());
					sorguifadesi.setString(10,txtOtelID.getText());
					sorguifadesi.setString(11,txtID.getText());
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(sorguifadesi.executeUpdate()>0) {
						JOptionPane optionPane = new JOptionPane("Güncelleme Ýþlemi Baþarýlý",JOptionPane.NO_OPTION);
						JDialog dialog = optionPane.createDialog("Güncelleme");
						dialog.setAlwaysOnTop(true); // to show top of all other application
						dialog.setVisible(true); // to visible the dialog
						temizle();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
     		}
     	});
     	btnGuncelle.setBounds(233, 321, 89, 23);
     	contentPane.add(btnGuncelle);
     	
     	JButton btnSil = new JButton("Sil");
     	btnSil.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			int result = JOptionPane.showConfirmDialog(null,txtID.getText()+" "+"Numaralý Kayýtý Silmek Ýstediðinize eminmisiniz", "UYARI",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
           if(result==JOptionPane.NO_OPTION) {
          	 
           }
			 
			     
			      if(result==JOptionPane.YES_OPTION) {
			    	  try {
							cnn=Baglanti.baglan();
							String query="UPDATE personel SET Durumu='0' where PersonelID=?";
							sorguifadesi=cnn.prepareStatement(query);
							sorguifadesi.setString(1,txtID.getText());
							if(sorguifadesi.executeUpdate()>0) {
								JOptionPane optionPane = new JOptionPane("Silme Ýþlemi Baþarýlý",JOptionPane.WARNING_MESSAGE);
								JDialog dialog = optionPane.createDialog("Silme");
								dialog.setAlwaysOnTop(true); // to show top of all other application
								dialog.setVisible(true); // 
								temizle();
							}
						}
							catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			      }
     		}
     	});
     	btnSil.setBounds(345, 321, 89, 23);
     	contentPane.add(btnSil);
     	
     	JButton btnListele = new JButton("Listele");
     	
     	btnListele.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			sum=0;
     			Object []baslik= {"ID","AD","SOYAD","TC","TELEFON","ADRES","GÖREVÝ","MAAS","KULLANICI ADI","ÞÝFRE","OTELID"};
				Object [][]veri;
				String query="SELECT * FROM personel where OtelID='"+FrmYoneticiAnasayfa.otelID+"' and Durumu='1'";
				try {
					cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(query);
					getirilen=sorguifadesi.executeQuery();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int count=0;
				try {
					getirilen.last();
					count=getirilen.getRow();
					veri=new Object[count][11];
					getirilen.first();
					for(int i=0;i<count;i++) {
						for(int j=0;j<11;j++) {
							veri[i][j]=getirilen.getObject(j+1);
						  
						}
						  getirilen.next();
					}
					table.setModel(new DefaultTableModel(veri,baslik));
					getirilen.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int rowscount=table.getRowCount();
				for(int b=0;b<rowscount;b++) {
					sum=sum+Integer.parseInt(table.getValueAt(b,7).toString());
				}
			 String toplam=Integer.toString(sum);
			 lblpersonelmaastoplam.setText(toplam);
				}
     		
     	});
     	btnListele.setBounds(459, 321, 89, 23);
     	contentPane.add(btnListele);
     	
     	JButton btnEkle = new JButton("Ekle");
     	btnEkle.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			if(txtAD.getText().isEmpty()|| txtSoyad.getText().isEmpty() || txtTC.getText().isEmpty() || txtTelefon.getText().isEmpty() || txtAdres.getText().isEmpty() || txtGorev.getText().isEmpty()|| txtMaas.getText().isEmpty()|| txtkulad.getText().isEmpty()|| txtSifre.getText().isEmpty() || txtOtelID.getText().isEmpty())
				{
					JOptionPane optionPane = new JOptionPane("Lütfen Alanlarýn tümünü doldurunuz!",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("HATA");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); // to visible the dialog
				}
				else {
					
					cnn=Baglanti.baglan();
					String sorgu;
					sorgu="INSERT INTO `personel`(`Adi`,`Soyadi`,`TC`,`Telefon`,`Adres`,`Görevi`,`Maas`,`KullaniciAdi`,`Sifre`,`OtelID`) VALUES(?,?,?,?,?,?,?,?,?,?)"; 
                      try {
						sorguifadesi=cnn.prepareStatement(sorgu);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
                   try {
                	  
						sorguifadesi.setString(1,txtAD.getText());
						sorguifadesi.setString(2,txtSoyad.getText());
		                sorguifadesi.setString(3,txtTC.getText());
		                sorguifadesi.setString(4,txtTelefon.getText());
		                sorguifadesi.setString(5,txtAdres.getText());
		                sorguifadesi.setString(6,txtGorev.getText());
		                sorguifadesi.setString(7,txtMaas.getText());
		                sorguifadesi.setString(8,txtkulad.getText());
		                sorguifadesi.setString(9,txtSifre.getText());
		                sorguifadesi.setString(10,txtOtelID.getText());
		            
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                   try {
					int row=sorguifadesi.executeUpdate();
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
     	btnEkle.setBounds(116, 321, 89, 23);
     	contentPane.add(btnEkle);
     	
     	JScrollPane scrollPane = new JScrollPane();
     	scrollPane.setBounds(0, 355, 558, 177);
     	contentPane.add(scrollPane);
     	
     	table = new JTable();
     	table.addMouseListener(new MouseAdapter() {
     		@Override
     		public void mouseClicked(MouseEvent e) {
     			int i=table.getSelectedRow();
				txtID.setText(table.getValueAt(i, 0).toString());
				txtAD.setText(table.getValueAt(i, 1).toString());
				txtSoyad.setText(table.getValueAt(i, 2).toString());
				txtTC.setText(table.getValueAt(i, 3).toString());
				txtTelefon.setText(table.getValueAt(i, 4).toString());
				txtAdres.setText(table.getValueAt(i, 5).toString());
				txtGorev.setText(table.getValueAt(i, 6).toString());
				txtMaas.setText(table.getValueAt(i, 7).toString());
				txtkulad.setText(table.getValueAt(i, 8).toString());
				txtSifre.setText(table.getValueAt(i, 9).toString());
				txtOtelID.setText(table.getValueAt(i, 10).toString());
				
				
     		}
     	});
     	scrollPane.setViewportView(table);
     	table.setSelectionBackground(Color.GREEN);
     	
     	JLabel lblOteld = new JLabel("OTELID:");
     	lblOteld.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	lblOteld.setBounds(330, 178, 68, 16);
     	contentPane.add(lblOteld);
     	
     	txtOtelID = new JTextField();
     	txtOtelID.setEnabled(false);
     	txtOtelID.setColumns(10);
     	txtOtelID.setBounds(433, 176, 96, 20);
     	contentPane.add(txtOtelID);
     	
     	lblpersonelmaas = new JLabel("Personel Maa\u015Flar\u0131:");
     	lblpersonelmaas.setFont(new Font("Times New Roman", Font.BOLD, 14));
     	lblpersonelmaas.setBounds(316, 253, 120, 31);
     	contentPane.add(lblpersonelmaas);
     	
     	lblpersonelmaastoplam = new JLabel("");
     	lblpersonelmaastoplam.setFont(new Font("Times New Roman", Font.BOLD, 14));
     	lblpersonelmaastoplam.setBounds(433, 254, 120, 31);
     	contentPane.add(lblpersonelmaastoplam);
	}
}
