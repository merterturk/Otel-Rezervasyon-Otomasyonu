package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmRootYönetici extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JTextField txtad;
	private static JTextField txtsoyad;
	private static JTextField txttelefon;
	private static JTextField txtkulad;
	private static JTextField txtsifre;
	private static JTextField txtID;
	static void temizle() {
		txtID.setText(null);
		txtad.setText(null);
		txtkulad.setText(null);
		txtsifre.setText(null);
		txtsoyad.setText(null);
		txttelefon.setText(null);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRootYönetici frame = new FrmRootYönetici();
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
	public FrmRootYönetici() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(0, 0, 621, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblyonetici = new JLabel("Y\u00D6NET\u0130C\u0130 \u0130\u015ELEMLER\u0130");
		lblyonetici.setForeground(new Color(0, 0, 0));
		lblyonetici.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblyonetici.setBounds(170, 0, 250, 43);
		panel.add(lblyonetici);
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRootAnasayfa frm = new FrmRootAnasayfa();
				frm.setVisible(true);
				FrmRootYönetici.this.setVisible(false);
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
     	btngeri.setIcon(new ImageIcon(img4));
		btngeri.setBounds(532, 13, 89, 33);
		panel.add(btngeri);
	
		JScrollPane scrollPane = new JScrollPane();
		
		
		
		scrollPane.setBounds(-2, 267, 623, 164);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setSelectionBackground(Color.GREEN);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				txtad.setText(table.getValueAt(i, 1).toString());
				txtsoyad.setText(table.getValueAt(i, 2).toString());
				txtID.setText(table.getValueAt(i, 0).toString());
				txttelefon.setText(table.getValueAt(i, 3).toString());
				txtkulad.setText(table.getValueAt(i, 4).toString());
				txtsifre.setText(table.getValueAt(i, 5).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		table.setShowHorizontalLines(false);
		
		JLabel lblNewLabel = new JLabel("AD:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 84, 68, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblSoyad = new JLabel("SOYAD:");
		lblSoyad.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSoyad.setBounds(10, 114, 68, 16);
		contentPane.add(lblSoyad);
		
		JLabel lblTelefon = new JLabel("TELEFON:");
		lblTelefon.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTelefon.setBounds(10, 144, 68, 16);
		contentPane.add(lblTelefon);
		
		JLabel lblKullancAd = new JLabel("KULLANICI ADI:");
		lblKullancAd.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblKullancAd.setBounds(7, 174, 104, 16);
		contentPane.add(lblKullancAd);
		
		JLabel lblSifre = new JLabel("\u015E\u0130FRE:");
		lblSifre.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSifre.setBounds(10, 204, 68, 16);
		contentPane.add(lblSifre);
		
		txtad = new JTextField();
		txtad.setBounds(113, 78, 96, 20);
		contentPane.add(txtad);
		txtad.setColumns(10);
		
		txtsoyad = new JTextField();
		txtsoyad.setColumns(10);
		txtsoyad.setBounds(113, 112, 96, 20);
		contentPane.add(txtsoyad);
		
		txttelefon = new JTextField();
		txttelefon.setColumns(10);
		txttelefon.setBounds(113, 142, 96, 20);
		contentPane.add(txttelefon);
		
		txtkulad = new JTextField();
		txtkulad.setColumns(10);
		txtkulad.setBounds(113, 172, 96, 20);
		contentPane.add(txtkulad);
		
		txtsifre = new JTextField();
		txtsifre.setColumns(10);
		txtsifre.setBounds(113, 202, 96, 20);
		contentPane.add(txtsifre);
		
		JButton btnListele = new JButton("Listele");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object []baslik= {"ID","AD","SOYAD","TELEFON","KULLANÝCÝADÝ","ÞÝFRE"};
				Object [][]veri;
				String query="Select * from yönetici where Durumu='1'";
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
					veri=new Object[count][6];
					getirilen.first();
					for(int i=0;i<count;i++) {
						for(int j=0;j<6;j++) {
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
		btnListele.setBounds(219, 233, 89, 23);
		contentPane.add(btnListele);
		
		JButton btnGuncelle = new JButton("G\u00FCncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String query="UPDATE yönetici SET AD=?,Soyad=?,Telefon=?,KullaniciAdi=?,Sifre=? where ID=?";
			try {
				sorguifadesi=cnn.prepareStatement(query);
				sorguifadesi.setString(1,txtad.getText());
				sorguifadesi.setString(2,txtsoyad.getText());
				sorguifadesi.setString(3,txttelefon.getText());
				sorguifadesi.setString(4,txtkulad.getText());
				sorguifadesi.setString(5,txtsifre.getText());
				sorguifadesi.setString(6,txtID.getText());
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
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 temizle();
			}
		});
		btnGuncelle.setBounds(113, 233, 96, 23);
		contentPane.add(btnGuncelle);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblId.setBounds(10, 53, 68, 16);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(113, 51, 96, 20);
		contentPane.add(txtID);
		
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
							String query="UPDATE yönetici SET Durumu='0' where ID=?";
							sorguifadesi=cnn.prepareStatement(query);
							sorguifadesi.setString(1,txtID.getText());
							if(sorguifadesi.executeUpdate()>0) {
								JOptionPane optionPane = new JOptionPane("Silme Ýþlemi Baþarýlý",JOptionPane.WARNING_MESSAGE);
								JDialog dialog = optionPane.createDialog("Silme");
								dialog.setAlwaysOnTop(true); // to show top of all other application
								dialog.setVisible(true); // 
							}
						}
							catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			      }
				
			    
			}
		}
		);
		btnSil.setBounds(318, 233, 89, 23);
		contentPane.add(btnSil);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtad.getText().isEmpty()|| txtsoyad.getText().isEmpty()||txttelefon.getText().isEmpty()|| txtkulad.getText().isEmpty()||txtsifre.getText().isEmpty()) {
					JOptionPane optionPane = new JOptionPane("Alanlarý Boþ Býrakmayýnýz.",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("Hata");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); // 
				}
				else {
				cnn=Baglanti.baglan();
			String sorgu="INSERT INTO `yönetici`(`AD`,`Soyad`,`Telefon`,`KullaniciAdi`,`Sifre`) VALUES(?,?,?,?,?)";
			try {
		 	sorguifadesi=cnn.prepareStatement(sorgu);
				sorguifadesi.setString(1,txtad.getText());
				sorguifadesi.setString(2,txtsoyad.getText());
                sorguifadesi.setString(3,txttelefon.getText());
                sorguifadesi.setString(4,txtkulad.getText());
                sorguifadesi.setString(5,txtsifre.getText());
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
		btnEkle.setBounds(0, 233, 96, 23);
		contentPane.add(btnEkle);
		Image img5 = new ImageIcon(this.getClass().getResource("/man.png")).getImage();
		lblyonetici.setIcon(new ImageIcon(img5));
		}
	private void createUI(JFrame frame) {
		// TODO Auto-generated method stub
		
	}
	}
	


