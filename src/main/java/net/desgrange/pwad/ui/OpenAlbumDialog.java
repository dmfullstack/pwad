package net.desgrange.pwad.ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
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

        messageLabel = new JLabel();
        invitationLinkLabel = new JLabel();
        invitationLinkField = new JTextField();
        cancelButton = new JButton();
        okButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ResourceBundle bundle = ResourceBundle.getBundle("pwad/l10n/OpenAlbumDialog"); // NOI18N
        setTitle(bundle.getString("OpenAlbumDialog.title")); // NOI18N
        setMinimumSize(new Dimension(474, 97));

        messageLabel.setText(bundle.getString("OpenAlbumDialog.messageLabel.text")); // NOI18N
        messageLabel.setName("messageLabel"); // NOI18N

        invitationLinkLabel.setLabelFor(invitationLinkField);
        invitationLinkLabel.setText(bundle.getString("OpenAlbumDialog.invitationLinkLabel.text")); // NOI18N
        invitationLinkLabel.setName("invitationLinkLabel"); // NOI18N

        invitationLinkField.setText(bundle.getString("OpenAlbumDialog.invitationLinkField.text")); // NOI18N
        invitationLinkField.setName("invitationLinkField"); // NOI18N

        cancelButton.setText(bundle.getString("OpenAlbumDialog.cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N

        okButton.setText(bundle.getString("OpenAlbumDialog.okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(messageLabel, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(invitationLinkLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(invitationLinkField, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(okButton)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(invitationLinkLabel)
                    .addComponent(invitationLinkField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton cancelButton;
    private JTextField invitationLinkField;
    private JLabel invitationLinkLabel;
    private JLabel messageLabel;
    private JButton okButton;
    // End of variables declaration//GEN-END:variables

}
