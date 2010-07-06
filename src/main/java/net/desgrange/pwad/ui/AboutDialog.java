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

import java.awt.Dialog;
import java.awt.Frame;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import net.desgrange.pwad.service.EnvironmentService;

public final class AboutDialog extends JDialog {
    private static final long serialVersionUID = -8568798361234817727L;
    private transient final EnvironmentService environmentService;

    public AboutDialog(final Frame parent, final EnvironmentService environmentService) {
        super(parent, Dialog.ModalityType.MODELESS);
        this.environmentService = environmentService;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoLabel = new JLabel();
        aboutLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setIcon(new ImageIcon(getClass().getResource("/pwad/images/pwad-logo_64.png"))); // NOI18N
        logoLabel.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/AboutDialog").getString("AboutDialog.logoLabel.text"), new Object[] {})); // NOI18N
        logoLabel.setName("logoLabel"); // NOI18N

        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aboutLabel.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/AboutDialog").getString("AboutDialog.aboutLabel.text"), new Object[] { environmentService.getVersion() })); // NOI18N
        aboutLabel.setName("aboutLabel"); // NOI18N

        final GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(logoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                        .addComponent(aboutLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                                .addContainerGap())
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(logoLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(aboutLabel)
                                .addContainerGap())
                );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel aboutLabel;
    private JLabel logoLabel;
    // End of variables declaration//GEN-END:variables
}
