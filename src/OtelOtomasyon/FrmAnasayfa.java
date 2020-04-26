package OtelOtomasyon;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAnasayfa extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			 	FrmAnasayfa frame = new FrmAnasayfa();
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
	public FrmAnasayfa() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(25);
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 434, 68);
		contentPane.add(panel);
		
		JLabel lblisim = new JLabel("OTEL REZERVASYON S\u0130STEM\u0130");
		lblisim.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(lblisim);
		//Müþteri Kayýt Olma Formuna Aktarma Ýþlemi
		JButton btnkayitol = new JButton("KAYIT OL");
		btnkayitol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmKayitOl frm = new FrmKayitOl();
				frm.setVisible(true);
				FrmAnasayfa.this.setVisible(false);
				
			}
		});
		btnkayitol.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnkayitol.setBounds(29, 101, 134, 55);
		contentPane.add(btnkayitol);
		
		JButton btngirisyap = new JButton("G\u0130R\u0130\u015E YAP");
		//Giris Yapma Formuna Aktarma Ýþlemi
		btngirisyap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmGirisYap frm = new FrmGirisYap();
				frm.setVisible(true);
				FrmAnasayfa.this.setVisible(false);
			}
		});
		btngirisyap.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btngirisyap.setBounds(247, 101, 134, 55);
		contentPane.add(btngirisyap);
		
		Image img = new ImageIcon(this.getClass().getResource("/account.png")).getImage();
		Image img2 = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		btnkayitol.setIcon(new ImageIcon(img));
		btngirisyap.setIcon(new ImageIcon(img2));
		Image img3 = new ImageIcon(this.getClass().getResource("/hotel.png")).getImage();
        lblisim.setIcon(new ImageIcon(img3));
		
	}
}
