/*
 * Created by JFormDesigner on Wed Sep 30 15:50:26 CST 2020
 */

package frame;

import common.DataProcessing;
import common.Doc;
import common.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author Trump
 */
public class FileFrame extends JFrame {
    private User  user;

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
    public FileFrame(User user,int choice) {
        this.user = user;
        initComponents();
        init(user,choice);
    }

    private void init(User user,int choice){
        tabbedPane1.setSelectedIndex(choice);
        if(!user.getRole().equals("operator")){
            tabbedPane1.setEnabledAt(1,false);
        }

        String[][] docData = new String[20][5];
        try {
            Enumeration<Doc> docs = DataProcessing.getAllDocs();
            int row=0;
            while(docs.hasMoreElements()){
                Doc doc =docs.nextElement();
                docData[row][0] = doc.getID();
                docData[row][1] = doc.getCreator();
                docData[row][2] = doc.getTimestamp().toString();
                docData[row][3] = doc.getFilename();
                docData[row][4] = doc.getDescription();
                row++;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        table1.setModel(new DefaultTableModel(docData ,
                new String[] {"\u6863\u6848\u53f7", "\u521b\u5efa\u8005", "\u65f6\u95f4", "\u6587\u4ef6\u540d", "\u63cf\u8ff0"
                }
        ));
    }

    private void uploadExitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
    }

    private void downloadExitButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.dispose();
    }

    private void downloadButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        int selectedRow =table1.getSelectedRow();
        if (selectedRow==-1){
            JOptionPane.showMessageDialog(null,"未选择任何文件");
            return;
        }
        String id = (String) table1.getValueAt(selectedRow,0);
        if (id ==null){
            JOptionPane.showMessageDialog(null,"未选择任何文件");
            return;
        }
        if (user.downloadFile(id)){
            JOptionPane.showMessageDialog(null,"下载成功");
        } else {
            JOptionPane.showMessageDialog(null,"下载失败");
        }

    }

    private void uploadButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String docID = docIDTextField.getText();
        String docDescription = docDescriptionTextField.getText();
        String docName = docNameTextField.getText();
        if (docID==null){
            JOptionPane.showMessageDialog(null,"未输入档案号");
            return;
        }
        if (docDescription ==null){
            JOptionPane.showMessageDialog(null,"未输入文件描述");
            return ;
        }
        if (docName==null){
            JOptionPane.showMessageDialog(null,"未选择文件");
            return;
        }
        try {
            if (DataProcessing.searchDoc(docID)!=null)
            {
                JOptionPane.showMessageDialog(null,"该档案号已经存在");
                return;
            }

           if (user.uploadFile(docName,docID,docDescription))
               JOptionPane.showMessageDialog(null,"成功上传");
           else JOptionPane.showMessageDialog(null,"上传失败");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        init(user,1);
    }

    private void openFileButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        FileDialog fileDialog = new FileDialog(this,"选择上传文件");
        fileDialog.setVisible(true);

        String path = fileDialog.getDirectory()+fileDialog.getFile();
        docNameTextField.setText(path);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        docDownloadPanel = new JPanel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        panel4 = new JPanel();
        downloadButton = new JButton();
        downloadExitButton = new JButton();
        docUploadPanel = new JPanel();
        docIDLabel = new JLabel();
        docDescriptionLabel = new JLabel();
        docNameLabel = new JLabel();
        uploadButton = new JButton();
        uploadExitButton = new JButton();
        docIDTextField = new JTextField();
        docDescriptionTextField = new JTextField();
        docNameTextField = new JTextField();
        openFileButton = new JButton();

        //======== this ========
        setTitle("\u6587\u4ef6\u7ba1\u7406\u754c\u9762");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {
            tabbedPane1.setPreferredSize(new Dimension(400, 260));

            //======== docDownloadPanel ========
            {
                docDownloadPanel.setLayout(new BorderLayout());

                //======== scrollPane2 ========
                {
                    scrollPane2.setPreferredSize(new Dimension(400, 285));

                    //---- table1 ----
                    table1.setModel(new DefaultTableModel());
                    table1.setPreferredSize(new Dimension(400, 250));
                    scrollPane2.setViewportView(table1);
                }
                docDownloadPanel.add(scrollPane2, BorderLayout.CENTER);

                //======== panel4 ========
                {
                    panel4.setPreferredSize(new Dimension(400, 60));
                    panel4.setLayout(null);

                    //---- downloadButton ----
                    downloadButton.setText("\u4e0b\u8f7d");
                    downloadButton.addActionListener(e -> downloadButtonActionPerformed(e));
                    panel4.add(downloadButton);
                    downloadButton.setBounds(new Rectangle(new Point(110, 10), downloadButton.getPreferredSize()));

                    //---- downloadExitButton ----
                    downloadExitButton.setText("\u53d6\u6d88");
                    downloadExitButton.addActionListener(e -> downloadExitButtonActionPerformed(e));
                    panel4.add(downloadExitButton);
                    downloadExitButton.setBounds(new Rectangle(new Point(210, 10), downloadExitButton.getPreferredSize()));
                }
                docDownloadPanel.add(panel4, BorderLayout.SOUTH);
            }
            tabbedPane1.addTab("\u6587\u6863\u4e0b\u8f7d", docDownloadPanel);

            //======== docUploadPanel ========
            {
                docUploadPanel.setLayout(null);

                //---- docIDLabel ----
                docIDLabel.setText("\u6863\u6848\u53f7");
                docUploadPanel.add(docIDLabel);
                docIDLabel.setBounds(new Rectangle(new Point(65, 15), docIDLabel.getPreferredSize()));

                //---- docDescriptionLabel ----
                docDescriptionLabel.setText("\u6863\u6848\u63cf\u8ff0");
                docUploadPanel.add(docDescriptionLabel);
                docDescriptionLabel.setBounds(new Rectangle(new Point(65, 65), docDescriptionLabel.getPreferredSize()));

                //---- docNameLabel ----
                docNameLabel.setText("\u6863\u6848\u6587\u4ef6\u540d");
                docUploadPanel.add(docNameLabel);
                docNameLabel.setBounds(new Rectangle(new Point(65, 120), docNameLabel.getPreferredSize()));

                //---- uploadButton ----
                uploadButton.setText("\u4e0a\u4f20");
                uploadButton.addActionListener(e -> uploadButtonActionPerformed(e));
                docUploadPanel.add(uploadButton);
                uploadButton.setBounds(new Rectangle(new Point(95, 165), uploadButton.getPreferredSize()));

                //---- uploadExitButton ----
                uploadExitButton.setText("\u53d6\u6d88");
                uploadExitButton.addActionListener(e -> uploadExitButtonActionPerformed(e));
                docUploadPanel.add(uploadExitButton);
                uploadExitButton.setBounds(new Rectangle(new Point(215, 165), uploadExitButton.getPreferredSize()));
                docUploadPanel.add(docIDTextField);
                docIDTextField.setBounds(150, 10, 165, docIDTextField.getPreferredSize().height);
                docUploadPanel.add(docDescriptionTextField);
                docDescriptionTextField.setBounds(150, 50, 165, 50);
                docUploadPanel.add(docNameTextField);
                docNameTextField.setBounds(150, 115, 165, docNameTextField.getPreferredSize().height);

                //---- openFileButton ----
                openFileButton.setText("\u6253\u5f00");
                openFileButton.addActionListener(e -> openFileButtonActionPerformed(e));
                docUploadPanel.add(openFileButton);
                openFileButton.setBounds(new Rectangle(new Point(320, 115), openFileButton.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < docUploadPanel.getComponentCount(); i++) {
                        Rectangle bounds = docUploadPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = docUploadPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    docUploadPanel.setMinimumSize(preferredSize);
                    docUploadPanel.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("\u6587\u6863\u4e0a\u4f20", docUploadPanel);

            tabbedPane1.setSelectedIndex(0);
        }
        contentPane.add(tabbedPane1, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel docDownloadPanel;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPanel panel4;
    private JButton downloadButton;
    private JButton downloadExitButton;
    private JPanel docUploadPanel;
    private JLabel docIDLabel;
    private JLabel docDescriptionLabel;
    private JLabel docNameLabel;
    private JButton uploadButton;
    private JButton uploadExitButton;
    private JTextField docIDTextField;
    private JTextField docDescriptionTextField;
    private JTextField docNameTextField;
    private JButton openFileButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
