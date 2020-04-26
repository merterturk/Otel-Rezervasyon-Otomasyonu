package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
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
import com.toedter.calendar.JDateChooser;

public class FrmYoneticiOdeme extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lbltoplamfiyat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmYoneticiOdeme frame = new FrmYoneticiOdeme();
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
	PreparedStatement sorguifadesi1=null;
	ResultSet getirilen1=null;
	public FrmYoneticiOdeme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(205, 92, 92));
		panel.setBounds(0, 0, 568, 46);
		contentPane.add(panel);
		
		JLabel lblodeme = new JLabel("\u00D6DEME \u0130\u015ELEMLER\u0130");
		lblodeme.setForeground(Color.BLACK);
		lblodeme.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblodeme.setBounds(170, 0, 207, 43);
		panel.add(lblodeme);
		JLabel lbltoplamfiyat = new JLabel("");
		lbltoplamfiyat.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbltoplamfiyat.setBounds(98, 68, 103, 18);
		contentPane.add(lbltoplamfiyat);
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYoneticiAnasayfa frm = new FrmYoneticiAnasayfa();
				frm.setVisible(true);
				FrmYoneticiOdeme.this.setVisible(false);
			}
		});
		btngeri.setBounds(479, 11, 89, 35);
		panel.add(btngeri);
		Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btngeri.setIcon(new ImageIcon(img2));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 178, 568, 256);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnListele = new JButton("L\u0130STELE");
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sum=0;
				Object []baslik= {"ID","ODANO","ADI","SOYADI","Tutar","ÖDEMETÜRÜ"};
				Object [][]veri;
				String query="SELECT a.ID,ODANO,MusteriAD,MusteriSoyad,Tutar,OdemeTuru FROM ödeme AS a INNER JOIN oda AS b ON a.ODAID=b.ID\r\n" + 
						"INNER JOIN musteri AS c ON a.MusteriID=c.MusteriID WHERE a.Durumu='1' AND a.OTELID='"+FrmYoneticiAnasayfa.otelID+"'";
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
			int rowscount=table.getRowCount();
			for(int b=0;b<rowscount;b++) {
				sum=sum+Integer.parseInt(table.getValueAt(b,4).toString());
			}
		 String toplam=Integer.toString(sum);
		 lbltoplamfiyat.setText(toplam);
				
			}
		
		});
		btnListele.setBounds(210, 139, 103, 33);
		contentPane.add(btnListele);
		
		JLabel lblfiyat = new JLabel("KAZAN\u00C7:");
		lblfiyat.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblfiyat.setBounds(10, 68, 103, 18);
		contentPane.add(lblfiyat);
		Image img3 = new ImageIcon(this.getClass().getResource("/money (1).png")).getImage();
		lblodeme.setIcon(new ImageIcon(img3));
		
	}
}
