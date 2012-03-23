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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import net.desgrange.pwad.service.exceptions.BadUrlException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class UrlUtils {
  public static String getParameter(final String url, final String parameterName) throws BadUrlException {
    try {
      final List<NameValuePair> nameValuePairs = URLEncodedUtils.parse(new URI(url), "UTF-8");
      for (final NameValuePair pair : nameValuePairs) {
        if (pair.getName().equalsIgnoreCase(parameterName)) {
          return pair.getValue();
        }
      }
      return null;
    } catch (final URISyntaxException e) {
      throw new BadUrlException(e);
    } catch (final IllegalArgumentException e) {
      throw new BadUrlException(e);
    } catch (final NullPointerException e) {
      throw new BadUrlException(e);
    }
  }

  public static String getPathElement(final String url, final int elementPosition) {
    try {
      final String[] explodedPath = new URI(url).getPath().split("/");
      return StringUtils.defaultIfEmpty(explodedPath[elementPosition + 1], null);
    } catch (final IndexOutOfBoundsException e) {
      return null;
    } catch (final URISyntaxException e) {
      throw new BadUrlException(e);
    } catch (final NullPointerException e) {
      throw new BadUrlException(e);
    }
  }

  public static String parseInvitationLink(final String url) {
    final String userName = StringUtils.defaultIfEmpty(getParameter(url, "uname"), getPathElement(url, 0));
    final String albumName = getPathElement(url, 1);
    final String targetType = UrlUtils.getParameter(url, "target");
    final String targetId = UrlUtils.getParameter(url, "id");
    final String authKey = UrlUtils.getParameter(url, "authkey");

    if (StringUtils.isBlank(userName)) {
      throw new BadUrlException("The link provided is not supported.");
    }
    if (!"ALBUM".equalsIgnoreCase(targetType) && StringUtils.isEmpty(albumName)) {
      throw new BadUrlException("The link provided is not supported.");
    }

    final StringBuilder albumUrl = new StringBuilder("http://picasaweb.google.com/data/feed/api");
    albumUrl.append("/user/").append(userName);
    if (StringUtils.isNotBlank(targetType)) {
      albumUrl.append("/albumid/").append(targetId);
    } else {
      albumUrl.append("/album/").append(albumName);
    }
    albumUrl.append("?kind=photo&imgmax=d");
    albumUrl.append("&max-results=").append(Short.MAX_VALUE);
    if (StringUtils.isNotBlank(authKey)) {
      albumUrl.append("&authkey=").append(authKey);
    }
    return albumUrl.toString();
  }
}
