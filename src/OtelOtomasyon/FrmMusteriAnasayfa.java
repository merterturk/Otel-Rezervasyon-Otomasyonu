package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMusteriAnasayfa extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMusteriAnasayfa frame = new FrmMusteriAnasayfa();
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
	public FrmMusteriAnasayfa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(244, 164, 96));
		panel.setBounds(0, 0, 546, 74);
		contentPane.add(panel);
		
		JLabel lblMusAnasayfa = new JLabel("ANASAYFA");
		lblMusAnasayfa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMusAnasayfa.setBounds(119, 11, 167, 52);
		panel.add(lblMusAnasayfa);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(358, 0, 3, 74);
		panel.add(panel_1);
		
		JLabel lblisim = new JLabel("");
		lblisim.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblisim.setBounds(370, 0, 188, 45);
		panel.add(lblisim);
		
		Image img8 = new ImageIcon(this.getClass().getResource("/home.png")).getImage();
     	lblMusAnasayfa.setIcon(new ImageIcon(img8));
     	lblisim.setText("Hoþgeldin"+" "+FrmGirisYap.musad+" "+FrmGirisYap.mussoyad);
     	
     	JButton btnRezervasyonYap = new JButton("REZERVASYON \u0130\u015ELEMLER\u0130");
     	btnRezervasyonYap.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			FrmMusteriRezervasyonYap frm = new FrmMusteriRezervasyonYap();
     			frm.setVisible(true);
     			FrmMusteriAnasayfa.this.setVisible(false);
     		}
     	});
     	btnRezervasyonYap.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	btnRezervasyonYap.setBounds(0, 105, 241, 64);
     	contentPane.add(btnRezervasyonYap);
     	
     	JButton btnodemelerim = new JButton("\u00D6DEMELER\u0130M");
     	btnodemelerim.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			FrmMusteriOdemeler frm = new FrmMusteriOdemeler();
     			frm.setVisible(true);
     			FrmMusteriAnasayfa.this.setVisible(false);
     		}
     	});
     	btnodemelerim.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	btnodemelerim.setBounds(0, 215, 241, 64);
     	contentPane.add(btnodemelerim);
     	
     	JButton btnRezervasyonlarim = new JButton("REZERVASYONLARIM");
     	btnRezervasyonlarim.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			FrmMusteriRezervasyonlar frm = new FrmMusteriRezervasyonlar();
     			frm.setVisible(true);
     			FrmMusteriAnasayfa.this.setVisible(false);
     		}
     	});
     	btnRezervasyonlarim.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	btnRezervasyonlarim.setBounds(305, 105, 241, 64);
     	contentPane.add(btnRezervasyonlarim);
     	
    	Image img9 = new ImageIcon(this.getClass().getResource("/front-desk.png")).getImage();
    	Image img10 = new ImageIcon(this.getClass().getResource("/room (1).png")).getImage();
    	Image img11 = new ImageIcon(this.getClass().getResource("/wallet.png")).getImage();
    	btnRezervasyonlarim.setIcon(new ImageIcon(img10));
    	btnRezervasyonYap.setIcon(new ImageIcon(img9));
    	btnodemelerim.setIcon(new ImageIcon(img11));
    	
    	JButton btnOtelBak = new JButton("OTELLER");
    	btnOtelBak.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			FrmMusteriOteller frm = new FrmMusteriOteller();
    			frm.setVisible(true);
    			FrmMusteriAnasayfa.this.setVisible(false);
    		}
    	});
    	btnOtelBak.setFont(new Font("Times New Roman", Font.BOLD, 13));
    	btnOtelBak.setBounds(305, 215, 241, 64);
    	contentPane.add(btnOtelBak);
    	Image img12 = new ImageIcon(this.getClass().getResource("/hotel.png")).getImage();
     	btnOtelBak.setIcon(new ImageIcon(img12));
	}

}
