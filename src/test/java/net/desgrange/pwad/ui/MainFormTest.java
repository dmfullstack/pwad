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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.uispec4j.assertion.UISpecAssert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import net.desgrange.pwad.model.Album;
import net.desgrange.pwad.model.Picture;
import net.desgrange.pwad.service.EnvironmentService;
import net.desgrange.pwad.service.PwadService;
import net.desgrange.pwad.service.exceptions.BadUrlException;
import net.desgrange.pwad.utils.UiTestCase;

import org.junit.Before;
import org.junit.Test;
import org.uispec4j.MenuItem;
import org.uispec4j.Trigger;
import org.uispec4j.Window;
import org.uispec4j.interception.BasicHandler;
import org.uispec4j.interception.FileChooserHandler;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

public class MainFormTest extends UiTestCase {
    private EnvironmentService environmentService;
    private PwadService pwadService;

    @Before
    public void setUp() {
        environmentService = mock(EnvironmentService.class);
        pwadService = mock(PwadService.class);
    }

    @Test
    public void testDefaultState() {
        final Window window = createWindow();
        assertTrue(window.titleEquals("pwad - Picasa Web Albums Downloader"));
        assertTrue(window.getTextBox("messageLabel").textEquals("Paste, in the following field, the link you received in the invitation email."));
        assertTrue(window.getInputTextBox("Invitation link").textIsEmpty());
        assertTrue(window.getButton("Download").isEnabled());
        final MenuItem fileMenu = window.getMenuBar().getMenu("File");
        assertTrue(fileMenu.isEnabled());
        assertTrue(fileMenu.getSubMenu("About pwad").isEnabled());
        assertTrue(fileMenu.getSubMenu("Quit").isEnabled());

    }

    @Test
    public void testDownload() {
        final String link = "http://some.link.to/album";
        final String badLink = "hey, this is not a link!";
        final Album album = createAlbum("The album name", 3);
        when(pwadService.getAlbumByInvitationLink(link)).thenReturn(album);
        when(pwadService.getAlbumByInvitationLink(badLink)).thenThrow(new BadUrlException());
        final Window window = createWindow();

        window.getInputTextBox("Invitation link").setText(badLink);
        WindowInterceptor.init(window.getButton().triggerClick()).process(BasicHandler.init()
                .assertTitleEquals("Bad link provided")
                .assertContainsText("The link you provided was not recognized as a valid Picasa Album link.")
                .triggerButtonClick("OK")).run();

        window.getInputTextBox("Invitation link").setText(link);
        WindowInterceptor.init(window.getButton().triggerClick()).process(new FileChooserHandler().cancelSelection()).run();
        WindowInterceptor.init(window.getButton().triggerClick())
                .process(new FileChooserHandler().select(System.getProperty("java.io.tmpdir")))
                .process(new WindowHandler() {
                    @Override
                    public Trigger process(final Window downloadWindow) throws Exception {
                        assertTrue(downloadWindow.isModal());
                        assertTrue(downloadWindow.titleEquals("Downloading…"));
                        return Trigger.DO_NOTHING;
                    }
                }).run();
    }

    private Album createAlbum(final String name, final int numberOfPictures) {
        final List<Picture> pictures = new ArrayList<Picture>();
        for (int i = 0; i < numberOfPictures; i++) {
            final Picture picture = new Picture();
            picture.setId(String.valueOf(i));
            picture.setName("DSC000" + i + ".JPG");
            pictures.add(picture);
        }
        final Album album = new Album();
        album.setName(name);
        album.setPictures(pictures);
        return album;
    }

    private Window createWindow() {
        final MainForm mainForm = new MainForm();
        mainForm.setEnvironmentService(environmentService);
        mainForm.setPwadService(pwadService);
        mainForm.init();
        return WindowInterceptor.run(new Trigger() {
            @Override
            public void run() throws Exception {
                mainForm.setVisible(true);
            }
        });
    }
}
