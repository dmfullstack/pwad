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

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gdata.client.photos.PicasawebService;

public class PwadServiceTest {
    private PwadService pwadService;
    private PicasawebService picasawebService;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        picasawebService = mock(PicasawebService.class);
        pwadService = new PwadService(picasawebService);
    }

    @Test
    public void testGetAlbumThrowsAnExceptionWhenGivenUrlIsInvalid() {
        thrown.expect(BadUrlException.class);
        pwadService.getAlbumByInvitationLink("bad url");
    }

    @Test
    public void testGetAlbumThrowsAnExceptionWhenGivenUrlIsNotSupported() {
        thrown.expect(BadUrlException.class);
        thrown.expectMessage("The link provided is not supported.");
        pwadService.getAlbumByInvitationLink("http://picasaweb.google.fr/lh/sredir");
    }

    @Test
    @Ignore
    public void testGetAlbumByInvitationLink() {
        pwadService.getAlbumByInvitationLink("http://picasaweb.google.fr/lh/sredir?uname=my_name&target=ALBUM&id=1234567890&authkey=ABC123DE-FGH456IJK78&invite=LMN90OPQ&feat=email");

        fail();
    }
}
