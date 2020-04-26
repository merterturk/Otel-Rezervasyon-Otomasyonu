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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmRootMüþteri extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JTextField txtID;
	private static JTextField txtAD;
	private static JTextField txtSoyad;
	private static JTextField txtTC;
	private static JTextField txtTelefon;
	private static JTextField txtKulad;
	private static JTextField txtsifre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRootMüþteri frame = new FrmRootMüþteri();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static void temizle() {
		txtID.setText(null);
		txtAD.setText(null);
		txtSoyad.setText(null);
		txtTC.setText(null);
		txtTelefon.setText(null);
		txtKulad.setText(null);
		txtsifre.setText(null);
	}

	/**
	 * Create the frame.
	 */
	Connection cnn=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	public FrmRootMüþteri() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 567, 46);
		contentPane.add(panel);
		
		JLabel lblmusteri = new JLabel("M\u00DC\u015ETER\u0130 \u0130\u015ELEMLER\u0130");
		lblmusteri.setForeground(Color.BLACK);
		lblmusteri.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblmusteri.setBounds(170, 0, 250, 43);
		panel.add(lblmusteri);
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRootAnasayfa frm = new FrmRootAnasayfa();
				frm.setVisible(true);
				FrmRootMüþteri.this.setVisible(false);
			}
		});
		btngeri.setBounds(478, 13, 89, 33);
		panel.add(btngeri);
		Image img4 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btngeri.setIcon(new ImageIcon(img4));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 293, 567, 139);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionBackground(Color.GREEN);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				txtAD.setText(table.getValueAt(i, 1).toString());
				txtSoyad.setText(table.getValueAt(i, 2).toString());
				txtID.setText(table.getValueAt(i, 0).toString());
				txtTC.setText(table.getValueAt(i, 3).toString());
				txtTelefon.setText(table.getValueAt(i, 4).toString());
				txtKulad.setText(table.getValueAt(i, 5).toString());
				txtsifre.setText(table.getValueAt(i, 6).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnekle = new JButton("Listele");
		btnekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object []baslik= {"ID","AD","SOYAD","TC","TELEFON","KULLANÝCÝADÝ","ÞÝFRE"};
				Object [][]veri;
				String query="Select * from musteri where Durumu='1'";
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
					veri=new Object[count][7];
					getirilen.first();
					for(int i=0;i<count;i++) {
						for(int j=0;j<7;j++) {
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
		btnekle.setBounds(289, 271, 89, 23);
		contentPane.add(btnekle);
		
		JButton btnsil = new JButton("Sil");
		btnsil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null,txtID.getText()+" "+"Numaralý Kayýtý Silmek Ýstediðinize Eminmisiniz", "UYARI",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
              if(result==JOptionPane.NO_OPTION) {
             	 
              }
			 
			     
			      if(result==JOptionPane.YES_OPTION) {
			    	  try {
							cnn=Baglanti.baglan();
							String query="UPDATE musteri SET Durumu='0' where MusteriID=?";
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
					});
		btnsil.setBounds(0, 271, 89, 23);
		contentPane.add(btnsil);
		
		JButton btnguncelle = new JButton("G\u00FCncelle");
		btnguncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="UPDATE musteri SET MusteriAD=?,MusteriSoyad=?,MusteriTC=?,MusteriTel=?,MusteriKad=?,Musterisifre=? where MusteriID=?";
				try {
					sorguifadesi=cnn.prepareStatement(query);
					sorguifadesi.setString(1,txtAD.getText());
					sorguifadesi.setString(2,txtSoyad.getText());
					sorguifadesi.setString(3,txtTC.getText());
					sorguifadesi.setString(4,txtTelefon.getText());
					sorguifadesi.setString(5,txtKulad.getText());
					sorguifadesi.setString(6,txtsifre.getText());
					sorguifadesi.setString(7,txtID.getText());
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
		btnguncelle.setBounds(116, 271, 89, 23);
		contentPane.add(btnguncelle);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblId.setBounds(3, 50, 68, 16);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(116, 48, 96, 20);
		contentPane.add(txtID);
		
		JLabel lblNewLabel = new JLabel("AD:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(3, 81, 68, 16);
		contentPane.add(lblNewLabel);
		
		txtAD = new JTextField();
		txtAD.setColumns(10);
		txtAD.setBounds(116, 79, 96, 20);
		contentPane.add(txtAD);
		
		JLabel lblSoyad = new JLabel("SOYAD:");
		lblSoyad.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSoyad.setBounds(3, 111, 68, 16);
		contentPane.add(lblSoyad);
		
		txtSoyad = new JTextField();
		txtSoyad.setColumns(10);
		txtSoyad.setBounds(116, 106, 96, 20);
		contentPane.add(txtSoyad);
		
		JLabel lblTc = new JLabel("TC:");
		lblTc.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTc.setBounds(3, 141, 68, 16);
		contentPane.add(lblTc);
		
		txtTC = new JTextField();
		txtTC.setColumns(10);
		txtTC.setBounds(116, 139, 96, 20);
		contentPane.add(txtTC);
		
		JLabel lblTelefon = new JLabel("TELEFON");
		lblTelefon.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTelefon.setBounds(0, 171, 104, 16);
		contentPane.add(lblTelefon);
		
		txtTelefon = new JTextField();
		txtTelefon.setColumns(10);
		txtTelefon.setBounds(116, 170, 96, 20);
		contentPane.add(txtTelefon);
		
		JLabel lblKullancAd = new JLabel("KULLANICI ADI:");
		lblKullancAd.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblKullancAd.setBounds(3, 201, 120, 16);
		contentPane.add(lblKullancAd);
		
		txtKulad = new JTextField();
		txtKulad.setColumns(10);
		txtKulad.setBounds(116, 200, 96, 20);
		contentPane.add(txtKulad);
		
		JLabel lblifre = new JLabel("\u015E\u0130FRE:");
		lblifre.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblifre.setBounds(3, 228, 120, 16);
		contentPane.add(lblifre);
		
		txtsifre = new JTextField();
		txtsifre.setColumns(10);
		txtsifre.setBounds(116, 228, 96, 20);
		contentPane.add(txtsifre);
		
		Image img5 = new ImageIcon(this.getClass().getResource("/staff.png")).getImage();
		lblmusteri.setIcon(new ImageIcon(img5));
		
	}
}
