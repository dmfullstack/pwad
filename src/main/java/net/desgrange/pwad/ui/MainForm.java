/**
 *
 * Copyright 2010 Laurent Desgrange
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package net.desgrange.pwad.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import net.desgrange.pwad.model.Album;
import net.desgrange.pwad.service.EnvironmentService;
import net.desgrange.pwad.service.PwadService;
import net.desgrange.pwad.service.exceptions.BadUrlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MainForm extends JFrame {
    private static final long serialVersionUID = 4313821019914508450L;
    private transient final Logger logger = LoggerFactory.getLogger(getClass());
    private transient EnvironmentService environmentService;
    private transient PwadService pwadService;
    private String selectTitle;
    private String selectText;
    private String openInvitationErrorTitle;
    private String openInvitationErrorMessage;

    public void init() {
        final Image iconSvg = new ImageIcon(getClass().getResource("/pwad/images/pwad-logo.svg")).getImage();
        final Image icon64 = new ImageIcon(getClass().getResource("/pwad/images/pwad-logo_64.png")).getImage();
        final Image icon32 = new ImageIcon(getClass().getResource("/pwad/images/pwad-logo_32.png")).getImage();
        final Image icon24 = new ImageIcon(getClass().getResource("/pwad/images/pwad-logo_24.png")).getImage();
        final Image icon16 = new ImageIcon(getClass().getResource("/pwad/images/pwad-logo_16.png")).getImage();
        setIconImages(Arrays.asList(iconSvg, icon64, icon32, icon24, icon16));
        initComponents();
        setLocationRelativeTo(null);
    }

    public void displayAboutDialog() {
        final AboutDialog dialog = new AboutDialog(this, environmentService);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public void quit() {
        dispose();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        messageLabel = new JLabel();
        invitationLinkLabel = new JLabel();
        invitationLinkField = new JTextField();
        downloadButton = new JButton();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        aboutMenu = new JMenuItem();
        separator1 = new Separator();
        quitMenu = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final ResourceBundle bundle = ResourceBundle.getBundle("pwad/l10n/MainForm"); // NOI18N
        setTitle(bundle.getString("MainForm.title")); // NOI18N
        setName("pwad"); // NOI18N

        messageLabel.setText(bundle.getString("MainForm.messageLabel.text")); // NOI18N
        messageLabel.setName("messageLabel"); // NOI18N

        invitationLinkLabel.setLabelFor(invitationLinkField);
        invitationLinkLabel.setText(bundle.getString("MainForm.invitationLinkLabel.text")); // NOI18N
        invitationLinkLabel.setName("invitationLinkLabel"); // NOI18N

        invitationLinkField.setText(bundle.getString("MainForm.invitationLinkField.text")); // NOI18N
        invitationLinkField.setName("invitationLinkField"); // NOI18N

        downloadButton.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/MainForm").getString("MainForm.downloadButton.text"), new Object[] {})); // NOI18N
        downloadButton.setName("downloadButton"); // NOI18N
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });
        selectTitle = ResourceBundle.getBundle("pwad/l10n/MainForm").getString("MainForm.downloadButton.selectTitle");
        selectText = ResourceBundle.getBundle("pwad/l10n/MainForm").getString("MainForm.downloadButton.selectText");
        openInvitationErrorTitle = ResourceBundle.getBundle("pwad/l10n/MainForm").getString("MainForm.openMenuItem.errorTitle");
        openInvitationErrorMessage = ResourceBundle.getBundle("pwad/l10n/MainForm").getString("MainForm.openMenuItem.errorMessage");

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(bundle.getString("MainForm.fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        aboutMenu.setText(bundle.getString("MainForm.aboutMenu.text")); // NOI18N
        aboutMenu.setName("aboutMenu"); // NOI18N
        aboutMenu.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                aboutMenuActionPerformed(evt);
            }
        });
        fileMenu.add(aboutMenu);

        separator1.setName("separator1"); // NOI18N
        fileMenu.add(separator1);

        quitMenu.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/MainForm").getString("MainForm.quitMenu.text"), new Object[] {})); // NOI18N
        quitMenu.setName("quitMenu"); // NOI18N
        quitMenu.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                quitMenuActionPerformed(evt);
            }
        });
        fileMenu.add(quitMenu);

        menuBar.add(fileMenu);

        if (!environmentService.isMacOs()) {

            setJMenuBar(menuBar);
        }

        final GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(messageLabel, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(downloadButton)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(invitationLinkLabel)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(invitationLinkField, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)))
                                                .addGap(20, 20, 20))))
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
                                .addComponent(downloadButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aboutMenuActionPerformed(final ActionEvent evt) {// GEN-FIRST:event_aboutPwadActionPerformed
        logger.trace("{}", evt);
        displayAboutDialog();
    }// GEN-LAST:event_aboutPwadActionPerformed

    private void quitMenuActionPerformed(final ActionEvent evt) {// GEN-FIRST:event_quitMenuActionPerformed
        logger.trace("{}", evt);
        quit();
    }// GEN-LAST:event_quitMenuActionPerformed

    private void downloadButtonActionPerformed(final ActionEvent evt) {// GEN-FIRST:event_downloadButtonActionPerformed
        logger.trace("{}", evt);
        Album album = null;

        try {
            album = pwadService.getAlbumByInvitationLink(invitationLinkField.getText());
        } catch (final BadUrlException e) {
            JOptionPane.showMessageDialog(this, openInvitationErrorMessage, openInvitationErrorTitle, JOptionPane.WARNING_MESSAGE);
            return;
        }

        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(selectTitle);
        fileChooser.setApproveButtonText(selectText);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        final int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            final File outputDirectory = fileChooser.getSelectedFile();
            logger.debug("Selected directory: {}", outputDirectory);
            final DownloadDialog downloadDialog = new DownloadDialog(this, pwadService, album.getPictures(), outputDirectory);
            downloadDialog.run();
        }

    }// GEN-LAST:event_downloadButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenuItem aboutMenu;
    private JButton downloadButton;
    private JMenu fileMenu;
    private JTextField invitationLinkField;
    private JLabel invitationLinkLabel;
    private JMenuBar menuBar;
    private JLabel messageLabel;
    private JMenuItem quitMenu;
    private Separator separator1;

    // End of variables declaration//GEN-END:variables

    public void setPwadService(final PwadService pwadService) {
        this.pwadService = pwadService;
    }

    public void setEnvironmentService(final EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }
}
