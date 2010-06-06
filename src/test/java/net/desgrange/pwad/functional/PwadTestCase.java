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
package net.desgrange.pwad.functional;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.desgrange.pwad.Main;
import net.desgrange.pwad.utils.UiTestCase;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.junit.After;
import org.junit.Before;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;

public abstract class PwadTestCase extends UiTestCase {
    private UISpecAdapter adapter;
    private Server server;
    private int port;

    @Before
    public void ensureEnvironmentIsInitialized() throws Exception {
        getServer();
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8080");
    }

    @After
    public void afterTest() {
        adapter = null;
    }

    public Window getMainWindow() {
        return getAdapter().getMainWindow();
    }

    private UISpecAdapter getAdapter() {
        if (adapter == null) {
            adapter = new MainClassAdapter(Main.class);
        }
        return adapter;
    }

    private Server getServer() throws Exception {
        if (server == null) {
            server = new Server(getPort());
            server.setHandler(new AbstractHandler() {
                @Override
                public void handle(final String target, final Request jettyRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
                    System.out.println("Target: " + target);
                    System.out.println("Request: " + request);
                    System.out.println("Response: " + response);
                    if (target.equals("/data/feed/api/user/dead_kennedys/albumid/holiday_in_cambodia")) {
                        sendResponse(response, "/response.xml");
                    }

                }

                private void sendResponse(final HttpServletResponse response, final String resourcePath) throws IOException {
                    response.setContentType("application/atom+xml; charset=UTF-8; type=feed");
                    final ServletOutputStream output = response.getOutputStream();
                    final InputStream input = Main.class.getResource(resourcePath).openStream();
                    final byte chunk[] = new byte[1024];
                    int read = 0;
                    while ((read = input.read(chunk)) != -1) {
                        output.write(chunk, 0, read);
                    }
                    input.close();
                    output.close();
                }
            });
            server.start();
        }
        return server;
    }

    private int getPort() {
        if (port == 0) {
            port = 8080;// TODO find a free port to use automatically
        }
        return port;
    }
}
