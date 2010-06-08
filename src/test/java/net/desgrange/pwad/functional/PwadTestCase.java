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

import net.desgrange.pwad.Main;
import net.desgrange.pwad.utils.UiTestCase;

import org.junit.After;
import org.junit.Before;
import org.uispec4j.UISpecAdapter;
import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;

public abstract class PwadTestCase extends UiTestCase {
    private UISpecAdapter adapter;

    @Before
    public void ensureEnvironmentIsInitialized() throws Exception {
        TestEnvironment.getInstance();
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
}
