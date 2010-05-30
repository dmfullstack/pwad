package net.desgrange.pwad.ui;

import static org.uispec4j.assertion.UISpecAssert.assertTrue;
import net.desgrange.pwad.ui.utils.UiTestCase;

import org.junit.Test;
import org.uispec4j.Trigger;
import org.uispec4j.Window;
import org.uispec4j.interception.WindowInterceptor;

public class MainFormTest extends UiTestCase {
    @Test
    public void testDefaultState() {
        final Window mainForm = createMainForm();
        assertTrue(mainForm.titleEquals("pwad - Picasa Web Albums Downloader"));
    }

    private Window createMainForm() {
        return WindowInterceptor.run(new Trigger() {
            public void run() throws Exception {
                final MainForm mainForm = new MainForm();
                mainForm.setVisible(true);
            }
        });
    }
}
