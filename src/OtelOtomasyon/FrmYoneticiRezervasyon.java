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
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.toedter.calendar.*;
public class FrmYoneticiRezervasyon extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmYoneticiRezervasyon frame = new FrmYoneticiRezervasyon();
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
	PreparedStatement sorguifadesi1=null;
	ResultSet getirilen1=null;
	public FrmYoneticiRezervasyon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(173, 255, 47));
		panel.setBounds(0, 0, 803, 46);
		contentPane.add(panel);
		
		JLabel lblrezervasyon = new JLabel("REZERVASYON \u0130\u015ELEMLER\u0130");
		lblrezervasyon.setForeground(Color.BLACK);
		lblrezervasyon.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblrezervasyon.setBounds(265, 0, 274, 43);
		panel.add(lblrezervasyon);
		
		JButton btngeri = new JButton("");
		btngeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYoneticiAnasayfa frm = new FrmYoneticiAnasayfa();
				frm.setVisible(true);
				FrmYoneticiRezervasyon.this.setVisible(false);
			}
		});
		btngeri.setBounds(714, 11, 89, 35);
		panel.add(btngeri);
		
		Image img2 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btngeri.setIcon(new ImageIcon(img2));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 209, 803, 248);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnListele = new JButton("L\u0130STELE");
		
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object []baslik= {"ID","ODANO","ADI","SOYADI","GÝRÝSTARÝHÝ","ÇIKIÞTARÝHÝ","YETÝÞKÝN","ÇOCUK"};
				Object [][]veri;
				String query="SELECT RezID,b.ODANO,c.MusteriAD,c.MusteriSoyad,a.GirisTarih,a.CikisTarih,a.YetiskinSayisi,a.CocukSayisi FROM `rezervasyon` AS a INNER JOIN oda AS b on a.ODAID=b.ID INNER JOIN musteri AS c on a.MusteriID=c.MusteriID WHERE a.Durumu='1' AND a.OtelID='"+FrmYoneticiAnasayfa.otelID+"' ";
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
					veri=new Object[count][8];
					getirilen.first();
					for(int i=0;i<count;i++) {
						for(int j=0;j<8;j++) {
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
		btnListele.setBounds(333, 165, 103, 33);
		contentPane.add(btnListele);
		
		JLabel lblgiris = new JLabel("Giri\u015F Tarihi:");
		lblgiris.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblgiris.setBounds(10, 78, 93, 23);
		contentPane.add(lblgiris);
		
		JLabel lblcikis = new JLabel("\u00C7\u0131k\u0131\u015F Tarihi:");
		lblcikis.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblcikis.setBounds(10, 136, 93, 23);
		contentPane.add(lblcikis);
		
		JDateChooser dtgiris = new JDateChooser();
		dtgiris.setBounds(98, 81, 140, 20);
		contentPane.add(dtgiris);
		
		JDateChooser dtcikis = new JDateChooser();
		dtcikis.setBounds(98, 136, 140, 20);
		contentPane.add(dtcikis);
		SimpleDateFormat giris= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat cikis= new SimpleDateFormat("yyyy-MM-dd");
		JButton btnAra = new JButton("ARA");
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String dategiris=giris.format(dtgiris.getDate());
			String datecikis=giris.format(dtcikis.getDate());
			Object []baslik= {"ID","ODANO","ADI","SOYADI","GÝRÝSTARÝHÝ","ÇIKIÞTARÝHÝ","YETÝÞKÝN","ÇOCUK"};
			Object [][]veri;
				String query="SELECT RezID,b.ODANO,c.MusteriAD,c.MusteriSoyad,a.GirisTarih,a.CikisTarih,a.YetiskinSayisi,a.CocukSayisi FROM `rezervasyon` AS a INNER JOIN oda AS b on a.ODAID=b.ID INNER JOIN musteri AS c on a.MusteriID=c.MusteriID WHERE GirisTarih BETWEEN '"+dategiris+"' AND '"+datecikis+"' AND a.Durumu='1' AND a.OtelID='"+FrmYoneticiAnasayfa.otelID+"'";		
			  try {
				  cnn=Baglanti.baglan();
				sorguifadesi=cnn.prepareStatement(query);
				getirilen=sorguifadesi.executeQuery();
				int count=0;
					getirilen.last();
					count=getirilen.getRow();
					veri=new Object[count][8];
					getirilen.first();
					for(int i=0;i<count;i++) {
						for(int j=0;j<8;j++) {
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
		btnAra.setBounds(98, 170, 103, 33);
		contentPane.add(btnAra);
		Image img3 = new ImageIcon(this.getClass().getResource("/reception.png")).getImage();
		lblrezervasyon.setIcon(new ImageIcon(img3));
	}
}
