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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmRootOtel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtID;
	private JTextField txtOtelAD;
	private JTextField txttelefon;
	private JTextField txtSehir;
	private JComboBox cmbyonetici;
	private JTextArea txtAdres;
	private JComboBox cmbyildiz;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRootOtel frame = new FrmRootOtel();
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
	static String yöneticiID;
     	void temizle() {
txtID.setText(null);
txtOtelAD.setText(null);
txttelefon.setText(null);
txtSehir.setText(null);
txtAdres.setText(null);
cmbyonetici.setSelectedIndex(1);
cmbyildiz.setSelectedIndex(1);
}
	public FrmRootOtel() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String sql="Select AD,Soyad from yönetici where Durumu='1' ";
				try {
					cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(sql);
					getirilen=sorguifadesi.executeQuery();
					String adsoyad;
					while(getirilen.next()) {
						adsoyad=getirilen.getString("AD")+" "+getirilen.getString("Soyad");
						cmbyonetici.addItem(adsoyad);
						
						//cmbotel.addItem(getirilen.getString("OtelAdi"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 250, 154));
		panel.setBounds(0, 0, 577, 46);
		contentPane.add(panel);
		
		JLabel lblotel = new JLabel("OTEL \u0130\u015ELEMLER\u0130");
		lblotel.setForeground(Color.BLACK);
		lblotel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblotel.setBounds(170, 0, 250, 43);
		panel.add(lblotel);
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRootAnasayfa frm = new FrmRootAnasayfa();
				frm.setVisible(true);
				FrmRootOtel.this.setVisible(false);
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btngeri.setIcon(new ImageIcon(img4));
		btngeri.setBounds(488, 13, 89, 33);
		panel.add(btngeri);
		Image img5 = new ImageIcon(this.getClass().getResource("/hotel.png")).getImage();
		lblotel.setIcon(new ImageIcon(img5));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 307, 579, 126);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				txtOtelAD.setText(table.getValueAt(i, 1).toString());
				txtSehir.setText(table.getValueAt(i, 2).toString());
				txtID.setText(table.getValueAt(i, 0).toString());
				txtAdres.setText(table.getValueAt(i, 3).toString());
				txttelefon.setText(table.getValueAt(i, 4).toString());
				cmbyonetici.setSelectedItem(table.getValueAt(i, 5).toString());
				cmbyildiz.setSelectedItem(table.getValueAt(i, 6).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblId.setBounds(13, 59, 68, 16);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(116, 57, 96, 20);
		contentPane.add(txtID);
		
		JLabel lblOtelAd = new JLabel("OTEL ADI:");
		lblOtelAd.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblOtelAd.setBounds(13, 90, 91, 16);
		contentPane.add(lblOtelAd);
		
		txtOtelAD = new JTextField();
		txtOtelAD.setColumns(10);
		txtOtelAD.setBounds(116, 84, 96, 20);
		contentPane.add(txtOtelAD);
		
		JLabel lblAdres = new JLabel("ADRES:");
		lblAdres.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblAdres.setBounds(13, 149, 68, 16);
		contentPane.add(lblAdres);
		
		txtAdres = new JTextArea();
		txtAdres.setWrapStyleWord(true);
		txtAdres.setLineWrap(true);
		txtAdres.setBounds(112, 150, 100, 91);
		contentPane.add(txtAdres);
		
		JLabel lblTelefon = new JLabel("TELEFON:");
		lblTelefon.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTelefon.setBounds(10, 254, 104, 16);
		contentPane.add(lblTelefon);
		
		txttelefon = new JTextField();
		txttelefon.setColumns(10);
		txttelefon.setBounds(116, 252, 96, 20);
		contentPane.add(txttelefon);
		
		JLabel lblYnetici = new JLabel("Y\u00D6NET\u0130C\u0130:");
		lblYnetici.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblYnetici.setBounds(293, 57, 104, 16);
		contentPane.add(lblYnetici);
		
		 cmbyonetici = new JComboBox();
		 cmbyonetici.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
             String sql="SELECT ID FROM yönetici where CONCAT(AD,' ',Soyad)='"+cmbyonetici.getSelectedItem()+"' ";
				
				try {
					cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(sql);
					getirilen=sorguifadesi.executeQuery();
				
					if(getirilen.next()) {
					yöneticiID=getirilen.getString("ID");
				
					
						
					
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		 	
		 });
		 cmbyonetici.setModel(new DefaultComboBoxModel(new String[] {"Y\u00F6netici Se\u00E7iniz"}));
		cmbyonetici.setBounds(373, 54, 123, 22);
		contentPane.add(cmbyonetici);
		
		JLabel lblehir = new JLabel("\u015EEH\u0130R:");
		lblehir.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblehir.setBounds(13, 122, 68, 16);
		contentPane.add(lblehir);
		
		txtSehir = new JTextField();
		txtSehir.setColumns(10);
		txtSehir.setBounds(116, 116, 96, 20);
		contentPane.add(txtSehir);
		
		 cmbyildiz = new JComboBox();
		cmbyildiz.setModel(new DefaultComboBoxModel(new String[] {"Yildiz Sayisi Se\u00E7iniz", "1", "2", "3", "4", "5"}));
		cmbyildiz.setBounds(373, 103, 123, 22);
		contentPane.add(cmbyildiz);
		
		JLabel lblYldz = new JLabel("YILDIZ:");
		lblYldz.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblYldz.setBounds(293, 107, 104, 16);
		contentPane.add(lblYldz);
		table.setSelectionBackground(Color.GREEN);
		JButton btnEkle = new JButton("EKLE");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtOtelAD.getText().isEmpty()|| txtSehir.getText().isEmpty()||txtAdres.getText().isEmpty()|| txttelefon.getText().isEmpty()) {
					JOptionPane optionPane = new JOptionPane("Alanlarý Boþ Býrakmayýnýz.",JOptionPane.WARNING_MESSAGE);
					JDialog dialog = optionPane.createDialog("Hata");
					dialog.setAlwaysOnTop(true); // to show top of all other application
					dialog.setVisible(true); // 
				}
				else {
					cnn=Baglanti.baglan();
					String sorgu="INSERT INTO `otel`(`OtelAdi`,`Sehir`,`OtelAdres`,`OtelTelefon`,`YöneticiID`,`YildizSayisi`) VALUES(?,?,?,?,?,?)";
					try {
					 	sorguifadesi=cnn.prepareStatement(sorgu);
							sorguifadesi.setString(1,txtOtelAD.getText());
							sorguifadesi.setString(2,txtSehir.getText());
			                sorguifadesi.setString(3,txtAdres.getText());
			                sorguifadesi.setString(4,txttelefon.getText());
			                sorguifadesi.setString(5,yöneticiID);
			                sorguifadesi.setInt(6,cmbyildiz.getSelectedIndex());
			               
			                
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
		btnEkle.setBounds(116, 281, 89, 23);
		contentPane.add(btnEkle);
		
		JButton btnGuncelle = new JButton("G\u00DCNCELLE");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="UPDATE otel SET OtelAdi=?,Sehir=?,OtelAdres=?,OtelTelefon=?,YöneticiID=?,YildizSayisi=? where OtelID=?";
				try {
					sorguifadesi=cnn.prepareStatement(query);
					sorguifadesi.setString(1,txtOtelAD.getText());
					sorguifadesi.setString(2,txtSehir.getText());
					sorguifadesi.setString(3,txtAdres.getText());
					sorguifadesi.setString(4,txttelefon.getText());
					sorguifadesi.setString(5,yöneticiID);
					sorguifadesi.setInt(6,cmbyildiz.getSelectedIndex());
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
			}
		
			
		});
		btnGuncelle.setBounds(246, 281, 104, 23);
		contentPane.add(btnGuncelle);
		
		JButton btnListele = new JButton("L\u0130STELE");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object []baslik= {"ID","OTELADI","ÞEHÝR","ADRES","TELEFON","YÖNETÝCÝ","KAÇYILDIZ"};
				Object [][]veri;
				String query="SELECT OtelID,OtelAdi,Sehir,OtelAdres,OtelTelefon,CONCAT(AD, \" \",Soyad) AS 'Adsoyad',YildizSayisi FROM `otel` AS a INNER JOIN yönetici AS b ON a.YöneticiID=b.ID WHERE a.Durumu='1'";
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
		btnListele.setBounds(373, 281, 89, 23);
		contentPane.add(btnListele);
		
		JButton btnSil = new JButton("S\u0130L");
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
							String query="UPDATE otel SET Durumu='0' where OtelID=?";
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
		btnSil.setBounds(477, 281, 89, 23);
		contentPane.add(btnSil);
	}

}
