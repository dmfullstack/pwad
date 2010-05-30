package net.desgrange.pwad.ui;

import java.util.ResourceBundle;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;

public class MainForm extends JFrame {
    private static final long serialVersionUID = 4313821019914508450L;

    public MainForm() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ResourceBundle bundle = ResourceBundle.getBundle("pwad/l10n/MainForm"); // NOI18N
        setTitle(bundle.getString("MainForm.title")); // NOI18N
        setName("Form"); // NOI18N

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
