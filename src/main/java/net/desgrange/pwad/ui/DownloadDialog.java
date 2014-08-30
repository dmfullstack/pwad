/**
 *
 * Copyright 2010-2012 Laurent Desgrange
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import net.desgrange.pwad.model.Picture;
import net.desgrange.pwad.service.PwadService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadDialog extends JDialog {
  private static final long serialVersionUID = 2574421000272809793L;
  private transient final Logger logger = LoggerFactory.getLogger(getClass());
  private transient final DownloadWorker worker;

  public DownloadDialog(final Frame parent, final PwadService pwadService, final List<Picture> pictures, final File outputDirectory) {
    super(parent, Dialog.ModalityType.DOCUMENT_MODAL);
    getRootPane().putClientProperty("apple.awt.documentModalSheet", "true");
    initComponents();
    final String pattern = progressLabel.getText();
    progressLabel.setText(MessageFormat.format(pattern, 0, pictures.size()));
    worker = new DownloadWorker(pwadService, pictures, outputDirectory);
    worker.addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(final PropertyChangeEvent event) {
        logger.trace("{} - {}: {}", new Object[] { event, event.getPropertyName(), event.getNewValue() });
        if (DownloadWorker.PROGRESS_PROPERTY_NAME.equals(event.getPropertyName())) {
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
  }

  public void run() {
    setLocationRelativeTo(getParent());
    worker.execute();
    setVisible(true);
  }

  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    progressLabel = new JLabel();
    progressBar = new JProgressBar();
    cancelButton = new JButton();

    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/DownloadDialog").getString("DownloadDialog.title"), new Object[] {})); // NOI18N
    setResizable(false);

    progressLabel.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/DownloadDialog").getString("DownloadDialog.progressLabel.text"), new Object[] {})); // NOI18N
    progressLabel.setName("progressLabel"); // NOI18N

    progressBar.setName("progressBar"); // NOI18N

    cancelButton.setText(MessageFormat.format(ResourceBundle.getBundle("pwad/l10n/DownloadDialog").getString("DownloadDialog.cancelButton.text"), new Object[] {})); // NOI18N
    cancelButton.setName("cancelButton"); // NOI18N
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent evt) {
        cancelButtonActionPerformed(evt);
      }
    });

    final GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(progressLabel, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(cancelButton, Alignment.TRAILING))
                .addContainerGap())
        );
    layout.setVerticalGroup(
        layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void cancelButtonActionPerformed(final ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
    logger.trace("{}", evt);
    cancelButton.setEnabled(false);
    worker.cancel(true);
  }// GEN-LAST:event_cancelButtonActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private JButton cancelButton;
  private JProgressBar progressBar;
  private JLabel progressLabel;
  // End of variables declaration//GEN-END:variables
}
