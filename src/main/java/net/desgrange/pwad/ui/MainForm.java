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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu.Separator;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.desgrange.pwad.model.Album;
import net.desgrange.pwad.service.EnvironmentService;
import net.desgrange.pwad.service.PwadService;

import org.apache.log4j.Logger;

public class MainForm extends JFrame {
    private static final long serialVersionUID = 4313821019914508450L;
    private final Logger logger = Logger.getLogger(getClass());
    private EnvironmentService environmentService;
    private PwadService pwadService;

    public MainForm() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        albumNameLabel = new JLabel();
        albumNameField = new JLabel();
        picturesCountLabel = new JLabel();
        picturesCountField = new JLabel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        aboutPwad = new JMenuItem();
        separator1 = new Separator();
        openMenuItem = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final ResourceBundle bundle = ResourceBundle.getBundle("pwad/l10n/MainForm"); // NOI18N
        setTitle(bundle.getString("MainForm.title")); // NOI18N
        setName("Form"); // NOI18N

        albumNameLabel.setText(bundle.getString("MainForm.albumNameLabel.text")); // NOI18N
        albumNameLabel.setName("albumNameLabel"); // NOI18N

        albumNameField.setText(bundle.getString("MainForm.albumNameField.text")); // NOI18N
        albumNameField.setName("albumNameField"); // NOI18N

        picturesCountLabel.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/MainForm").getString("MainForm.picturesCountLabel.text"), new Object[] {})); // NOI18N
        picturesCountLabel.setName("picturesCountLabel"); // NOI18N

        picturesCountField.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/MainForm").getString("MainForm.picturesCountField.text"), new Object[] {})); // NOI18N
        picturesCountField.setName("picturesCountField"); // NOI18N

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(bundle.getString("MainForm.fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        aboutPwad.setText(bundle.getString("MainForm.aboutPwad.text")); // NOI18N
        aboutPwad.setName("aboutPwad"); // NOI18N
        aboutPwad.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                aboutPwadActionPerformed(evt);
            }
        });
        fileMenu.add(aboutPwad);

        separator1.setName("separator1"); // NOI18N
        fileMenu.add(separator1);

        openMenuItem.setText(bundle.getString("MainForm.openMenuItem.text")); // NOI18N
        openMenuItem.setName("openMenuItem"); // NOI18N
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        final GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(albumNameLabel, Alignment.TRAILING)
                                        .addComponent(picturesCountLabel, Alignment.TRAILING))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(albumNameField, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                        .addComponent(picturesCountField, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                                .addContainerGap())
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(albumNameLabel)
                                        .addComponent(albumNameField))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(picturesCountLabel)
                                        .addComponent(picturesCountField))
                                .addContainerGap(218, Short.MAX_VALUE))
                );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aboutPwadActionPerformed(final ActionEvent evt) {// GEN-FIRST:event_aboutPwadActionPerformed
        logger.trace(evt);
        final AboutDialog dialog = new AboutDialog(this, environmentService);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }// GEN-LAST:event_aboutPwadActionPerformed

    private void openMenuItemActionPerformed(final ActionEvent evt) {// GEN-FIRST:event_openMenuItemActionPerformed
        logger.trace(evt);
        final OpenAlbumDialog dialog = new OpenAlbumDialog(this);
        dialog.setVisible(true);
        final String link = dialog.getLink();
        logger.debug("Invitation link: " + link);

        final Album album = pwadService.getAlbumByInvitationLink(link);
        albumNameField.setText(album.getName());
        picturesCountField.setText(String.valueOf(album.getPictures().size()));
    }// GEN-LAST:event_openMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenuItem aboutPwad;
    private JLabel albumNameField;
    private JLabel albumNameLabel;
    private JMenu fileMenu;
    private JMenuBar menuBar;
    private JMenuItem openMenuItem;
    private JLabel picturesCountField;
    private JLabel picturesCountLabel;
    private Separator separator1;

    // End of variables declaration//GEN-END:variables

    public void setPwadService(final PwadService pwadService) {
        this.pwadService = pwadService;
    }

    public void setEnvironmentService(final EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }
}
