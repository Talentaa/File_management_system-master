/*
 * Created by JFormDesigner on Tue Sep 29 19:45:53 CST 2020
 */

package frame;

import java.awt.event.*;

import common.User;
import javax.swing.*;
import java.awt.*;

/**
 * @author Trump
 */
public class MainFrame extends JFrame {

    private int docDownloadTab = 0;
    private int docUploadTab = 1;
    private int updateUserTab = 0;
    private int deleteUserTab = 1;
    private int insertUserTab = 2;
    private User user = null;

    public MainFrame(User user) {
        this.user = user;
        initComponents();
        setTitle(user.getTitle());
        init(user.getRole());
    }

    private void init(String role){
    //设置权限
        {
            if (role.equals("administrator")){

                uploadDocMenuItem.setEnabled(false);
            }else if (role.equals("browser")){
                userManageMenu.setEnabled(false);
                uploadDocMenuItem.setEnabled(false);

            }else  if (role.equals("operator")){
                userManageMenu.setEnabled(false);

            }
        }

    }

    private void userChangeInfoMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        SelfFrame selfFrame = new SelfFrame(user);
        selfFrame.setVisible(true);
    }

    private void uploadDocMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        FileFrame fileFrame = new FileFrame(user,docUploadTab);
        fileFrame.setVisible(true);

    }

    private void downloadDocMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        FileFrame fileFrame = new FileFrame(user,docDownloadTab);
        fileFrame.setVisible(true);
    }

    private void updateUserMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        UserFrame userFrame = new UserFrame(updateUserTab);
        userFrame.setVisible(true);
    }

    private void deleteUserMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        UserFrame userFrame = new UserFrame(deleteUserTab);
        userFrame.setVisible(true);
    }

    private void insertUserMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
        UserFrame userFrame = new UserFrame(insertUserTab);
        userFrame.setVisible(true);
    }

    private void inserctUserMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar = new JMenuBar();
        userManageMenu = new JMenu();
        updateUserMenuItem = new JMenuItem();
        deleteUserMenuItem = new JMenuItem();
        insertUserMenuItem = new JMenuItem();
        docManageMenu = new JMenu();
        downloadDocMenuItem = new JMenuItem();
        uploadDocMenuItem = new JMenuItem();
        personInfoManageItemMenu = new JMenu();
        userChangeInfoMenuItem = new JMenuItem();

        //======== this ========
        setMinimumSize(new Dimension(720, 455));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar ========
        {
            menuBar.setToolTipText("\u83dc\u5355");

            //======== userManageMenu ========
            {
                userManageMenu.setText("\u7528\u6237\u7ba1\u7406");

                //---- updateUserMenuItem ----
                updateUserMenuItem.setText("\u4fee\u6539\u7528\u6237");
                updateUserMenuItem.addActionListener(e -> updateUserMenuItemActionPerformed(e));
                userManageMenu.add(updateUserMenuItem);

                //---- deleteUserMenuItem ----
                deleteUserMenuItem.setText("\u5220\u9664\u7528\u6237");
                deleteUserMenuItem.addActionListener(e -> deleteUserMenuItemActionPerformed(e));
                userManageMenu.add(deleteUserMenuItem);

                //---- insertUserMenuItem ----
                insertUserMenuItem.setText("\u65b0\u589e\u7528\u6237");
                insertUserMenuItem.addActionListener(e -> insertUserMenuItemActionPerformed(e));
                userManageMenu.add(insertUserMenuItem);
            }
            menuBar.add(userManageMenu);

            //======== docManageMenu ========
            {
                docManageMenu.setText("\u6863\u6848\u7ba1\u7406");

                //---- downloadDocMenuItem ----
                downloadDocMenuItem.setText("\u6863\u6848\u4e0b\u8f7d");
                downloadDocMenuItem.addActionListener(e -> downloadDocMenuItemActionPerformed(e));
                docManageMenu.add(downloadDocMenuItem);

                //---- uploadDocMenuItem ----
                uploadDocMenuItem.setText("\u6863\u6848\u4e0a\u4f20");
                uploadDocMenuItem.addActionListener(e -> uploadDocMenuItemActionPerformed(e));
                docManageMenu.add(uploadDocMenuItem);
            }
            menuBar.add(docManageMenu);

            //======== personInfoManageItemMenu ========
            {
                personInfoManageItemMenu.setText("\u4e2a\u4eba\u4fe1\u606f\u7ba1\u7406");

                //---- userChangeInfoMenuItem ----
                userChangeInfoMenuItem.setText("\u4fe1\u606f\u4fee\u6539");
                userChangeInfoMenuItem.addActionListener(e -> userChangeInfoMenuItemActionPerformed(e));
                personInfoManageItemMenu.add(userChangeInfoMenuItem);
            }
            menuBar.add(personInfoManageItemMenu);
        }
        setJMenuBar(menuBar);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar;
    private JMenu userManageMenu;
    private JMenuItem updateUserMenuItem;
    private JMenuItem deleteUserMenuItem;
    private JMenuItem insertUserMenuItem;
    private JMenu docManageMenu;
    private JMenuItem downloadDocMenuItem;
    private JMenuItem uploadDocMenuItem;
    private JMenu personInfoManageItemMenu;
    private JMenuItem userChangeInfoMenuItem;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


}
