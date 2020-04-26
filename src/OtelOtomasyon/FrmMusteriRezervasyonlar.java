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
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class FrmMusteriRezervasyonlar extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMusteriRezervasyonlar frame = new FrmMusteriRezervasyonlar();
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
	public FrmMusteriRezervasyonlar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 0, 633, 50);
		contentPane.add(panel);
		
		JLabel lblRezervasyonlarim = new JLabel("REZERVASYONLARIM");
		lblRezervasyonlarim.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblRezervasyonlarim.setBounds(206, 11, 221, 28);
		panel.add(lblRezervasyonlarim);
	 	Image img10 = new ImageIcon(this.getClass().getResource("/room (1).png")).getImage();
	 	lblRezervasyonlarim.setIcon(new ImageIcon(img10));
	 	
	 	JButton btngeri = new JButton("");
	 	btngeri.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			FrmMusteriAnasayfa frm = new FrmMusteriAnasayfa();
    			frm.setVisible(true);
    			FrmMusteriRezervasyonlar.this.setVisible(false);
	 		}
	 	});
	 	btngeri.setBounds(544, 16, 89, 33);
	 	panel.add(btngeri);
	 	Image img15 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
    	btngeri.setIcon(new ImageIcon(img15));
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBounds(0, 190, 633, 254);
    	contentPane.add(scrollPane);
    	
    	table = new JTable();
    	scrollPane.setViewportView(table);
    
    	JButton btnListele = new JButton("Listele");
    	btnListele.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			Object []baslik= {"OTEL","ODANO","ODATÜRÜ","GÝRÝSTARÝH","CÝKÝSTARÝH","YETÝSKÝN","COCUK"};
				Object [][]veri;
				String query="	SELECT OtelAdi,ODANO,ODATURU,GirisTarih,CikisTarih,a.YetiskinSayisi,a.CocukSayisi FROM rezervasyon AS a \r\n" + 
						"    	INNER JOIN otel AS b ON a.OtelID=b.OtelID\r\n" + 
						"    	INNER JOIN oda AS c ON a.OdaID=c.ID where a.MusteriID='"+FrmGirisYap.musID+"' AND a.Durumu='1'";
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
    	btnListele.setBounds(237, 118, 107, 38);
    	contentPane.add(btnListele);
	}

}
