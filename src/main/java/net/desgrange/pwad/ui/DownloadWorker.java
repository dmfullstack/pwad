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

import java.io.File;
import java.util.List;

import javax.swing.SwingWorker;

import net.desgrange.pwad.model.Picture;
import net.desgrange.pwad.service.PwadService;

import org.apache.log4j.Logger;

public class DownloadWorker extends SwingWorker<Void, Integer> {
    private final Logger logger = Logger.getLogger(getClass());
    private final PwadService pwadService;
    private final List<Picture> pictures;
    private final File outputDirectory;

    public DownloadWorker(final PwadService pwadService, final List<Picture> pictures, final File outputDirectory) {
        this.pwadService = pwadService;
        this.pictures = pictures;
        this.outputDirectory = outputDirectory;
    }

    @Override
    protected Void doInBackground() throws Exception {
        logger.trace("Starting downloading pictures in background…");
        for (int i = 0; i < pictures.size(); i++) {
            logger.trace("Downloading picture " + (i + 1) + " out of " + pictures.size() + "…");
            final Picture picture = pictures.get(i);
            pwadService.downloadPicture(picture, outputDirectory);
            setProgress((i + 1) / pictures.size() * 100);
        }
        return null;
    }
}
