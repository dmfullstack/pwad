package net.desgrange.pwad.utils;

import net.desgrange.pwad.Main;

import org.junit.After;
import org.junit.Before;
import org.uispec4j.UISpec4J;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;
import org.uispec4j.interception.toolkit.UISpecDisplay;

public abstract class UiTestCase {
    private UISpecAdapter adapter;

    static {
        UISpec4J.init();
    }

    @Before
    public void setUpUISpecDisplay() {
        UISpecDisplay.instance().reset();
    }

    @After
    public void tearDownUISpecDisplay() {
        adapter = null;
        UISpecDisplay.instance().rethrowIfNeeded();
        UISpecDisplay.instance().reset();
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
}
