package javaswingdev.main;

import java.awt.Component;
import javaswingdev.GoogleMaterialDesignIcon;
import javaswingdev.form.admin.Form_Dashboard_Admin;
import javaswingdev.form.admin.Form_Transaksi_Admin;
import javaswingdev.form.admin.Form_Data_Pupuk;
import javaswingdev.login.Login;
import javaswingdev.loginUI.main.LoginMain;
import javaswingdev.form.user.Form_Dashboard_User;
import javaswingdev.form.user.Form_Transaksi_User;
import javaswingdev.form.Form_Dashboard;
import javaswingdev.form.Form_Email;
import javaswingdev.form.Form_Empty;
import javaswingdev.form.admin.Data_Transaksi_Admin;
import javaswingdev.form.admin.Form_Restock_Pupuk;
import javaswingdev.form.admin.Form_Stock_Pupuk;
import javaswingdev.form.admin.Form_User;
import javaswingdev.form.admin.Data_bulanan;
import javaswingdev.menu.EventMenuSelected;
import javaswingdev.menu.ModelMenuItem;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

    private static Main main;
    private String userType;

    public Main() {
        initComponents();

    }

    public void setUserType(String userType) {
        this.userType = userType;
        System.out.println("User Type di Main: " + this.userType); // Debugging
        init();
    }

    private void init() {
        main = this;
        titleBar.initJFram(this);
//        menu.removeAll();

        if ("user".equals(userType)) {
            // Menu untuk User
            menu.addTitle("MAIN");
            menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.DASHBOARD, "Dashboard"));
            menu.addTitle("TRANSAKSI");
            menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.PERM_CONTACT_CALENDAR, "Transaksi"));
        } else if ("admin".equals(userType)) {
            // Menu untuk Admin
            menu.addTitle("MAIN");
            menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.DASHBOARD, "Dashboard"));

            menu.addTitle("TRANSAKSI");
            menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.PERM_CONTACT_CALENDAR, "Transaksi", "Penjualan", "Data Penjualan"));

            menu.addTitle("MANAGEMENT USER");
            menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.WHATSHOT, "Data User"));

            menu.addTitle("INVENTORY");
            menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.STORE, "Data Barang Pupuk"));
            menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.STORAGE, "Stock", "Stock", "Restock")); // Tambahan menu Stock

            menu.addTitle("LAPORAN");
            menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.ASSESSMENT, "Data_Laporan"));

        }

        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int index, int indexSubMenu) {
                if ("user".equals(userType)) {
                    // Menu untuk User
                    if (index == 0 && indexSubMenu == 0) {
                        showForm(new Form_Dashboard_User());
                    } else if (index == 1 && indexSubMenu == 0) {
                        showForm(new Form_Transaksi_User("User"));
                    } else if (index == 2 && indexSubMenu == 0) { // Profile untuk Admin
                        showForm(new Form_Empty(index + " " + indexSubMenu));
                    } else if (index == 3 && indexSubMenu == 0) { // Logout
                        int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            setVisible(false); // Sembunyikan form utama
                            LoginMain loginForm = new LoginMain();
                            loginForm.setLocationRelativeTo(null); // Agar form Login muncul di tengah layar
                            loginForm.setVisible(true);
                            dispose(); // Hancurkan form utama setelah form Login tampil
                        }
                    } else if (index != menu.getComponentCount() - 2) {
                        System.out.println("Menampilkan Form_Empty");
                        showForm(new Form_Empty(index + " " + indexSubMenu));
                    }

                } else if ("admin".equals(userType)) {
                    // Menu untuk Admin
                    if (index == 0 && indexSubMenu == 0) {
                        showForm(new Form_Dashboard_Admin());
                    } else if (index == 1 && indexSubMenu == 1) {
                        showForm(new Form_Transaksi_Admin("Admin"));
                    } else if (index == 1 && indexSubMenu == 2) {
                        showForm(new Data_Transaksi_Admin("Admin"));
                    } else if (index == 2 && indexSubMenu == 0) {
                        showForm(new Form_User("User"));
                    } else if (index == 3 && indexSubMenu == 0) {
                        showForm(new Form_Data_Pupuk("Data Barang Pupuk"));
                    } else if (index == 4 && indexSubMenu == 1) { // Stock dipindah ke submenu 1
                        showForm(new Form_Stock_Pupuk("Stock"));
                    } else if (index == 4 && indexSubMenu == 2) { // Restock dipindah ke submenu 2
                        showForm(new Form_Restock_Pupuk("Restock Pupuk"));
                    } else if (index == 5 && indexSubMenu == 0) { // Laporan turun ke index 5
                        showForm(new Data_bulanan("Laporan"));
                    } else if (index == 6 && indexSubMenu == 0) { // Profile turun ke index 6
                        showForm(new Form_Empty("Profile"));
                    } else if (index == 7 && indexSubMenu == 0) { // Logout turun ke index 7
                        int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            setVisible(false); // Sembunyikan form utama
                            LoginMain loginForm = new LoginMain();
                            loginForm.setLocationRelativeTo(null); // Agar form Login muncul di tengah layar
                            loginForm.setVisible(true);
                            dispose(); // Hancurkan form utama setelah form Login tampil
                        }
                    } else if (index != menu.getComponentCount() - 2) {
                        System.out.println("Menampilkan Form_Empty");
                        showForm(new Form_Empty(index + " " + indexSubMenu));
                    }

                }
            }
        });

        // Tambahkan Profile dan Logout di bagian bawah menu
        menu.addTitle("PENGATURAN");
        menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.ACCOUNT_CIRCLE, "Profile"));
        menu.addMenuItem(new ModelMenuItem(GoogleMaterialDesignIcon.EXIT_TO_APP, "Logout"));

//        menu.revalidate();
//        menu.repaint();
        menu.setSelectedIndex(0, 0);
    }

    public void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }

    public static Main getMain() {
        return main;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        menu = new javaswingdev.menu.Menu();
        titleBar = new javaswingdev.swing.titlebar.TitleBar();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background.setBackground(new java.awt.Color(245, 245, 245));

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(titleBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                .addContainerGap())
        );

        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel body;
    private javaswingdev.menu.Menu menu;
    private javax.swing.JPanel panelMenu;
    private javaswingdev.swing.titlebar.TitleBar titleBar;
    // End of variables declaration//GEN-END:variables
}
