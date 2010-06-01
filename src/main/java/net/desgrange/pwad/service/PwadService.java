package net.desgrange.pwad.service;

import net.desgrange.pwad.model.Album;

public class PwadService {
    public Album getAlbumByInvitationLink(final String link) {
        final Album album = new Album();
        album.setName("Holiday in Cambodia");
        return album;
    }
}
