package chats;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import page_login.Login;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class chat_admin {

	private JFrame frame;
	private JTextField msg_text;
	private static JTextArea msg_area;

	static ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_admin window = new chat_admin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String msgin= "";
		try {
			
			ss = new ServerSocket(1201);
			
			s = ss.accept();
			
			
			din = new DataInputStream(s.getInputStream());
			
			
			dout = new DataOutputStream(s.getOutputStream());
			
			while(!msgin.equals("exit")) {
				
				msgin = din.readUTF();
				
				msg_area.setText(msg_area.getText().trim()+"\n vendeur : "+msgin);
			}
			
			
		}catch(Exception e) {
			
		}
		
	}

	/**
	 * Create the application.
	 */
	
	
	
	public chat_admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 648, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 msg_area = new JTextArea();
		msg_area.setBounds(79, 67, 382, 192);
		frame.getContentPane().add(msg_area);
		
		msg_text = new JTextField();
		msg_text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		msg_text.setBounds(79, 270, 382, 39);
		frame.getContentPane().add(msg_text);
		msg_text.setColumns(10);
		
		JButton msg_send = new JButton("envoyer");
		msg_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msgout ="";
					msgout = msg_text.getText().trim();
					dout.writeUTF(msgout);
					
				}catch(Exception e1) {
					
					e1.printStackTrace();
				}
				
				
				
			}
		});
		msg_send.setBounds(481, 274, 89, 31);
		frame.getContentPane().add(msg_send);
		
		lblNewLabel = new JLabel("Boite de r\u00E9ception");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setBounds(166, 20, 225, 36);
		frame.getContentPane().add(lblNewLabel);
		
		btnNewButton = new JButton("D\u00E9connect\u00E9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Login ah = new Login();   
                 ah.setVisible(true);
				
			}
		});
		btnNewButton.setForeground(SystemColor.desktop);
		btnNewButton.setBounds(521, 0, 111, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
