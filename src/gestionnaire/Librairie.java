package gestionnaire;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.sql.*;
import ConnectionDB.Conix;
import net.proteanit.sql.DbUtils;
import page_login.Login;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
 import chats.chat_admin;

public class Librairie {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Librairie window = new Librairie();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	PreparedStatement pst;
	ResultSet rs;
	
	public Librairie() {
		initialize();
		Conix.getCon();
		table_load();
	}

	//le moyen le plus simple de remplir/modéliser une table avec ResultSet .. Téléchargez et incluez rs2xml.jar
	public void table_load() {
		try {
			 pst=Conix.getCon().prepareStatement("select * from livres");
			 rs = pst.executeQuery();
			 table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100,648, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Librairie");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(28, 11, 113, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 82, 270, 203);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nom du livre");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 29, 97, 20);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("L'\u00E9dition");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 70, 97, 20);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Le prix");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(10, 116, 97, 20);
		panel.add(lblNewLabel_1_2);
		
		JTextPane txtbookname = new JTextPane();
		txtbookname.setBounds(133, 29, 112, 20);
		panel.add(txtbookname);
		
		JTextPane txtedition = new JTextPane();
		txtedition.setBounds(133, 70, 112, 20);
		panel.add(txtedition);
		
		JTextPane txtprice = new JTextPane();
		txtprice.setBounds(133, 116, 112, 20);
		panel.add(txtprice);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("la place de livre");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(10, 160, 121, 20);
		panel.add(lblNewLabel_1_2_1);
		
		JTextPane txtplace = new JTextPane();
		txtplace.setBounds(133, 160, 112, 20);
		panel.add(txtplace);
		
		JButton btnNewButton = new JButton("enregistr\u00E9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String lname , edition , prix ,place;
				lname= txtbookname.getText();
				edition = txtedition.getText();
				prix = txtprice.getText();
				place = txtplace.getText();				
				try {
					PreparedStatement pst = Conix.getCon().prepareStatement("insert into livres(nom,edition,prix,place) values (?,?,?,?)");
					pst.setString(1, lname);
					pst.setString(2, edition);
					pst.setString(3, prix);
					pst.setString(4, place);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"enregistrement !!!!!!!");
				table_load();
					txtbookname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtplace.setText("");
					txtbookname.requestFocus();
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(28, 296, 95, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnQuitter.setBounds(127, 296, 79, 33);
		frame.getContentPane().add(btnQuitter);
		
		JButton btnSupprimer = new JButton("supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbookname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtplace.setText("");
				txtbookname.requestFocus();
				
				
				
			}
		});
		btnSupprimer.setBounds(209, 296, 89, 33);
		frame.getContentPane().add(btnSupprimer);
		
		JScrollPane tableux = new JScrollPane();
		tableux.setBounds(301, 91, 321, 194);
		frame.getContentPane().add(tableux);
		
		table = new JTable();
		tableux.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(326, 24, 270, 47);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane txtbid = new JTextPane();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					String id = txtbid.getText();
					pst=Conix.getCon().prepareStatement("select nom,edition,prix,place from livres where id=? ");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					if(rs.next()==true) {
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						String place = rs.getString(4);
						    txtbookname.setText(name);
							txtedition.setText(edition);
							txtprice.setText(price);
							txtplace.setText(place);
					}else {
						txtbookname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtplace.setText("");
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				
		}
}
				);
		txtbid.setBounds(120, 16, 112, 20);
		panel_1.add(txtbid);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Livre ID");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(42, 16, 73, 20);
		panel_1.add(lblNewLabel_1_1_1);
		
		JButton btnupdate = new JButton("Mis \u00E0 jour");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String lname , edition , prix, place ,bid;
				lname= txtbookname.getText();
				edition = txtedition.getText();
				prix = txtprice.getText();
				place = txtplace.getText();
				bid = txtbid.getText();
				
				try {
					PreparedStatement pst = Conix.getCon().prepareStatement("update livres set nom=? ,edition=?,prix=? , place=? where id=?");
					pst.setString(1, lname);
					pst.setString(2, edition);
					pst.setString(3, prix);
					pst.setString(4, place);
					pst.setString(5, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"modification !!!!!!!");
				table_load();
					txtbookname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtplace.setText("");
					txtbookname.requestFocus();
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnupdate.setBounds(311, 296, 95, 33);
		frame.getContentPane().add(btnupdate);
		
		JButton btnSupprimer_2 = new JButton("supprimer");
		btnSupprimer_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bid;
				
				bid = txtbid.getText();
				
				try {
					PreparedStatement pst = Conix.getCon().prepareStatement("delete from livres  where id=?");
					
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"suppresion !!!!!!!");
				table_load();
					txtbookname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtplace.setText("");
					txtbookname.requestFocus();
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			}
				
				
				
		});
		btnSupprimer_2.setBounds(416, 296, 95, 33);
		frame.getContentPane().add(btnSupprimer_2);
		
		JButton btnNewButton_1 = new JButton("Messagerie");
		btnNewButton_1.setBackground(new Color(102, 204, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chat_admin ah = new chat_admin();
               
                 ah.setVisible(true);
				
				
				
				
			}
		});
		btnNewButton_1.setBounds(521, 298, 101, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("D\u00E9connect\u00E9");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Login ah = new Login();
	               
                ah.setVisible(true);
				
				
				
			}
		});
		btnNewButton_2.setBounds(508, 0, 124, 23);
		frame.getContentPane().add(btnNewButton_2);
		frame.setVisible(true);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
