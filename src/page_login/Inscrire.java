package page_login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ConnectionDB.Conix;

public class Inscrire extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
    private JTextField username;
    private JTextField mob;
    private JPasswordField passwordField;
    private JButton btnNewButton;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Inscrire frame = new Inscrire();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    

    public Inscrire() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ayoub\\Desktop\\mar.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,648, 397);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Nouvel utilisateur");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        lblNewUserRegister.setBounds(220, 15,273, 93);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Nom:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(40, 140, 120, 43);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Prenom:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(40, 200, 110, 29);
        contentPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email:");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(40, 260, 124, 36);
        contentPane.add(lblEmailAddress);

        firstname = new JTextField();
        firstname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        firstname.setBounds(120, 140,  160, 40);
        contentPane.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        lastname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lastname.setBounds(120, 200, 160, 40);
        contentPane.add(lastname);
        lastname.setColumns(10);

        email = new JTextField();

        email.setFont(new Font("Tahoma", Font.PLAIN, 20));
        email.setBounds(120, 260, 160, 40);
        contentPane.add(email);
        email.setColumns(10);

        username = new JTextField();
        username.setFont(new Font("Tahoma", Font.PLAIN, 20));
        username.setBounds(450, 140, 160, 40);
        contentPane.add(username);
        username.setColumns(10);

        JLabel lblUsername = new JLabel("utilisateur:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(310, 140, 141, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Mot de passe:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(310,  200, 141, 29);
        contentPane.add(lblPassword);

        JLabel lblMobileNumber = new JLabel(" N° télelphone:");
        lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMobileNumber.setBounds(310, 260, 141, 29);
        contentPane.add(lblMobileNumber);

        mob = new JTextField();
        mob.setFont(new Font("Tahoma", Font.PLAIN, 32));
        mob.setBounds(450, 260,  160, 40);
        contentPane.add(mob);
        mob.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(450, 200,  160, 40);
        contentPane.add(passwordField);

        btnNewButton = new JButton("Inscrire");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = firstname.getText();
                String prenom = lastname.getText();
                String mail = email.getText();
                String Nom_utilisateur = username.getText();
                String Telephone = mob.getText();
                int len = Telephone.length();
                String motpasse = passwordField.getText();

                String msg = "" + firstname;
                msg += " \n";
                if (len != 10) {
                    JOptionPane.showMessageDialog(btnNewButton, "Entrer le numéro de téléphone correcte");
                }

                try {
                	
                	String query = "INSERT INTO Login values('" + nom + "','" + prenom + "','" + Nom_utilisateur + "','" +
                			motpasse + "','" + mail + "','" + Telephone + "')";

                    Statement sta = Conix.getCon().createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "Cela existe déjà");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton,
                            "Binvenue Votre compte est créé avec succès");
                        Login ah = new Login();
                        ah.setVisible(true);
                        
                        
                    }
         
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(233, 320, 160, 30);
        contentPane.add(btnNewButton);
    }
}