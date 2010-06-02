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
