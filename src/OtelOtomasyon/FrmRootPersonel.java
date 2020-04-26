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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;

public class FrmRootPersonel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtTelefon;
	private JTextField txtTC;
	private JTextField txtSoyad;
	private JTextField txtAD;
	private JTextField txtID;
	private JTextField txtGorevi;
	private JTextField txtMaas;
	private JTextField txtkulad;
	private JTextField txtSifre;
	private JTextArea txtAdres;
    private JComboBox cmbotel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRootPersonel frame = new FrmRootPersonel();
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
		txtAD.setText(null);
		txtAdres.setText(null);
		txtGorevi.setText(null);
		txtID.setText(null);
		txtkulad.setText(null);
		txtMaas.setText(null);
		txtSifre.setText(null);
		txtSoyad.setText(null);
		txtTC.setText(null);
		txtTelefon.setText(null);
		cmbotel.setSelectedIndex(0);
	}
	Connection cnn=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	 String otelid;
	public FrmRootPersonel() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				String sql="Select OtelAdi from otel where Durumu='1' ";
				try {
					cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(sql);
					getirilen=sorguifadesi.executeQuery();
					while(getirilen.next()) {
						cmbotel.addItem(getirilen.getString("OtelAdi"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cmbotel = new JComboBox();
		cmbotel.setModel(new DefaultComboBoxModel(new String[] {"Otel Ad\u0131n\u0131 se\u00E7iniz"}));
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(205, 92, 92));
		panel.setBounds(0, 0, 659, 46);
		contentPane.add(panel);
		cmbotel.setBounds(423, 166, 137, 21);
		contentPane.add(cmbotel);
		JLabel lblpersonel = new JLabel("PERSONEL \u0130\u015ELEMLER\u0130");
		lblpersonel.setForeground(Color.BLACK);
		lblpersonel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblpersonel.setBounds(170, 0, 250, 43);
		panel.add(lblpersonel);
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRootAnasayfa frm = new FrmRootAnasayfa();
				frm.setVisible(true);
				FrmRootPersonel.this.setVisible(false);
			}
		});
		
		btngeri.setBounds(570, 11, 89, 35);
		panel.add(btngeri);
		Image img4 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btngeri.setIcon(new ImageIcon(img4));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 302, 659, 135);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionBackground(Color.GREEN);
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
				txtGorevi.setText(table.getValueAt(i, 6).toString());
				txtMaas.setText(table.getValueAt(i, 7).toString());
				txtkulad.setText(table.getValueAt(i, 8).toString());
				txtSifre.setText(table.getValueAt(i, 9).toString());
				cmbotel.setSelectedItem(table.getValueAt(i, 10).toString());
			}
		});
		
		cmbotel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String sql="Select OtelID from otel WHERE OtelAdi='"+cmbotel.getSelectedItem()+"' AND durumu='1' ";
				
				try {
					cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(sql);
					getirilen=sorguifadesi.executeQuery();
				
					if(getirilen.next()) {
						
					otelid=getirilen.getString("OtelID");
					
					
						
				
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		scrollPane.setViewportView(table);
		Image img5 = new ImageIcon(this.getClass().getResource("/waiter.png")).getImage();
		lblpersonel.setIcon(new ImageIcon(img5));
		
		JLabel lblAdres = new JLabel("ADRES:");
		lblAdres.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblAdres.setBounds(3, 199, 68, 16);
		contentPane.add(lblAdres);
		
		txtTelefon = new JTextField();
		txtTelefon.setColumns(10);
		txtTelefon.setBounds(106, 167, 96, 20);
		contentPane.add(txtTelefon);
		
		JLabel lblTelefon = new JLabel("TELEFON:");
		lblTelefon.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTelefon.setBounds(0, 169, 104, 16);
		contentPane.add(lblTelefon);
		
		JLabel lblTc = new JLabel("TC:");
		lblTc.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTc.setBounds(3, 139, 68, 16);
		contentPane.add(lblTc);
		
		txtTC = new JTextField();
		txtTC.setColumns(10);
		txtTC.setBounds(106, 137, 96, 20);
		contentPane.add(txtTC);
		
		txtSoyad = new JTextField();
		txtSoyad.setColumns(10);
		txtSoyad.setBounds(106, 107, 96, 20);
		contentPane.add(txtSoyad);
		
		JLabel lblSoyad = new JLabel("SOYAD:");
		lblSoyad.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSoyad.setBounds(3, 109, 68, 16);
		contentPane.add(lblSoyad);
		
		JLabel lblNewLabel = new JLabel("AD:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(3, 79, 68, 16);
		contentPane.add(lblNewLabel);
		
		txtAD = new JTextField();
		txtAD.setColumns(10);
		txtAD.setBounds(106, 73, 96, 20);
		contentPane.add(txtAD);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblId.setBounds(3, 48, 68, 16);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(106, 46, 96, 20);
		contentPane.add(txtID);
		
		txtAdres = new JTextArea();
		txtAdres.setWrapStyleWord(true);
		txtAdres.setLineWrap(true);
		txtAdres.setBounds(106, 200, 100, 91);
		contentPane.add(txtAdres);
		
		JLabel lblGrevi = new JLabel("G\u00D6REV\u0130:");
		lblGrevi.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblGrevi.setBounds(320, 54, 68, 16);
		contentPane.add(lblGrevi);
		
		txtGorevi = new JTextField();
		txtGorevi.setColumns(10);
		txtGorevi.setBounds(423, 48, 96, 20);
		contentPane.add(txtGorevi);
		
		JLabel lblMaa = new JLabel("MAA\u015E:");
		lblMaa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblMaa.setBounds(320, 84, 68, 16);
		contentPane.add(lblMaa);
		
		txtMaas = new JTextField();
		txtMaas.setColumns(10);
		txtMaas.setBounds(423, 82, 96, 20);
		contentPane.add(txtMaas);
		
		JLabel lblKullancAd = new JLabel("KULLANICI ADI:");
		lblKullancAd.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblKullancAd.setBounds(320, 114, 104, 16);
		contentPane.add(lblKullancAd);
		
		txtkulad = new JTextField();
		txtkulad.setColumns(10);
		txtkulad.setBounds(423, 112, 96, 20);
		contentPane.add(txtkulad);
		
		JLabel lblifre = new JLabel("\u015E\u0130FRE:");
		lblifre.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblifre.setBounds(320, 141, 104, 16);
		contentPane.add(lblifre);
		
		txtSifre = new JTextField();
		txtSifre.setColumns(10);
		txtSifre.setBounds(423, 139, 96, 20);
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
					sorguifadesi.setString(6,txtGorevi.getText());
					sorguifadesi.setString(7,txtMaas.getText());
					sorguifadesi.setString(8,txtkulad.getText());
					sorguifadesi.setString(9,txtSifre.getText());
					sorguifadesi.setString(10,otelid);
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
		btnGuncelle.setBounds(256, 272, 89, 23);
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
		btnSil.setBounds(367, 272, 89, 23);
		contentPane.add(btnSil);
		
		JButton btnListele = new JButton("Listele");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object []baslik= {"ID","AD","SOYAD","TC","TELEFON","ADRES","GÖREVÝ","MAAS","KULLANICI ADI","ÞÝFRE","OTEL ADI"};
				Object [][]veri;
				String query="SELECT `PersonelID`,`Adi`,`Soyadi`,`TC`,`Telefon`,`Adres`,`Görevi`,`Maas`,`KullaniciAdi`,`Sifre`,`OtelAdi`\r\n" + 
						"FROM personel AS a INNER JOIN otel AS b ON a.OtelID=b.OtelID WHERE a.Durumu='1' ORDER BY PersonelID ASC";
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
				
			}
			
		});
		btnListele.setBounds(471, 272, 89, 23);
		contentPane.add(btnListele);
		
		JLabel lblOtelAd = new JLabel("OTEL ADI:");
		lblOtelAd.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblOtelAd.setBounds(320, 170, 104, 16);
		contentPane.add(lblOtelAd);
		
	
	
	}
	
}
