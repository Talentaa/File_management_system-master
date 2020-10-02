/*
 * Created by JFormDesigner on Wed Sep 30 16:37:22 CST 2020
 */

package frame;

import javax.swing.table.*;
import common.DataProcessing;
import common.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.swing.*;

/**
 * @author Trump
 */
public class UserFrame extends JFrame {
    private int choice ;
    public UserFrame(int choice) {
        this.choice = choice;
        initComponents();
        init(choice);
    }
    private void init(int choice){
        userTappedPane.setSelectedIndex(choice);
        try {
            updateUserNameComboBox.removeAllItems();
            for(Enumeration<User> users = DataProcessing.getAllUser(); users.hasMoreElements();){
                User user = users.nextElement();
                if (!user.getRole().equals("administrator"))
                    updateUserNameComboBox.addItem(user.getName());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String[][] userData = new String[20][3];
        try {
            Enumeration<User> users = DataProcessing.getAllUser();
            int row = 0;
            while(users.hasMoreElements()){
                User user = users.nextElement();
                userData[row][0] = user.getName();
                userData[row][1] = user.getPassword();
                userData[row][2] = user.getRole();
                row++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        deleteUserTable.setModel(new DefaultTableModel( userData , new String[] {"\u7528\u6237\u540d", "\u53e3\u4ee4", "\u89d2\u8272"}));

    }



    private void updateUserExitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
    }

    private void updateUserConfirmButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String username = updateUserNameComboBox.getSelectedItem().toString();
        String password = new String(updatePasswordTextField.getPassword()) ;
        String role = updateUserComboBox.getSelectedItem().toString();

        if (username==null||role ==null){
            JOptionPane.showMessageDialog(null,"异常");
            return ;
        }

        if (password.equals("")){
            JOptionPane.showMessageDialog(null,"没输入密码");
            return ;
        }

        int value = JOptionPane.showConfirmDialog(null,"确定修改吗");
        if (value ==0){
            try {
                if (DataProcessing.updateUser(username,password,role)){
                    JOptionPane.showMessageDialog(null,"修改成功");
                    init(0);
                }else {
                    JOptionPane.showMessageDialog(null,"修改失败");
                }
                return ;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else if (value==1){
            return ;
        }



    }

    private void insertUserExitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
    }

    private void insertUserConfirmButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String userName = insertUserNameTextField.getText();
        String password = new String ( insertPasswordTextField.getPassword() );
        String role = insertUserComboBox.getSelectedItem().toString();

        try {
            if (DataProcessing.searchUser(userName)!=null){
                JOptionPane.showMessageDialog(null,"已存在该用户名");
                return;
            }

            if (userName==null||password.equals("")){
                JOptionPane.showMessageDialog(null,"未输入用户名或密码");
                return;
            }

            if(role==null){
                JOptionPane.showMessageDialog(null,"异常");
                return;
            }

           int value= JOptionPane.showConfirmDialog(null,"确定添加用户吗！","",2);
            if (value ==0){
                if (DataProcessing.insertUser( userName, password, role )){
                    init(2);
                    JOptionPane.showMessageDialog(null,"添加成功");
                }else
                    JOptionPane.showMessageDialog(null,"添加失败");
                return;
            }else if (value==1){
                return;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void deleteUserExitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
    }

    private void deleteUserButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        int selectedRow = deleteUserTable.getSelectedRow();

        if (selectedRow==-1){
            JOptionPane.showMessageDialog(null,"未选择用户");
            return;
        }else {
            String role = (String) deleteUserTable.getValueAt(selectedRow,2);
            if (role==null){
                JOptionPane.showMessageDialog(null,"未选择用户");
                return ;
            }else if (role.equals("administrator")){
                JOptionPane.showMessageDialog(null,"你没有权限");
                return;
            }
        }

        String userName = (String) deleteUserTable.getValueAt(selectedRow,0);

        int value = JOptionPane.showConfirmDialog(null,"确定要删除用户吗？","",2);
        if (value == 0){
            try {
                if (DataProcessing.deleteUser(userName)){
                    init(1);
                    JOptionPane.showMessageDialog(null,"删除成功");
                }
                else {
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
                return;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else if (value == 1){
            return;
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        userTappedPane = new JTabbedPane();
        updateUserPanel = new JPanel();
        updateUserNameLabel = new JLabel();
        updatePasswordLabel = new JLabel();
        updateUserLabel = new JLabel();
        updateUserNameComboBox = new JComboBox();
        updatePasswordTextField = new JPasswordField();
        updateUserComboBox = new JComboBox<>();
        updateUserConfirmButton = new JButton();
        updateUserExitButton = new JButton();
        deleteUserPanel = new JPanel();
        deleteUserScrollPane = new JScrollPane();
        deleteUserTable = new JTable();
        subDeleteUserPanel = new JPanel();
        deleteUserButton = new JButton();
        deleteUserExitButton = new JButton();
        insertUserPanel = new JPanel();
        insertUserNameLabel = new JLabel();
        insertPasswordLabel = new JLabel();
        insertUserLabel = new JLabel();
        insertUserComboBox = new JComboBox<>();
        insertPasswordTextField = new JPasswordField();
        insertUserNameTextField = new JTextField();
        insertUserExitButton = new JButton();
        insertUserConfirmButton = new JButton();

        //======== this ========
        setTitle("\u7528\u6237\u7ba1\u7406\u754c\u9762");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== userTappedPane ========
        {
            userTappedPane.setPreferredSize(new Dimension(360, 260));

            //======== updateUserPanel ========
            {
                updateUserPanel.setLayout(null);

                //---- updateUserNameLabel ----
                updateUserNameLabel.setText("\u7528\u6237\u540d");
                updateUserPanel.add(updateUserNameLabel);
                updateUserNameLabel.setBounds(new Rectangle(new Point(70, 25), updateUserNameLabel.getPreferredSize()));

                //---- updatePasswordLabel ----
                updatePasswordLabel.setText("\u53e3\u4ee4");
                updateUserPanel.add(updatePasswordLabel);
                updatePasswordLabel.setBounds(new Rectangle(new Point(70, 75), updatePasswordLabel.getPreferredSize()));

                //---- updateUserLabel ----
                updateUserLabel.setText("\u89d2\u8272");
                updateUserPanel.add(updateUserLabel);
                updateUserLabel.setBounds(new Rectangle(new Point(70, 125), updateUserLabel.getPreferredSize()));
                updateUserPanel.add(updateUserNameComboBox);
                updateUserNameComboBox.setBounds(140, 15, 160, updateUserNameComboBox.getPreferredSize().height);
                updateUserPanel.add(updatePasswordTextField);
                updatePasswordTextField.setBounds(140, 70, 160, updatePasswordTextField.getPreferredSize().height);

                //---- updateUserComboBox ----
                updateUserComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "browser",
                    "operator",
                    "administrator"
                }));
                updateUserPanel.add(updateUserComboBox);
                updateUserComboBox.setBounds(140, 120, 160, updateUserComboBox.getPreferredSize().height);

                //---- updateUserConfirmButton ----
                updateUserConfirmButton.setText("\u4fee\u6539");
                updateUserConfirmButton.addActionListener(e -> updateUserConfirmButtonActionPerformed(e));
                updateUserPanel.add(updateUserConfirmButton);
                updateUserConfirmButton.setBounds(new Rectangle(new Point(80, 170), updateUserConfirmButton.getPreferredSize()));

                //---- updateUserExitButton ----
                updateUserExitButton.setText("\u53d6\u6d88");
                updateUserExitButton.addActionListener(e -> updateUserExitButtonActionPerformed(e));
                updateUserPanel.add(updateUserExitButton);
                updateUserExitButton.setBounds(new Rectangle(new Point(200, 170), updateUserExitButton.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < updateUserPanel.getComponentCount(); i++) {
                        Rectangle bounds = updateUserPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = updateUserPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    updateUserPanel.setMinimumSize(preferredSize);
                    updateUserPanel.setPreferredSize(preferredSize);
                }
            }
            userTappedPane.addTab("\u4fee\u6539\u7528\u6237", updateUserPanel);

            //======== deleteUserPanel ========
            {
                deleteUserPanel.setLayout(new BorderLayout());

                //======== deleteUserScrollPane ========
                {

                    //---- deleteUserTable ----
                    deleteUserTable.setModel(new DefaultTableModel());
                    deleteUserScrollPane.setViewportView(deleteUserTable);
                }
                deleteUserPanel.add(deleteUserScrollPane, BorderLayout.CENTER);

                //======== subDeleteUserPanel ========
                {
                    subDeleteUserPanel.setPreferredSize(new Dimension(200, 90));
                    subDeleteUserPanel.setLayout(null);

                    //---- deleteUserButton ----
                    deleteUserButton.setText("\u5220\u9664");
                    deleteUserButton.addActionListener(e -> deleteUserButtonActionPerformed(e));
                    subDeleteUserPanel.add(deleteUserButton);
                    deleteUserButton.setBounds(80, 32, 78, 30);

                    //---- deleteUserExitButton ----
                    deleteUserExitButton.setText("\u53d6\u6d88");
                    deleteUserExitButton.addActionListener(e -> {
			updateUserExitButtonActionPerformed(e);
			deleteUserExitButtonActionPerformed(e);
		});
                    subDeleteUserPanel.add(deleteUserExitButton);
                    deleteUserExitButton.setBounds(200, 32, 78, 30);
                }
                deleteUserPanel.add(subDeleteUserPanel, BorderLayout.SOUTH);
            }
            userTappedPane.addTab("\u5220\u9664\u7528\u6237", deleteUserPanel);

            //======== insertUserPanel ========
            {
                insertUserPanel.setLayout(null);

                //---- insertUserNameLabel ----
                insertUserNameLabel.setText("\u7528\u6237\u540d");
                insertUserPanel.add(insertUserNameLabel);
                insertUserNameLabel.setBounds(70, 25, 36, 17);

                //---- insertPasswordLabel ----
                insertPasswordLabel.setText("\u53e3\u4ee4");
                insertUserPanel.add(insertPasswordLabel);
                insertPasswordLabel.setBounds(70, 75, 24, 17);

                //---- insertUserLabel ----
                insertUserLabel.setText("\u89d2\u8272");
                insertUserPanel.add(insertUserLabel);
                insertUserLabel.setBounds(70, 125, 24, 17);

                //---- insertUserComboBox ----
                insertUserComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "browser",
                    "operator",
                    "administrator"
                }));
                insertUserPanel.add(insertUserComboBox);
                insertUserComboBox.setBounds(140, 120, 160, 30);
                insertUserPanel.add(insertPasswordTextField);
                insertPasswordTextField.setBounds(140, 70, 160, 30);
                insertUserPanel.add(insertUserNameTextField);
                insertUserNameTextField.setBounds(140, 15, 160, insertUserNameTextField.getPreferredSize().height);

                //---- insertUserExitButton ----
                insertUserExitButton.setText("\u53d6\u6d88");
                insertUserExitButton.addActionListener(e -> insertUserExitButtonActionPerformed(e));
                insertUserPanel.add(insertUserExitButton);
                insertUserExitButton.setBounds(200, 170, 78, 30);

                //---- insertUserConfirmButton ----
                insertUserConfirmButton.setText("\u589e\u52a0");
                insertUserConfirmButton.addActionListener(e -> insertUserConfirmButtonActionPerformed(e));
                insertUserPanel.add(insertUserConfirmButton);
                insertUserConfirmButton.setBounds(80, 170, 78, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < insertUserPanel.getComponentCount(); i++) {
                        Rectangle bounds = insertUserPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = insertUserPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    insertUserPanel.setMinimumSize(preferredSize);
                    insertUserPanel.setPreferredSize(preferredSize);
                }
            }
            userTappedPane.addTab("\u65b0\u589e\u7528\u6237", insertUserPanel);

            userTappedPane.setSelectedIndex(1);
        }
        contentPane.add(userTappedPane, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane userTappedPane;
    private JPanel updateUserPanel;
    private JLabel updateUserNameLabel;
    private JLabel updatePasswordLabel;
    private JLabel updateUserLabel;
    private JComboBox updateUserNameComboBox;
    private JPasswordField updatePasswordTextField;
    private JComboBox<String> updateUserComboBox;
    private JButton updateUserConfirmButton;
    private JButton updateUserExitButton;
    private JPanel deleteUserPanel;
    private JScrollPane deleteUserScrollPane;
    private JTable deleteUserTable;
    private JPanel subDeleteUserPanel;
    private JButton deleteUserButton;
    private JButton deleteUserExitButton;
    private JPanel insertUserPanel;
    private JLabel insertUserNameLabel;
    private JLabel insertPasswordLabel;
    private JLabel insertUserLabel;
    private JComboBox<String> insertUserComboBox;
    private JPasswordField insertPasswordTextField;
    private JTextField insertUserNameTextField;
    private JButton insertUserExitButton;
    private JButton insertUserConfirmButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
