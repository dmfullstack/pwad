package net.desgrange.pwad.ui.utils;

import org.junit.After;
import org.junit.Before;
import org.uispec4j.UISpec4J;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.Window;
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
        UISpecDisplay.instance().rethrowIfNeeded();
        UISpecDisplay.instance().reset();
    }

    protected Window getMainWindow() {
        return adapter.getMainWindow();
    }

    public UISpecAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(final UISpecAdapter adapter) {
        this.adapter = adapter;
    }
}
