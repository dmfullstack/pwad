package net.desgrange.pwad.functional;

import static org.uispec4j.assertion.UISpecAssert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.desgrange.pwad.utils.UiTestCase;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.junit.Test;
import org.uispec4j.MenuItem;
import org.uispec4j.Trigger;
import org.uispec4j.Window;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

public class ViewAlbumTest extends UiTestCase {
    @Test
    public void testUserCanViewPublicAlbumFromInvitationLink() throws Exception {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8080");
        startServer();
        final Window window = getMainWindow();

        assertTrue(window.titleEquals("pwad - Picasa Web Albums Downloader"));
        final MenuItem fileMenu = window.getMenuBar().getMenu("File");
        final WindowInterceptor interceptor = WindowInterceptor.init(fileMenu.getSubMenu("Open album from invitation link…").triggerClick());
        interceptor.process(new WindowHandler() {
            @Override
            public Trigger process(final Window dialog) throws Exception {
                System.out.println("Handling intercepted dialog…");
                assertTrue(dialog.titleEquals("Open album from invitation link…"));
                assertTrue(dialog.getTextBox("Paste, in the following field, the link you received in the invitation email.").isVisible());
                dialog.getInputTextBox("Invitation link").setText(createInvitationLink());
                return dialog.getButton("OK").triggerClick();
            }
        });
        interceptor.run();
        window.getTextBox("albumName").textEquals("Holyday in Cambodia");
        // window.getTable("Pictures").
    }

    private void startServer() throws Exception {
        final Server server = new Server(8080);
        server.setHandler(new AbstractHandler() {
            @Override
            public void handle(final String target, final Request jettyRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
                System.out.println("Target: " + target);
                System.out.println("Request: " + request);
                System.out.println("Response: " + response);
            }
        });
        server.start();

    }

    private String createInvitationLink() {
        final String userId = "some_user_id";
        final String albumId = "an_album_id";
        final String authenticationKey = "the_authentication_key";
        final String invitationId = "invitation_id";
        return "http://picasaweb.google.fr/lh/sredir?uname=" + userId + "&target=ALBUM&id=" + albumId + "&authkey=" + authenticationKey + "&invite=" + invitationId + "&feat=email";
    }
}
