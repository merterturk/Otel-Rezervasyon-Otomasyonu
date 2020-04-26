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
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class FrmMusteriOdemeler extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMusteriOdemeler frame = new FrmMusteriOdemeler();
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
	public FrmMusteriOdemeler() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 573, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblodeme = new JLabel("\u00D6DEMELER\u0130M");
		lblodeme.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblodeme.setBounds(179, 11, 182, 28);
		panel.add(lblodeme);
		
		Image img9 = new ImageIcon(this.getClass().getResource("/money (1).png")).getImage();
    	lblodeme.setIcon(new ImageIcon(img9));
    	
    	JButton btngeri = new JButton("");
    	btngeri.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			FrmMusteriAnasayfa frm = new FrmMusteriAnasayfa();
    			frm.setVisible(true);
    			FrmMusteriOdemeler.this.setVisible(false);
    		}
    	});
    	btngeri.setBounds(484, 16, 89, 33);
    	panel.add(btngeri);
    	Image img15 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
    	btngeri.setIcon(new ImageIcon(img15));
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBounds(0, 162, 573, 227);
    	contentPane.add(scrollPane);
    	
    	table = new JTable();
    	scrollPane.setViewportView(table);
    	
    	JButton btnListele = new JButton("Listele");
    	btnListele.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			Object []baslik= {"OTEL","ODA TÜRÜ","ÖDEME TÜRÜ","TUTAR","ÖDEME TARÝHÝ"};
				Object [][]veri;
				String query="SELECT OtelAdi,ODATURU,OdemeTuru,Tutar,OdemeTarihi FROM `ödeme` AS a INNER JOIN otel AS b ON a.OtelID=b.OtelID \r\n" + 
						"INNER JOIN oda as c ON a.OdaID=C.ID\r\n" + 
						"WHERE MusteriID='"+FrmGirisYap.musID+"'AND a.Durumu='1';";
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
					veri=new Object[count][5];
					getirilen.first();
					for(int i=0;i<count;i++) {
						for(int j=0;j<5;j++) {
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
    	btnListele.setBounds(214, 110, 107, 38);
    	contentPane.add(btnListele);
	}
}
