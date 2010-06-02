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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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
        checkException(null);
        checkException("[]");
        checkException("http://bad_url?a=b?b=c");
    }

    private void checkException(final String url) {
        try {
            UrlUtils.getParameter(url, "foo");
            fail();
        } catch (final BadUrlException e) {
            // Expected
        }
    }
}
