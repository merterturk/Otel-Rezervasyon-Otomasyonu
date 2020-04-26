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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmMusteriOteller extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cmbsehir;
	private JComboBox cmbYildiz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMusteriOteller frame = new FrmMusteriOteller();
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
	public FrmMusteriOteller() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(127, 255, 212));
		panel.setBounds(0, 0, 578, 50);
		contentPane.add(panel);
		
		JLabel lblOtelArama = new JLabel("OTEL L\u0130STES\u0130");
		lblOtelArama.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblOtelArama.setBounds(184, 16, 221, 28);
		panel.add(lblOtelArama);
		Image img9 = new ImageIcon(this.getClass().getResource("/hotel.png")).getImage();
    	lblOtelArama.setIcon(new ImageIcon(img9));
    	
    	JButton btngeri = new JButton("");
    	btngeri.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			FrmMusteriAnasayfa frm = new FrmMusteriAnasayfa();
    			frm.setVisible(true);
    			FrmMusteriOteller.this.setVisible(false);
    		}
    	});
    	btngeri.setBounds(489, 16, 89, 33);
    	panel.add(btngeri);
    	Image img15 = new ImageIcon(this.getClass().getResource("/back.png")).getImage();
    	btngeri.setIcon(new ImageIcon(img15));
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBounds(-2, 211, 580, 206);
    	contentPane.add(scrollPane);
    	
    	table = new JTable();
    	scrollPane.setViewportView(table);
    	
    	JButton btnlistele = new JButton("Listele");
    	btnlistele.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			Object []baslik= {"OTELADI","ÞEHÝR","ADRES","TELEFON","KAÇYILDIZ"};
    			Object [][]veri;
    			String query="SELECT OtelAdi,Sehir,OtelAdres,OtelTelefon,YildizSayisi FROM otel WHERE Durumu='1'  "; 
    				try {
    					cnn=Baglanti.baglan();
    					sorguifadesi=cnn.prepareStatement(query);
    					getirilen=sorguifadesi.executeQuery();
    					int count=0;
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
    	    		}
    				 catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				
    			
    		}
    	});
    	btnlistele.setBounds(391, 169, 110, 31);
    	contentPane.add(btnlistele);
    	
    	JButton btnAra = new JButton("ARA");
    	btnAra.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			Object []baslik= {"OTELADI","ÞEHÝR","ADRES","TELEFON","KAÇYILDIZ"};
    			Object [][]veri;
    			String query="SELECT OtelAdi,Sehir,OtelAdres,OtelTelefon,YildizSayisi FROM otel WHERE Sehir='"+cmbsehir.getSelectedItem().toString()+"'AND YildizSayisi='"+cmbYildiz.getSelectedItem().toString()+"'AND Durumu='1'"; 
    				try {
    					cnn=Baglanti.baglan();
    					sorguifadesi=cnn.prepareStatement(query);
    					getirilen=sorguifadesi.executeQuery();
    					int count=0;
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
    	    		}
    				 catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    		}
    			
    		
	});
    	btnAra.setBounds(10, 169, 110, 31);
    	contentPane.add(btnAra);
    	
    	 cmbsehir = new JComboBox();
    	cmbsehir.setModel(new DefaultComboBoxModel(new String[] {"", "Antalya", "Bodrum", "\u0130zmir", "\u0130stanbul", "Tekirda\u011F"}));
    	cmbsehir.setBounds(21, 92, 97, 22);
    	contentPane.add(cmbsehir);
    	
    	 cmbYildiz = new JComboBox();
    	cmbYildiz.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5"}));
    	cmbYildiz.setBounds(165, 92, 97, 22);
    	contentPane.add(cmbYildiz);
    	
    	JLabel lblNewLabel = new JLabel("\u015Eehir");
    	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	lblNewLabel.setBounds(21, 61, 69, 17);
    	contentPane.add(lblNewLabel);
    	
    	JLabel lblYldz = new JLabel("Y\u0131ld\u0131z");
    	lblYldz.setFont(new Font("Times New Roman", Font.BOLD, 14));
    	lblYldz.setBounds(166, 61, 69, 17);
    	contentPane.add(lblYldz);
	}

}
