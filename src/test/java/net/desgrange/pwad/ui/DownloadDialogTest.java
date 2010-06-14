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

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.uispec4j.assertion.UISpecAssert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import net.desgrange.pwad.model.Picture;
import net.desgrange.pwad.service.PwadService;
import net.desgrange.pwad.utils.UiTestCase;
import net.desgrange.pwad.utils.WaitAnswer;

import org.junit.Before;
import org.junit.Test;
import org.uispec4j.Trigger;
import org.uispec4j.Window;
import org.uispec4j.interception.WindowInterceptor;

public class DownloadDialogTest extends UiTestCase {
    private PwadService pwadService;
    private List<Picture> pictures;
    private File outputDirectory;
    private Picture picture1;
    private Picture picture2;

    @Before
    public void setUp() {
        pwadService = mock(PwadService.class);
        pictures = Arrays.asList(picture1, picture2);
        outputDirectory = mock(File.class);
    }

    @Test
    public void testDefaultState() {
        doAnswer(new WaitAnswer(5000)).when(pwadService).downloadPicture(picture1, outputDirectory);

        final Window dialog = createWindow();
        assertTrue(dialog.isModal());
        assertTrue(dialog.getTextBox().textEquals("Downloading picture 0 out of 2…"));
        assertTrue(dialog.getProgressBar().completionEquals(0));
        assertTrue(dialog.getButton().isEnabled());

    }

    private Window createWindow() {
        final DownloadDialog dialog = new DownloadDialog(null, pwadService, pictures, outputDirectory);
        return WindowInterceptor.getModalDialog(new Trigger() {
            @Override
            public void run() throws Exception {
                dialog.run();
            }
        });
    }
}
