package chats;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import page_login.Login;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class chat_client {

	private JFrame frame;
	private JTextField msg_text;
	private static JTextArea msg_area;
	

	   static Socket s;
	   static DataInputStream din;
	static DataOutputStream dout; 
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_client window = new chat_client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			s=new Socket("127.0.0.1",1201);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String msgin="";
			
			while(!msgin.equals("exit")) {
				msgin = din.readUTF();
				msg_area.setText(msg_area.getText().trim()+"\n admin : " + msgin);
			}
			
			
			
		}catch(Exception e) {
			
		}
		
		
		
	}

	/**
	 * Create the application.
	 */
	public chat_client() {
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
		msg_area.setBounds(86, 71, 378, 205);
		frame.getContentPane().add(msg_area);
		
		msg_text = new JTextField();
		msg_text.setBounds(86, 287, 378, 35);
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
		msg_send.setBounds(485, 290, 112, 29);
		frame.getContentPane().add(msg_send);
		
		JLabel lblNewLabel = new JLabel("Boite de r\u00E9ception");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setBounds(177, 11, 225, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("D\u00E9connect\u00E9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				Login ah = new Login();
	               
                ah.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(512, 0, 120, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
