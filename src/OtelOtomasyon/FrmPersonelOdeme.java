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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmPersonelOdeme extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPersonelOdeme frame = new FrmPersonelOdeme();
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
	Connection cnn;	
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	private JTable table;
	private JTextField txtID;
	public FrmPersonelOdeme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(147, 112, 219));
		panel.setBounds(0, 0, 816, 59);
		contentPane.add(panel);
		
		JLabel lblodeme = new JLabel("\u00D6DEME \u0130\u015ELEMLER\u0130");
		lblodeme.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblodeme.setBounds(187, 8, 282, 51);
		panel.add(lblodeme);
		Image img7 = new ImageIcon(this.getClass().getResource("/money (1).png")).getImage();
		lblodeme.setIcon(new ImageIcon(img7));
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmPersonelAnasayfa frm = new FrmPersonelAnasayfa();
				frm.setVisible(true);
				FrmPersonelOdeme.this.setVisible(false);
			}
		});
		btngeri.setBounds(727, 24, 89, 35);
		panel.add(btngeri);
		Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btngeri.setIcon(new ImageIcon(img2));
		
		JDateChooser dttarih = new JDateChooser();
		dttarih.setBounds(79, 85, 133, 20);
		contentPane.add(dttarih);
		
		JLabel lblNewLabel = new JLabel("TAR\u0130H:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(21, 91, 48, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnara = new JButton("ARA");
		SimpleDateFormat giris= new SimpleDateFormat("yyyy-MM-dd");
		btnara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dategiris=giris.format(dttarih.getDate());
				Object []baslik= {"ID","Otel","ODANO","MüþteriAD","Soyad","Telefon","Tutar","ÖdemeTürü","Kartno","CVC","SONKULLANMATARÝHÝ"};
				Object [][]veri;
					String query="SELECT a.ID,OtelAdi,ODANO,MusteriAD,MusteriSoyad,MusteriTel,Tutar,OdemeTuru,Kartno,Cvc,KartSonKullanmaTarih FROM `ödeme` AS a INNER JOIN otel AS b on a.OtelID=b.OtelID\r\n" + 
							"INNER JOIN oda as c on a.ODAID=c.ID\r\n" + 
							"INNER JOIN musteri as d ON a.MusteriID=d.MusteriID WHERE a.OtelID='"+FrmGirisYap.OtelID+"' AND OdemeTarihi='"+dategiris+"' AND a.Durumu='1'";
				  try {
					  cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(query);
					getirilen=sorguifadesi.executeQuery();
					int count=0;
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
					
				
					
				} 
					catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  }		
				
			
		});
		btnara.setBounds(79, 116, 133, 23);
		contentPane.add(btnara);
		
		JScrollPane scrollPane = new JScrollPane();
		
	
		scrollPane.setBounds(0, 152, 816, 199);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionBackground(Color.GREEN);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table.getSelectedRow();
				txtID.setText(table.getValueAt(i, 0).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblId.setBounds(398, 91, 48, 14);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(452, 88, 96, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
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
							String query="UPDATE ödeme SET Durumu='0' where ID=?";
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
		btnSil.setBounds(452, 116, 96, 23);
		contentPane.add(btnSil);
		
		JButton btnListele = new JButton("L\u0130STELE");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object []baslik= {"ID","ODANO","MüþteriAD","Soyad","Telefon","Tutar","ÖdemeTürü","Kartno","CVC","SONKULLANMATARÝHÝ"};
				Object [][]veri;
					String query="SELECT a.ID,ODANO,MusteriAD,MusteriSoyad,MusteriTel,Tutar,OdemeTuru,OdemeTarihi,Kartno,Cvc,KartSonKullanmaTarih FROM `ödeme` AS a INNER JOIN otel AS b on a.OtelID=b.OtelID\r\n" + 
							"INNER JOIN oda as c on a.ODAID=c.ID\r\n" + 
							"INNER JOIN musteri as d ON a.MusteriID=d.MusteriID WHERE a.OtelID='"+FrmGirisYap.OtelID+"'AND a.Durumu='1'";
				  try {
					  cnn=Baglanti.baglan();
					sorguifadesi=cnn.prepareStatement(query);
					getirilen=sorguifadesi.executeQuery();
					int count=0;
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
					
				
					
				} 
					catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  }		
				
			
		});
		btnListele.setBounds(246, 116, 133, 23);
		contentPane.add(btnListele);
	}
}
