/*
 * Created by JFormDesigner on Wed Sep 30 08:37:22 CST 2020
 */

package frame;

import common.DataProcessing;
import common.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Trump
 */
public class SelfFrame extends JFrame {
    private User user = null;

    public SelfFrame(User user) {
        this.user = user;
        initComponents();
        init();
    }

    private void init(){
        usernameTextField.setText(user.getName());
        userTextField.setText(user.getRole());
    }


    private void changeButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String oldPassword = oldPasswordTextField.getText();
        if(!oldPassword.equals(user.getPassword())){
            JOptionPane.showMessageDialog(null,"原口令错误");
            return ;
        }else{
            String newPassword = new String (this.newPasswordField.getPassword());
            if (!newPassword.equals( new String (this.confirmPasswordField.getPassword() ) ) ){
                JOptionPane.showMessageDialog(null,"两次密码不一致!");
                return;
            }else{
                try {
                    user.setPassword(newPassword);
                    DataProcessing.updateUser(user.getName(),newPassword,user.getRole());
                    JOptionPane.showMessageDialog(null,"修改成功");
                    this.dispose();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
    }

    private void usernameTextFieldActionPerformed(ActionEvent e) {
        // TODO add your code here
    }
    

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        usernameLabel = new JLabel();
        oldPasswordLabel = new JLabel();
        newPasswordLabel = new JLabel();
        confirmPasswordLabel = new JLabel();
        userLabel = new JLabel();
        usernameTextField = new JTextField();
        oldPasswordTextField = new JTextField();
        newPasswordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        changeButton = new JButton();
        exitButton = new JButton();
        userTextField = new JTextField();

        //======== this ========
        setMinimumSize(new Dimension(355, 310));
        setTitle("\u4e2a\u4eba\u4fe1\u606f\u7ba1\u7406");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setMinimumSize(new Dimension(355, 280));
            panel1.setLayout(null);

            //---- usernameLabel ----
            usernameLabel.setText("\u7528\u6237\u540d");
            panel1.add(usernameLabel);
            usernameLabel.setBounds(new Rectangle(new Point(40, 25), usernameLabel.getPreferredSize()));

            //---- oldPasswordLabel ----
            oldPasswordLabel.setText("\u539f\u53e3\u4ee4");
            panel1.add(oldPasswordLabel);
            oldPasswordLabel.setBounds(new Rectangle(new Point(40, 65), oldPasswordLabel.getPreferredSize()));

            //---- newPasswordLabel ----
            newPasswordLabel.setText("\u65b0\u53e3\u4ee4");
            panel1.add(newPasswordLabel);
            newPasswordLabel.setBounds(new Rectangle(new Point(40, 105), newPasswordLabel.getPreferredSize()));

            //---- confirmPasswordLabel ----
            confirmPasswordLabel.setText("\u786e\u8ba4\u65b0\u53e3\u4ee4");
            panel1.add(confirmPasswordLabel);
            confirmPasswordLabel.setBounds(new Rectangle(new Point(40, 145), confirmPasswordLabel.getPreferredSize()));

            //---- userLabel ----
            userLabel.setText("\u89d2\u8272");
            panel1.add(userLabel);
            userLabel.setBounds(new Rectangle(new Point(40, 185), userLabel.getPreferredSize()));

            //---- usernameTextField ----
            usernameTextField.setEditable(false);
            usernameTextField.addActionListener(e -> {
			usernameTextFieldActionPerformed(e);
			usernameTextFieldActionPerformed(e);
		});
            panel1.add(usernameTextField);
            usernameTextField.setBounds(125, 20, 155, usernameTextField.getPreferredSize().height);
            panel1.add(oldPasswordTextField);
            oldPasswordTextField.setBounds(125, 60, 155, oldPasswordTextField.getPreferredSize().height);
            panel1.add(newPasswordField);
            newPasswordField.setBounds(125, 100, 155, newPasswordField.getPreferredSize().height);
            panel1.add(confirmPasswordField);
            confirmPasswordField.setBounds(125, 140, 155, confirmPasswordField.getPreferredSize().height);

            //---- changeButton ----
            changeButton.setText("\u4fee\u6539");
            changeButton.addActionListener(e -> changeButtonActionPerformed(e));
            panel1.add(changeButton);
            changeButton.setBounds(new Rectangle(new Point(65, 225), changeButton.getPreferredSize()));

            //---- exitButton ----
            exitButton.setText("\u8fd4\u56de");
            exitButton.addActionListener(e -> exitButtonActionPerformed(e));
            panel1.add(exitButton);
            exitButton.setBounds(new Rectangle(new Point(175, 225), exitButton.getPreferredSize()));

            //---- userTextField ----
            userTextField.setEditable(false);
            panel1.add(userTextField);
            userTextField.setBounds(125, 180, 155, userTextField.getPreferredSize().height);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel usernameLabel;
    private JLabel oldPasswordLabel;
    private JLabel newPasswordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel userLabel;
    private JTextField usernameTextField;
    private JTextField oldPasswordTextField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton changeButton;
    private JButton exitButton;
    private JTextField userTextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
