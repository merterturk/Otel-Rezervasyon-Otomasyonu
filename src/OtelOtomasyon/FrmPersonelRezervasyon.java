package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;

public class FrmPersonelRezervasyon extends JFrame {
    private JComboBox cmbOda;
	private JPanel contentPane;
	private JLabel lblodaid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPersonelRezervasyon frame = new FrmPersonelRezervasyon();
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
	int toplamoda;
	Connection cnn=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	private JTable table;
	private JTextField txtID;
	private JTextField txtyetiskin;
	private JTextField txtcocuk;
	private JDateChooser dtgiris;
	private JDateChooser dtcikis;
	static String odaid;
	void temizle() {
		txtID.setText(null);
		txtcocuk.setText(null);
		txtyetiskin.setText(null);
		cmbOda.setSelectedIndex(1);
		dtgiris.setDate(null);
		dtcikis.setDate(null);
	}
	public FrmPersonelRezervasyon() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				String sql="SELECT ODANO FROM `oda` WHERE OTELID='"+FrmGirisYap.OtelID+"' AND Durumu='1' ORDER BY ODANO ASC";
				try {
					cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(sql);
					getirilen=sorguifadesi.executeQuery();
					while(getirilen.next()) {
						cmbOda.addItem(getirilen.getString("ODANO"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
				
				 }
			}
		);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 728, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblrezervasyon = new JLabel("REZERVASYON \u0130\u015ELEMLER\u0130");
		lblrezervasyon.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblrezervasyon.setBounds(152, 0, 282, 51);
		panel.add(lblrezervasyon);
	 	Image img7 = new ImageIcon(this.getClass().getResource("/reception.png")).getImage();
	 	lblrezervasyon.setIcon(new ImageIcon(img7));
	 	
	 	JButton btngeri = new JButton("");
	 	btngeri.setBounds(639, 11, 89, 42);
	 	panel.add(btngeri);
	 	btngeri.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			FrmPersonelAnasayfa frm = new FrmPersonelAnasayfa();
	 			frm.setVisible(true);
	 			FrmPersonelRezervasyon.this.setVisible(false);
	 		}
	 	});
	 	
		Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btngeri.setIcon(new ImageIcon(img2));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 239, 728, 151);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				txtID.setText(table.getValueAt(i, 0).toString());
				cmbOda.setSelectedItem(table.getValueAt(i,5).toString());
                try {
					Date dtgiriss =new SimpleDateFormat("yyyy-MM-dd").parse(table.getValueAt(i, 6).toString());
					dtgiris.setDate(dtgiriss);
					Date dtcikiss =new SimpleDateFormat("yyyy-MM-dd").parse(table.getValueAt(i, 7).toString());
					dtcikis.setDate(dtcikiss);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                txtcocuk.setText(table.getValueAt(i, 9).toString());
                txtyetiskin.setText(table.getValueAt(i, 8).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblOdaId = new JLabel("ODA:");
		lblOdaId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOdaId.setBounds(0, 105, 104, 32);
		contentPane.add(lblOdaId);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblId.setBounds(0, 64, 104, 32);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(86, 70, 96, 20);
		contentPane.add(txtID);
		
		cmbOda = new JComboBox();
		cmbOda.setModel(new DefaultComboBoxModel(new String[] {"Oda Numaras\u0131n\u0131 Se\u00E7iniz"}));
		
	
		cmbOda.setBounds(68, 110, 147, 22);
		contentPane.add(cmbOda);
		
		JLabel lblOdaId_1 = new JLabel("Giri\u015FTarihi:");
		lblOdaId_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOdaId_1.setBounds(0, 151, 104, 32);
		contentPane.add(lblOdaId_1);
		
		JLabel lblOdaId_2 = new JLabel("CikisTarihi:");
		lblOdaId_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOdaId_2.setBounds(0, 196, 104, 32);
		contentPane.add(lblOdaId_2);
		
		JLabel lblOdaId_2_1 = new JLabel("Yeti\u015Fkin Say\u0131s\u0131:");
		lblOdaId_2_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOdaId_2_1.setBounds(245, 64, 104, 32);
		contentPane.add(lblOdaId_2_1);
		
		JLabel lblOdaId_2_2 = new JLabel("\u00C7ocuk Say\u0131s\u0131:");
		lblOdaId_2_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOdaId_2_2.setBounds(245, 105, 104, 32);
		contentPane.add(lblOdaId_2_2);
		
		txtyetiskin = new JTextField();
		txtyetiskin.setBounds(331, 70, 96, 20);
		contentPane.add(txtyetiskin);
		txtyetiskin.setColumns(10);
		
		txtcocuk = new JTextField();
		txtcocuk.setColumns(10);
		txtcocuk.setBounds(331, 111, 96, 20);
		contentPane.add(txtcocuk);
		
		 dtgiris = new JDateChooser();
		dtgiris.setBounds(86, 155, 96, 20);
		contentPane.add(dtgiris);
		
		 dtcikis = new JDateChooser();
		dtcikis.setBounds(86, 200, 96, 20);
		contentPane.add(dtcikis);
		cmbOda.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String sql="Select ID,ODANO from oda WHERE OtelID='"+FrmGirisYap.OtelID+"'AND ODANO='"+cmbOda.getSelectedItem()+"' AND durumu='1' ";
				
				try {
					cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(sql);
					getirilen=sorguifadesi.executeQuery();
				
					if(getirilen.next()) {
					odaid=getirilen.getString("ID");
					
					
						
						//cmbotel.addItem(getirilen.getString("OtelAdi"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton btnGuncelle = new JButton("G\u00FCncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="UPDATE rezervasyon AS a SET OdaID=?,GirisTarih=?,CikisTarih=?,YetiskinSayisi=?,CocukSayisi=? where RezID=?";
				try {
					SimpleDateFormat giris= new SimpleDateFormat("yyyy-MM-dd");
					String dategiris=giris.format(dtgiris.getDate());
					SimpleDateFormat cikis= new SimpleDateFormat("yyyy-MM-dd");
					String datecikis=cikis.format(dtcikis.getDate());
					sorguifadesi=cnn.prepareStatement(query);
		            sorguifadesi.setString(1,odaid);
					sorguifadesi.setString(2,dategiris);
					sorguifadesi.setString(3,datecikis);
					sorguifadesi.setString(4,txtyetiskin.getText());
					sorguifadesi.setString(5,txtcocuk.getText());
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
			}
		});
		btnGuncelle.setBounds(219, 196, 96, 39);
		contentPane.add(btnGuncelle);
		
		JButton btnSil = new JButton("\u0130ptal Et");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null,txtID.getText()+" "+"Numaralý Rezervasyonu Ýptal Etmek Ýstediðinize eminmisiniz", "UYARI",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
              if(result==JOptionPane.NO_OPTION) {
             	 temizle();
              }
			 
			     
			      if(result==JOptionPane.YES_OPTION) {
			    	  try {
							cnn=Baglanti.baglan();
							String query="UPDATE rezervasyon SET Durumu='0' where RezID=?";
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
		btnSil.setBounds(370, 196, 96, 39);
		contentPane.add(btnSil);
		table.setSelectionBackground(Color.GREEN);
		JButton btnListele = new JButton("Listele");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object []baslik= {"ID","MüþteriAD","SOYAD","TC","TELEFON","ODANO","GÝRÝÞTARÝH","ÇIKIÞTARÝH","YETÝÞKÝN","ÇOCUK"};
				Object [][]veri;
				String query="SELECT RezID,MusteriAD,MusteriSoyad,MusteriTC,MusteriTel,ODANO,GirisTarih,CikisTarih,YetiskinSayisi,CocukSayisi FROM rezervasyon as a INNER JOIN musteri as b ON a.MusteriID=b.MusteriID\r\n" + 
						"INNER JOIN oda as c on a.OdaID=c.ID where a.OtelID='"+FrmGirisYap.OtelID+"' AND a.Durumu='1'";
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
					veri=new Object[count][10];
					getirilen.first();
					for(int i=0;i<count;i++) {
						for(int j=0;j<10;j++) {
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
		btnListele.setBounds(545, 196, 96, 39);
		contentPane.add(btnListele);
		
	 	
	}
}
