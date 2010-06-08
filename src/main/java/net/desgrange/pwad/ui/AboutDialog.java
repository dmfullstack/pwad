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

import java.awt.Frame;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;

import net.desgrange.pwad.service.EnvironmentService;

public class AboutDialog extends JDialog {
    private static final long serialVersionUID = -8568798361234817727L;
    private final EnvironmentService environmentService;

    public AboutDialog(final Frame parent, final EnvironmentService environmentService) {
        super(parent, true);
        this.environmentService = environmentService;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aboutLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aboutLabel.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/AboutDialog").getString("AboutDialog.aboutLabel.text"), new Object[] {environmentService.getVersion()})); // NOI18N
        aboutLabel.setName("aboutLabel"); // NOI18N

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aboutLabel, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aboutLabel, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel aboutLabel;
    // End of variables declaration//GEN-END:variables
}
