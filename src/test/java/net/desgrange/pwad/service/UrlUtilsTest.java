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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import net.desgrange.pwad.service.exceptions.BadUrlException;

import org.junit.Test;

public class UrlUtilsTest {
  @Test
  public void testGetParameter() {
    assertEquals("baz", UrlUtils.getParameter("http://www.example.com/foo?bar=baz&toto=titi", "bar"));
    assertEquals("titi", UrlUtils.getParameter("http://www.example.com/foo?bar=baz&toto=titi", "toto"));
    assertNull(UrlUtils.getParameter("http://www.example.com/foo?bar=baz&toto=titi", "missing_parameter"));
    assertNull(UrlUtils.getParameter("http://www.example.com/foo?bar=baz&toto=titi", null));
  }

  @Test
  public void testGetParameterThrowsAnExceptionIfGivenUrlIsTooWeirdToBeParsed() {
    checkParameterException(null);
    checkParameterException("[]");
    checkParameterException("http://bad_url?a=b?b=c");
  }

  @Test
  public void testGetPathElement() {
    assertEquals("foo", UrlUtils.getPathElement("http://www.example.com/foo/bar?toto=titi", 0));
    assertEquals("bar", UrlUtils.getPathElement("http://www.example.com/foo/bar?toto=titi", 1));
    assertNull(UrlUtils.getPathElement("http://www.example.com/foo/bar?toto=titi", -1));
    assertNull(UrlUtils.getPathElement("http://www.example.com/foo/bar?toto=titi", 12));
    assertNull(UrlUtils.getPathElement("http://www.example.com", 0));
  }

  @Test
  public void testGetPathElementThrowsAnExceptionIfGivenUrlIsTooWeirdToBeParsed() {
    checkPathException(null);
    checkPathException("[]");
  }

  @Test
  public void testParseInvitationLink() {
    final String link1 = "http://picasaweb.google.fr/lh/sredir?uname=MyUserName&target=ALBUM&id=0123456789012345678&invite=1234ABCD&feat=email";
    final String expectedUrl1 = "http://picasaweb.google.com/data/feed/api/user/MyUserName/albumid/0123456789012345678?kind=photo&imgmax=d&max-results=32767";
    assertEquals(expectedUrl1, UrlUtils.parseInvitationLink(link1));

    final String link2 = "http://picasaweb.google.fr/lh/sredir?uname=MyUserName&target=ALBUM&id=0123456789012345678&authkey=AAAA1111-AAAAA11111A&invite=1234ABCD&feat=email";
    final String expectedUrl2 = "http://picasaweb.google.com/data/feed/api/user/MyUserName/albumid/0123456789012345678?kind=photo&imgmax=d&max-results=32767&authkey=AAAA1111-AAAAA11111A";
    assertEquals(expectedUrl2, UrlUtils.parseInvitationLink(link2));

    final String link3 = "http://picasaweb.google.com/MyUserName/MyAlbumName?feat=email";
    final String expectedUrl3 = "http://picasaweb.google.com/data/feed/api/user/MyUserName/album/MyAlbumName?kind=photo&imgmax=d&max-results=32767";
    assertEquals(expectedUrl3, UrlUtils.parseInvitationLink(link3));

    final String link4 = "http://picasaweb.google.com/MyUserName/MyAlbumName?authkey=AAAA1111-AAAAA11111A&feat=email";
    final String expectedUrl4 = "http://picasaweb.google.com/data/feed/api/user/MyUserName/album/MyAlbumName?kind=photo&imgmax=d&max-results=32767&authkey=AAAA1111-AAAAA11111A";
    assertEquals(expectedUrl4, UrlUtils.parseInvitationLink(link4));
  }

  @Test(expected = BadUrlException.class)
  public void testParseInvitationLinkThrowsAnExceptionIfTheGivenLinkIsNotValid() {
    UrlUtils.parseInvitationLink("bad_link");
  }

  private void checkParameterException(final String url) {
    try {
      UrlUtils.getParameter(url, "foo");
      fail();
    } catch (final BadUrlException e) {
      // Expected
    }
  }

  private void checkPathException(final String url) {
    try {
      UrlUtils.getPathElement(url, 0);
      fail();
    } catch (final BadUrlException e) {
      // Expected
    }
  }
}
