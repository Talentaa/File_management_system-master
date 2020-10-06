/*
 * Created by JFormDesigner on Tue Sep 29 18:57:06 CST 2020
 */

package frame;

import common.DataProcessing;
import common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * @author Trump
 */
public class LoginFrame extends JFrame {

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    DataProcessing.setConnectToDatabase(
//                            "com.mysql.cj.jdbc.Driver",
//                            "jdbc:mysql://localhost:3306/document?serverTimezone=UTC",
//                            "root",
//                            "123");
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }

    public LoginFrame() {
        initComponents();
        try {
            DataProcessing.setConnectToDatabase(
                    "com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/document?serverTimezone=UTC",
                    "root",
                    "123");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loginButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String username = this.usernameTextField.getText();
        String password = new String(this.passwordTextField.getPassword());
        if(username==null||"".equals(username.trim())){
            JOptionPane.showMessageDialog(null,"未输入用户名！");
            return;
        }

        if (password==null||"".equals(password.trim())){
            JOptionPane.showMessageDialog(null,"未输入密码！");
            return;
        }

        try {
            User user = DataProcessing.searchUser(username, password);
            if (user == null ) {
                JOptionPane.showMessageDialog(null, "用户名或密码错误！");
            }else{
                this.dispose();
                MainFrame mainFrame = new MainFrame(user);
                mainFrame.setVisible(true);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        usernameTextField = new JTextField();
        loginButton = new JButton();
        exitButton = new JButton();
        passwordTextField = new JPasswordField();

        //======== this ========
        setTitle("\u767b\u5f55");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setResizable(false);
        setMinimumSize(new Dimension(280, 200));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(280, 170));
            panel1.setLayout(null);

            //---- usernameLabel ----
            usernameLabel.setText("\u7528\u6237\u540d");
            panel1.add(usernameLabel);
            usernameLabel.setBounds(new Rectangle(new Point(40, 30), usernameLabel.getPreferredSize()));

            //---- passwordLabel ----
            passwordLabel.setText("\u5bc6\u7801");
            panel1.add(passwordLabel);
            passwordLabel.setBounds(new Rectangle(new Point(40, 80), passwordLabel.getPreferredSize()));
            panel1.add(usernameTextField);
            usernameTextField.setBounds(100, 25, 140, usernameTextField.getPreferredSize().height);

            //---- loginButton ----
            loginButton.setText("\u786e\u5b9a");
            loginButton.addActionListener(e -> loginButtonActionPerformed(e));
            panel1.add(loginButton);
            loginButton.setBounds(new Rectangle(new Point(50, 120), loginButton.getPreferredSize()));

            //---- exitButton ----
            exitButton.setText("\u9000\u51fa");
            exitButton.addActionListener(e -> exitButtonActionPerformed(e));
            panel1.add(exitButton);
            exitButton.setBounds(new Rectangle(new Point(145, 120), exitButton.getPreferredSize()));
            panel1.add(passwordTextField);
            passwordTextField.setBounds(100, 75, 140, passwordTextField.getPreferredSize().height);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JButton exitButton;
    private JPasswordField passwordTextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
