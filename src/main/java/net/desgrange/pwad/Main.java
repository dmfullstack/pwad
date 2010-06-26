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
package net.desgrange.pwad;

import net.desgrange.pwad.service.EnvironmentService;
import net.desgrange.pwad.service.PwadService;
import net.desgrange.pwad.ui.MainForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gdata.client.photos.PicasawebService;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String APPLICATION_NAME = "pwad";
    private static final String PROPERTIES_FILE_PATH = "/pwad/pwad.properties";

    public static void main(final String... args) throws Exception {
        logger.info("Starting pwad (Picasa Web Albums Downloader)…");

        final EnvironmentService environmentService = new EnvironmentService(PROPERTIES_FILE_PATH);
        logger.info("pwad version: {}", environmentService.getVersion());

        final PicasawebService picasawebService = new PicasawebService(APPLICATION_NAME + "-" + environmentService.getVersion());
        logger.info("PicasawebService version: {}", picasawebService.getServiceVersion());

        final PwadService pwadService = new PwadService(picasawebService);
        final MainForm mainForm = new MainForm();
        mainForm.setEnvironmentService(environmentService);
        mainForm.setPwadService(pwadService);
        mainForm.setVisible(true);
    }
}
