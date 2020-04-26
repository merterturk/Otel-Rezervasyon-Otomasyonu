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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmYoneticiOda extends JFrame {

	private JPanel contentPane;
	private JTextField txtOtelID;
	public static JTextField []txtalan;
	public static JLabel[] lblalan;
    static int odasayi=0;
    private JTextField txtodano;
    private JTextField txtodafiyat;
    private JTable table;
    private JTextArea txtaciklama;
    private JComboBox cmbodatur;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmYoneticiOda frame = new FrmYoneticiOda();
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
		txtodano.setText(null);
		txtodafiyat.setText(null);
		txtaciklama.setText(null);
		txtID.setText(null);
		
	}
	Connection cnn=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	private JTextField txtID;
	public FrmYoneticiOda() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				txtOtelID.setText(FrmYoneticiAnasayfa.otelID);
			}
			
		});
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 502, 46);
		contentPane.add(panel);
		
		JLabel lbloda = new JLabel("ODA \u0130\u015ELEMLER\u0130");
		lbloda.setForeground(Color.BLACK);
		lbloda.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lbloda.setBounds(170, 0, 250, 43);
		panel.add(lbloda);
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYoneticiAnasayfa frm = new FrmYoneticiAnasayfa();
				frm.setVisible(true);
				FrmYoneticiOda.this.setVisible(false);
			}
		});
		btngeri.setBounds(409, 10, 89, 33);
		panel.add(btngeri);
		Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btngeri.setIcon(new ImageIcon(img2));
		Image img3 = new ImageIcon(this.getClass().getResource("/room.png")).getImage();
		lbloda.setIcon(new ImageIcon(img3));
		
	
		
		JLabel lblOtelId = new JLabel("OTEL ID:");
		lblOtelId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOtelId.setBounds(0, 87, 104, 32);
		contentPane.add(lblOtelId);
		
		txtOtelID = new JTextField();
		txtOtelID.setEnabled(false);
		txtOtelID.setColumns(10);
		txtOtelID.setBounds(86, 93, 96, 20);
		contentPane.add(txtOtelID);
		
		JLabel lblodano1 = new JLabel("ODA NO:");
		lblodano1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblodano1.setBounds(0, 124, 104, 32);
		contentPane.add(lblodano1);
		
		txtodano = new JTextField();
		txtodano.setColumns(10);
		txtodano.setBounds(86, 130, 96, 20);
		contentPane.add(txtodano);
		
		JLabel lblodatur1 = new JLabel("ODA T\u00DCR\u00DC:");
		lblodatur1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblodatur1.setBounds(0, 167, 104, 32);
		contentPane.add(lblodatur1);
		
		cmbodatur = new JComboBox();
		cmbodatur.setModel(new DefaultComboBoxModel(new String[] {"Oda Tipini Se\u00E7iniz", "Tek Ki\u015Filik Oda", "Standart iki yatakl\u0131 Oda", "\u0130ki ki\u015Filik oda", "Suit Oda", "Kraliyet Odas\u0131"}));
		cmbodatur.setBounds(86, 172, 104, 22);
		contentPane.add(cmbodatur);
		
		JLabel lblodafiyat1 = new JLabel("ODA F\u0130YATI:");
		lblodafiyat1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblodafiyat1.setBounds(0, 215, 104, 32);
		contentPane.add(lblodafiyat1);
		
		txtodafiyat = new JTextField();
		txtodafiyat.setColumns(10);
		txtodafiyat.setBounds(86, 221, 96, 20);
		contentPane.add(txtodafiyat);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtOtelID.getText().isEmpty()|| txtodano.getText().isEmpty()||txtodafiyat.getText().isEmpty()) {
					JOptionPane optionPane = new JOptionPane("Alanlarý Boþ Býrakmayýnýz.",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("Hata");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); // 
				}
				else {
					cnn=Baglanti.baglan();
					String sorgu="INSERT INTO `ODA`(`OTELID`,`ODANO`,`ODATURU`,`FIYAT`,`ACÝKLAMA`) VALUES(?,?,?,?,?)";
					try {
					 	sorguifadesi=cnn.prepareStatement(sorgu);
							sorguifadesi.setString(1,txtOtelID.getText());
			                sorguifadesi.setString(2,txtodano.getText());
			                sorguifadesi.setString(3,(String) cmbodatur.getSelectedItem());
			                sorguifadesi.setInt(4,Integer.parseInt(txtodafiyat.getText()));
			                sorguifadesi.setString(5, txtaciklama.getText());
			                
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
		btnEkle.setBounds(0, 258, 96, 32);
		contentPane.add(btnEkle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 301, 502, 145);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				txtID.setText(table.getValueAt(i, 0).toString());
				txtOtelID.setText(table.getValueAt(i, 1).toString());
				txtodano.setText(table.getValueAt(i, 2).toString());
				cmbodatur.setSelectedItem(table.getValueAt(i, 3).toString());
				txtodafiyat.setText(table.getValueAt(i, 4).toString());
				txtaciklama.setText(table.getValueAt(i, 5).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
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
							String query="UPDATE oda SET Durumu='0' where ID=?";
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
		btnSil.setBounds(133, 258, 96, 32);
		contentPane.add(btnSil);
		table.setSelectionBackground(Color.GREEN);
		JButton btnGuncelle = new JButton("G\u00FCncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="UPDATE oda SET ODANO=?,ODATURU=?,FIYAT=?,ACÝKLAMA=? WHERE ID=?";
				try {
					sorguifadesi=cnn.prepareStatement(query);
					sorguifadesi.setString(1,txtodano.getText());
					sorguifadesi.setString(2,(String) cmbodatur.getSelectedItem());
					sorguifadesi.setString(3,txtodafiyat.getText());
					sorguifadesi.setString(4,txtaciklama.getText());
					sorguifadesi.setString(5,txtID.getText());
					
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
		btnGuncelle.setBounds(266, 258, 96, 32);
		contentPane.add(btnGuncelle);
		JButton btnListele = new JButton("Listele");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object []baslik= {"ID","OTELID","ODANO","ODATÜRÜ","FÝYAT","AÇIKLAMA"};
				Object [][]veri;
				String query="SELECT *FROM oda where OTELID='"+txtOtelID.getText()+"' and Durumu='1'";
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
		btnListele.setBounds(406, 258, 96, 32);
		contentPane.add(btnListele);
		
		JLabel lblAklama = new JLabel("A\u00C7IKLAMA:");
		lblAklama.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblAklama.setBounds(230, 57, 104, 32);
		contentPane.add(lblAklama);
		
		txtaciklama = new JTextArea();
		txtaciklama.setLineWrap(true);
		txtaciklama.setBounds(319, 66, 173, 76);
		contentPane.add(txtaciklama);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblId.setBounds(0, 46, 104, 32);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(86, 52, 96, 20);
		contentPane.add(txtID);
		
		
	    
	}
}
