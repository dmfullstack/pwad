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
package net.desgrange.pwad.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.desgrange.pwad.model.Album;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;

public class PwadService {
    private final PicasawebService picasawebService;

    public PwadService(final PicasawebService picasawebService) throws IOException {
        this.picasawebService = picasawebService;
    }

    public Album getAlbumByInvitationLink(final String url) {
        final String userName = UrlUtils.getParameter(url, "uname");
        final String targetType = UrlUtils.getParameter(url, "target"); // ALBUM
        final String targetId = UrlUtils.getParameter(url, "id");

        if (!"ALBUM".equalsIgnoreCase(targetType)) {
            throw new RuntimeException("Sorry, can't handle that");// TODO change this (IllegalArgumentException, etc.)
        }

        final StringBuilder albumUrl = new StringBuilder("http://picasaweb.google.com/data/feed/api");
        albumUrl.append("/user/").append(userName);
        albumUrl.append("/albumid/").append(targetId);
        albumUrl.append("?kind=photo&imgmax=d");

        System.out.println("album url: " + albumUrl);

        try {
            final AlbumFeed albumFeed = picasawebService.getFeed(new URL(albumUrl.toString()), AlbumFeed.class);
            System.out.println("Album feed: " + albumFeed);

            final Album album = new Album();
            album.setId(albumFeed.getGphotoId());
            album.setName(albumFeed.getTitle().getPlainText());
            System.out.println("Album: " + album);

            final List<GphotoEntry> photoEntries = albumFeed.getEntries();
            System.out.println("Nb gphoto entries: " + photoEntries.size());
            for (final GphotoEntry entry : photoEntries) {
                System.out.println("Entry: " + entry);
                System.out.println("Entry id: " + entry.getId());
                System.out.println("Entry pic file name: " + entry.getTitle().getPlainText());
                final PhotoEntry photoEntry = new PhotoEntry(entry);
                System.out.println("Entry pic size: " + photoEntry.getWidth() + "x" + photoEntry.getHeight());
                System.out.println("Entry url: " + photoEntry.getMediaContents().get(0).getUrl());
                break;
            }

            return album;

        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } catch (final ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
