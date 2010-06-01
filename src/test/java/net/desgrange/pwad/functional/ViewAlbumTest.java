package net.desgrange.pwad.functional;

import static org.uispec4j.assertion.UISpecAssert.assertTrue;

import org.junit.Test;
import org.uispec4j.MenuItem;
import org.uispec4j.Trigger;
import org.uispec4j.Window;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

public class ViewAlbumTest extends PwadTestCase {
    @Test
    public void testUserCanViewPublicAlbumFromInvitationLink() throws Exception {
        final Window window = getMainWindow();

        assertTrue(window.titleEquals("pwad - Picasa Web Albums Downloader"));
        final MenuItem fileMenu = window.getMenuBar().getMenu("File");
        WindowInterceptor.init(fileMenu.getSubMenu("Open album from invitation link…").triggerClick()).process(new WindowHandler() {
            @Override
            public Trigger process(final Window dialog) throws Exception {
                assertTrue(dialog.titleEquals("Open album from invitation link…"));
                assertTrue(dialog.getTextBox("message").textEquals("Paste, in the following field, the link you received in the invitation email."));
                dialog.getInputTextBox("Invitation link").setText(createInvitationLink());
                return dialog.getButton("OK").triggerClick();
            }
        }).run();
        assertTrue(window.getTextBox("albumNameField").textEquals("Holiday in Cambodia"));
        // window.getTable("Pictures").
    }

    private String createInvitationLink() {
        final String userId = "some_user_id";
        final String albumId = "an_album_id";
        final String authenticationKey = "the_authentication_key";
        final String invitationId = "invitation_id";
        return "http://picasaweb.google.fr/lh/sredir?uname=" + userId + "&target=ALBUM&id=" + albumId + "&authkey=" + authenticationKey + "&invite=" + invitationId + "&feat=email";
    }
}
