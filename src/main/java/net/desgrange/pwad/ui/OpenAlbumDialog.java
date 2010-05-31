package net.desgrange.pwad.ui;

import java.awt.Frame;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;

public class OpenAlbumDialog extends JDialog {
    private static final long serialVersionUID = -1066338781417528922L;

    public OpenAlbumDialog(final Frame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
    }

    public String getLink() {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ResourceBundle bundle = ResourceBundle.getBundle("pwad/l10n/OpenAlbumDialog"); // NOI18N
        setTitle(bundle.getString("OpenAlbumDialog.title")); // NOI18N

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
