package org.desgrange.pwad;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.mediarss.MediaContent;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.PhotoFeed;
import com.google.gdata.util.ServiceException;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        logger.info("Starting pwad (Picasa Web Albums Downloader)…");


        logger.trace("Starting PicasawebService…");
        PicasawebService service = new PicasawebService("pwad-1.0-SNAPSHOT");
        String version = service.getServiceVersion();
        logger.trace("PicasawebService version " + version + " started.");

        String userId = args[0];
        String albumId = args[1];
        StringBuilder albumUrl = new StringBuilder("http://picasaweb.google.com/data/feed/api");
        albumUrl.append("/user/").append(userId);
        albumUrl.append("/albumid/").append(albumId);
        albumUrl.append("?kind=photo&imgmax=d");


        AlbumFeed userFeed = service.getFeed(new URL(albumUrl.toString()), AlbumFeed.class);
        System.out.println("UserFeed: " + userFeed + ", description: " + userFeed.getDescription().getPlainText());
        System.out.println("nickname:" + userFeed.getNickname());

        List<GphotoEntry> entries = userFeed.getEntries();
        for (GphotoEntry entry : entries) {
            System.out.println("Entry: " + entry);
            System.out.println("Description: " + entry.getDescription().getPlainText());
            System.out.println("Link: " + entry.getFeedLink().getHref());
            System.out.println("Photo ID: " + entry.getGphotoId() + ", id: " + entry.getId() + ", kind: " + entry.getKind());
            getPhoto(service, entry.getFeedLink().getHref());
            System.out.println("");
        }


    }

    private static void getPhoto(PicasawebService service, String href) throws IOException, ServiceException {
        PhotoFeed photoFeed = service.getFeed(new URL(href + "?imgmax=d"), PhotoFeed.class);
        System.out.println("photoFeed = " + photoFeed + ", " + photoFeed.getWidth() + "x" + photoFeed.getHeight() + ", " + photoFeed.getDescription().getPlainText());
        System.out.println("Filename: " + photoFeed.getTitle().getPlainText());
        System.out.println("Size: " + photoFeed.getSize());
        for (MediaContent mediaContent : photoFeed.getMediaContents()) {
            System.out.println(mediaContent.getUrl());
            download(mediaContent.getUrl(), photoFeed.getTitle().getPlainText());


        }
    }

    private static void download(String url, String fileName) throws IOException, FileNotFoundException {
        FileOutputStream output = new FileOutputStream("/Users/laurent/Downloads/pix/" + fileName);
        InputStream input = new URL(url).openStream();
        byte chunk[] = new byte[1024];
        int read = 0;
        while ((read = input.read(chunk)) != -1) {
            output.write(chunk, 0, read);
        }
        input.close();
        output.close();
    }
}
