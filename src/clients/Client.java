package clients;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import ConnectionDB.Conix;
import chats.chat_client;
import net.proteanit.sql.DbUtils;
import page_login.Login;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client {

	private JFrame frame;
	private JTextField txtbid;
	private JTextField txtbookname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTable table_1;
	private JScrollPane tableux;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param userName 
	 */
	public Client() {
		initialize();
		Conix.getCon();
		table_load();
	}




	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtplace;
	private JLabel lblNewLabel_1_4;
	private JButton btnvendre;
	private JButton btnNewButton;
	
	//le moyen le plus simple de remplir/modéliser une table avec ResultSet .. Téléchargez et incluez rs2xml.jar
	
	public void table_load() {
		try {
			 pst=Conix.getCon().prepareStatement("select * from livres");
			 rs = pst.executeQuery();
			 table_1.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 648, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Librarie");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(35, 31, 126, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Recherche", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(35, 114, 226, 191);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID livre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 28, 87, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nom du livre");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 62, 87, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("L'\u00E9dition");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(10, 93, 87, 23);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("le prix");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3.setBounds(10, 127, 87, 23);
		panel.add(lblNewLabel_1_3);
		
		txtbid = new JTextField();
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
		});
		txtbid.setBounds(118, 30, 86, 20);
		panel.add(txtbid);
		txtbid.setColumns(10);
		
		txtbookname = new JTextField();
		txtbookname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
try {
					
					String name = txtbookname.getText();
					pst=Conix.getCon().prepareStatement("select id,edition,prix, place from livres where nom=? ");
					pst.setString(1, name);
					ResultSet rs = pst.executeQuery();
					if(rs.next()==true) {
						String id = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						String place = rs.getString(4);
						txtbid.setText(id);
							txtedition.setText(edition);
							txtprice.setText(price);
							txtplace.setText(place);
					}else {
						txtbid.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtplace.setText("");
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
			}
		});
		txtbookname.setColumns(10);
		txtbookname.setBounds(118, 64, 86, 20);
		panel.add(txtbookname);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(118, 95, 86, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(118, 129, 86, 20);
		panel.add(txtprice);
		
		txtplace = new JTextField();
		txtplace.setColumns(10);
		txtplace.setBounds(118, 160, 86, 20);
		panel.add(txtplace);
		
		lblNewLabel_1_4 = new JLabel("la place de livre");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_4.setBounds(10, 161, 98, 23);
		panel.add(lblNewLabel_1_4);
		
		table = new JTable();
		table.setBounds(289, 124, 1, 1);
		frame.getContentPane().add(table);
		
		tableux = new JScrollPane();
		tableux.setBounds(289, 114, 333, 191);
		frame.getContentPane().add(tableux);
		
		table_1 = new JTable();
		tableux.setViewportView(table_1);
		
		lblNewLabel_2 = new JLabel("List des Livres");
		lblNewLabel_2.setFont(new Font("Sitka Small", Font.BOLD, 15));
		lblNewLabel_2.setBounds(373, 68, 126, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnvendre = new JButton("vendre");
		btnvendre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
                 String bid;
				
				bid = txtbid.getText();
				
				try {
					PreparedStatement pst = Conix.getCon().prepareStatement("delete from livres  where id=?");
					
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Il a était vendu");
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
		btnvendre.setBounds(45, 316, 89, 23);
		frame.getContentPane().add(btnvendre);
		
		btnNewButton = new JButton("Messagerie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				chat_client ah = new chat_client();
	               
                ah.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(144, 316, 106, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("D\u00E9connect\u00E9");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login ah = new Login();
	               
                ah.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(516, 11, 106, 23);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}

	public void setTitle1(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void setTitle(String string) {
		// TODO Auto-generated method stub
		
	}
}
