package page_login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import ConnectionDB.Conix;
import gestionnaire.*;

import clients.*;


public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton btnNewButton1;
    private JLabel label;
    private JPanel contentPane;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
   
        public Login() {
        	
            setBounds(100, 100,648, 397);
            setResizable(false);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel lblNewLabel = new JLabel("connexion");
            lblNewLabel.setForeground(Color.BLACK);
            lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
            lblNewLabel.setBounds(220, 13, 273, 93);
            contentPane.add(lblNewLabel);

            textField = new JTextField();
            textField.setFont(new Font("Tahoma", Font.PLAIN, 28));
            textField.setBounds(230, 120, 180, 40);
            contentPane.add(textField);
            textField.setColumns(10);

            passwordField = new JPasswordField();
            passwordField.setFont(new Font("Tahoma", Font.PLAIN, 28));
            passwordField.setBounds(230, 190, 180, 40);
            contentPane.add(passwordField);

            JLabel lblUsername = new JLabel("Nom:");
            lblUsername.setBackground(Color.BLACK);
            lblUsername.setForeground(Color.BLACK);
            lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 28));
            lblUsername.setBounds(80, 110, 90, 52);
            contentPane.add(lblUsername);

            JLabel lblPassword = new JLabel("Motpassse:");
            lblPassword.setForeground(Color.BLACK);
            lblPassword.setBackground(Color.CYAN);
            lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 28));
            lblPassword.setBounds(80, 180, 160, 52);
            contentPane.add(lblPassword);

            btnNewButton = new JButton("connexion");
            btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
            btnNewButton.setBounds(240, 260, 160, 30);
            btnNewButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String userName = textField.getText();
                    String password = passwordField.getText();
                    try {

                        PreparedStatement st = Conix.getCon().prepareStatement("Select Nom_utilisateur, motpasse from Login where Nom_utilisateur=?");

                        st.setString(1, userName);
                      
                        ResultSet rs = st.executeQuery();
                        if (rs.next()) {
                            dispose();
                        	Client ah = new Client();
                            ah.setTitle("Bienvenue");
                            ah.setVisible(true);
                          
                        } else if(userName.equals("admin") && password.equals("admin") ){
                        
                        	dispose();
                        Librairie ah = new Librairie();
                           
                            ah.setVisible(true);
                        	
                        }else
                        
                        {
                            JOptionPane.showMessageDialog(btnNewButton, "Nom d'utilisateur incorrect / mot de passe");
                           
                        }
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    } 
                }
            });
            
            
            
            btnNewButton1 = new JButton("Inscrire");
            btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 20));
            btnNewButton1.setBounds(240, 300, 160, 30);
           
            btnNewButton1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
               
                            Inscrire ah = new Inscrire();
                            ah.setTitle("Bienvenue");
                            ah.setVisible(true);
                            
        
                          
                             }});
            
            contentPane.add(btnNewButton1);


            contentPane.add(btnNewButton);

            label = new JLabel("");
            label.setBounds(0, 0, 1008, 562);
            contentPane.add(label);
            
        }
        

    }





























