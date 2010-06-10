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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.desgrange.pwad.model.Picture;
import net.desgrange.pwad.service.PwadService;

import org.apache.log4j.Logger;

public class DownloadDialog extends JDialog {
    private static final long serialVersionUID = 2574421000272809793L;
    private final Logger logger = Logger.getLogger(getClass());
    private final PwadService pwadService;
    private final List<Picture> pictures;
    private final File outputDirectory;

    public DownloadDialog(final Frame parent, final PwadService pwadService, final List<Picture> pictures, final File outputDirectory) {
        super(parent, true);
        this.pwadService = pwadService;
        this.pictures = pictures;
        this.outputDirectory = outputDirectory;
        initComponents();
    }

    public void run() {
        final String pattern = progressLabel.getText();
        progressLabel.setText(MessageFormat.format(pattern, 0, pictures.size()));
        setLocationRelativeTo(getParent());

        final DownloadWorker worker = new DownloadWorker(pwadService, pictures, outputDirectory);
        worker.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent event) {
                logger.trace(event);
                if ("progress".equals(event.getPropertyName())) {
                    final Integer pictureNumber = (Integer) event.getNewValue();
                    progressLabel.setText(MessageFormat.format(pattern, pictureNumber, pictures.size()));
                    progressBar.setValue(100 * pictureNumber / pictures.size());
                }
                if ("state".equals(event.getPropertyName()) && SwingWorker.StateValue.DONE.equals(event.getNewValue())) {
                    progressBar.setValue(100);
                    dispose();
                }
            }
        });
        worker.execute();
        setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progressLabel = new JLabel();
        progressBar = new JProgressBar();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/DownloadDialog").getString("DownloadDialog.title"), new Object[] {})); // NOI18N
        progressLabel.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/DownloadDialog").getString("DownloadDialog.progressLabel.text"), new Object[] {})); // NOI18N
        progressLabel.setName("progressLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        final GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                        .addComponent(progressLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                                .addContainerGap())
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(progressLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JProgressBar progressBar;
    private JLabel progressLabel;
    // End of variables declaration//GEN-END:variables
}
