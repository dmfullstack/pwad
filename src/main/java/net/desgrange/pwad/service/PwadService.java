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
package net.desgrange.pwad.service;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.desgrange.pwad.model.Album;
import net.desgrange.pwad.model.Picture;
import net.desgrange.pwad.service.exceptions.BadUrlException;
import net.desgrange.pwad.service.exceptions.DownloadFailedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;

public class PwadService {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final PicasawebService picasawebService;

  public PwadService(final PicasawebService picasawebService) {
    this.picasawebService = picasawebService;
  }

  public Album getAlbumByInvitationLink(final String url) throws BadUrlException {
    final String albumUrl = UrlUtils.parseInvitationLink(url);
    logger.debug("album url: {}", albumUrl);
    logger.trace("Connecting to google using proxy: {}:{}", System.getProperty("http.proxyHost"), System.getProperty("http.proxyPort"));

    try {
      final AlbumFeed albumFeed = picasawebService.getFeed(new URL(albumUrl), AlbumFeed.class);
      logger.trace("Received album feed: {}", albumFeed);
      final Album album = new Album();
      album.setId(albumFeed.getGphotoId());
      album.setName(albumFeed.getTitle().getPlainText());
      album.setPictures(getPictures(albumFeed));
      return album;
    } catch (final MalformedURLException e) {
      logger.warn("Error while downloading album", e);
      throw new BadUrlException(e);
    } catch (final IOException e) {
      logger.warn("Error while downloading album", e);
      throw new BadUrlException(e);
    } catch (final ServiceException e) {
      logger.warn("Error while downloading album", e);
      throw new BadUrlException(e);
    }
  }

  @SuppressWarnings("rawtypes")
  private List<Picture> getPictures(final AlbumFeed albumFeed) {
    final List<Picture> pictures = new ArrayList<Picture>(albumFeed.getEntries().size());
    final List<GphotoEntry> photoEntries = albumFeed.getEntries();
    for (final GphotoEntry entry : photoEntries) {
      final Picture picture = new Picture();
      picture.setId(entry.getId());
      picture.setName(entry.getTitle().getPlainText());
      picture.setUrl(new PhotoEntry(entry).getMediaContents().get(0).getUrl());
      pictures.add(picture);
    }
    return pictures;
  }

  public void downloadPicture(final Picture picture, final File outputDirectory) throws DownloadFailedException {
    FileOutputStream output = null;
    InputStream input = null;
    try {
      output = new FileOutputStream(new File(outputDirectory, picture.getName()));
      input = new URL(picture.getUrl()).openStream();
      final byte chunk[] = new byte[1024];
      int read;
      while ((read = input.read(chunk)) != -1) {
        output.write(chunk, 0, read);
      }
    } catch (final FileNotFoundException e) {
      logger.error("Error while downloading picture", e);
      throw new DownloadFailedException(e);
    } catch (final MalformedURLException e) {
      logger.error("Error while downloading picture", e);
      throw new DownloadFailedException(e);
    } catch (final IOException e) {
      logger.error("Error while downloading picture", e);
      throw new DownloadFailedException(e);
    } finally {
      close(input);
      close(output);
    }
  }

  private void close(final Closeable closeable) {
    if (closeable == null) {
      return;
    }
    try {
      closeable.close();
    } catch (final IOException e) {
      logger.debug("Error while closing resource", e);
    }
  }
}
