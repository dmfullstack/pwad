package net.desgrange.pwad.utils;

import org.junit.After;
import org.junit.Before;
import org.uispec4j.UISpec4J;
import org.uispec4j.interception.toolkit.UISpecDisplay;

public abstract class UiTestCase {
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
}
