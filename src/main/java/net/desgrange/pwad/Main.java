package net.desgrange.pwad;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import net.desgrange.pwad.service.PwadService;
import net.desgrange.pwad.ui.MainForm;

import org.apache.log4j.Logger;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.mediarss.MediaContent;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.PhotoFeed;
import com.google.gdata.util.ServiceException;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(final String... args) throws Exception {
        logger.info("Starting pwad (Picasa Web Albums Downloader)…");

        final PwadService pwadService = new PwadService();
        final MainForm mainForm = new MainForm();
        mainForm.setPwadService(pwadService);
        mainForm.setVisible(true);
    }

    private static void foo() throws Exception {
        logger.trace("Starting PicasawebService…");
        final PicasawebService service = new PicasawebService("pwad-0.1-SNAPSHOT");
        final String version = service.getServiceVersion();
        logger.trace("PicasawebService version " + version + " started.");

        final String userId = null;// args[0];
        final String albumId = null;// args[1];
        final StringBuilder albumUrl = new StringBuilder("http://picasaweb.google.com/data/feed/api");
        albumUrl.append("/user/").append(userId);
        albumUrl.append("/albumid/").append(albumId);
        albumUrl.append("?kind=photo&imgmax=d");

        final AlbumFeed userFeed = service.getFeed(new URL(albumUrl.toString()), AlbumFeed.class);
        System.out.println("UserFeed: " + userFeed + ", description: " + userFeed.getDescription().getPlainText());
        System.out.println("nickname:" + userFeed.getNickname());

        final List<GphotoEntry> entries = userFeed.getEntries();
        for (final GphotoEntry entry : entries) {
            System.out.println("Entry: " + entry);
            System.out.println("Description: " + entry.getDescription().getPlainText());
            System.out.println("Link: " + entry.getFeedLink().getHref());
            System.out.println("Photo ID: " + entry.getGphotoId() + ", id: " + entry.getId() + ", kind: " + entry.getKind());
            // getPhoto(service, entry.getFeedLink().getHref());
            System.out.println("");
        }

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
