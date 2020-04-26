package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRootAnasayfa extends JFrame {

	private JPanel contentPane;
	private JButton btnOtelislemleri;
	public static JLabel lblID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRootAnasayfa frame = new FrmRootAnasayfa();
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

	public FrmRootAnasayfa() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(0, 0, 623, 72);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRootAnasayfa = new JLabel("ANASAYFA");
		lblRootAnasayfa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblRootAnasayfa.setForeground(new Color(255, 255, 255));
		lblRootAnasayfa.setBounds(250, 11, 191, 37);
		panel.add(lblRootAnasayfa);
		
		JButton btnyöneticiislem = new JButton("Y\u00F6netici \u0130\u015Flemleri");
		btnyöneticiislem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRootYönetici frm = new FrmRootYönetici();
				frm.setVisible(true);
				FrmRootAnasayfa.this.setVisible(false);
			}
		});
		btnyöneticiislem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnyöneticiislem.setBounds(10, 98, 178, 64);
		contentPane.add(btnyöneticiislem);
		
		JButton btnpersonelislem = new JButton("Personel \u0130\u015Flemleri");
		btnpersonelislem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRootPersonel frm = new FrmRootPersonel();
				frm.setVisible(true);
				FrmRootAnasayfa.this.setVisible(false);
			}
		});
		btnpersonelislem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnpersonelislem.setBounds(213, 98, 178, 64);
		contentPane.add(btnpersonelislem);
		
		JButton btnmusterislem = new JButton("M\u00FC\u015Fteri \u0130\u015Flemleri");
		btnmusterislem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRootMüþteri frm = new FrmRootMüþteri();
				frm.setVisible(true);
				FrmRootAnasayfa.this.setVisible(false);
			}
		});
		btnmusterislem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnmusterislem.setBounds(435, 98, 178, 64);
		contentPane.add(btnmusterislem);
		
		Image img4 = new ImageIcon(this.getClass().getResource("/man.png")).getImage();
		Image img5 = new ImageIcon(this.getClass().getResource("/waiter.png")).getImage();
		Image img6 = new ImageIcon(this.getClass().getResource("/young.png")).getImage();
		Image img7 = new ImageIcon(this.getClass().getResource("/hotel.png")).getImage();
     	btnmusterislem.setIcon(new ImageIcon(img6));
     	btnyöneticiislem.setIcon(new ImageIcon(img4));
     	btnpersonelislem.setIcon(new ImageIcon(img5));
     	
     	
     	btnOtelislemleri = new JButton("Otel \u0130\u015Flemleri");
     	btnOtelislemleri.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			FrmRootOtel frm =  new FrmRootOtel();
     			frm.setVisible(true);
     			FrmRootAnasayfa.this.setVisible(false);
     		}
     	});
     	btnOtelislemleri.setFont(new Font("Times New Roman", Font.BOLD, 13));
     	btnOtelislemleri.setBounds(213, 194, 178, 64);
     	contentPane.add(btnOtelislemleri);
     	btnOtelislemleri.setIcon(new ImageIcon(img7));
     	
     	Image img8 = new ImageIcon(this.getClass().getResource("/home.png")).getImage();
     	lblRootAnasayfa.setIcon(new ImageIcon(img8));
     	
     			
	}
}
