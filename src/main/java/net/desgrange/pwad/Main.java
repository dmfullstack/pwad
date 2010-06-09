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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.desgrange.pwad.service.EnvironmentService;
import net.desgrange.pwad.service.PwadService;
import net.desgrange.pwad.ui.MainForm;

import org.apache.log4j.Logger;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.mediarss.MediaContent;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoFeed;
import com.google.gdata.util.ServiceException;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    private static final String APPLICATION_NAME = "pwad";
    private static final String PROPERTIES_FILE_PATH = "/pwad/pwad.properties";

    public static void main(final String... args) throws Exception {
        logger.info("Starting pwad (Picasa Web Albums Downloader)…");

        final EnvironmentService environmentService = new EnvironmentService(PROPERTIES_FILE_PATH);
        logger.info("pwad version: " + environmentService.getVersion());

        final PicasawebService picasawebService = new PicasawebService(APPLICATION_NAME + "-" + environmentService.getVersion());
        logger.info("PicasawebService version: " + picasawebService.getServiceVersion());

        final PwadService pwadService = new PwadService(picasawebService);
        final MainForm mainForm = new MainForm();
        mainForm.setEnvironmentService(environmentService);
        mainForm.setPwadService(pwadService);
        mainForm.setVisible(true);
    }

    private static void foo(final String userId, final String albumId) throws Exception {
        logger.trace("Starting PicasawebService…");
        final PicasawebService service = new PicasawebService("pwad-0.1-SNAPSHOT");
        final String version = service.getServiceVersion();
        logger.trace("PicasawebService version " + version + " started.");

        final StringBuilder albumUrl = new StringBuilder("http://picasaweb.google.com/data/feed/api");
        albumUrl.append("/user/").append(userId);
        albumUrl.append("/albumid/").append(albumId);
        albumUrl.append("?kind=photo&imgmax=d");

        final AlbumFeed userFeed = service.getFeed(new URL(albumUrl.toString()), AlbumFeed.class);
        System.out.println("UserFeed: " + userFeed + ", description: " + userFeed.getDescription().getPlainText());
        System.out.println("nickname:" + userFeed.getNickname());

        // final List<GphotoEntry> entries = userFeed.getEntries();
        // for (final GphotoEntry entry : entries) {
        // System.out.println("Entry: " + entry);
        // System.out.println("Description: " + entry.getDescription().getPlainText());
        // System.out.println("Link: " + entry.getFeedLink().getHref());
        // System.out.println("Photo ID: " + entry.getGphotoId() + ", id: " + entry.getId() + ", kind: " + entry.getKind());
        // // getPhoto(service, entry.getFeedLink().getHref());
        // System.out.println("");
        // }

    }

    private static void getPhoto(final PicasawebService service, final String href) throws IOException, ServiceException {
        final PhotoFeed photoFeed = service.getFeed(new URL(href + "?imgmax=d"), PhotoFeed.class);
        System.out.println("photoFeed = " + photoFeed + ", " + photoFeed.getWidth() + "x" + photoFeed.getHeight() + ", " + photoFeed.getDescription().getPlainText());
        System.out.println("Filename: " + photoFeed.getTitle().getPlainText());
        System.out.println("Size: " + photoFeed.getSize());
        for (final MediaContent mediaContent : photoFeed.getMediaContents()) {
            System.out.println(mediaContent.getUrl());
            download(mediaContent.getUrl(), photoFeed.getTitle().getPlainText());

        }
    }

    private static void download(final String url, final String fileName) throws IOException, FileNotFoundException {
        final FileOutputStream output = new FileOutputStream("/Users/laurent/Downloads/pix/" + fileName);
        final InputStream input = new URL(url).openStream();
        final byte chunk[] = new byte[1024];
        int read = 0;
        while ((read = input.read(chunk)) != -1) {
            output.write(chunk, 0, read);
        }
        input.close();
        output.close();
    }
}
