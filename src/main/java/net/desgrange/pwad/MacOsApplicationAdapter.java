/**
 *
 * Copyright 2010-2012 Laurent Desgrange
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
package net.desgrange.pwad;

import net.desgrange.pwad.ui.MainForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apple.eawt.ApplicationAdapter;
import com.apple.eawt.ApplicationEvent;

public class MacOsApplicationAdapter extends ApplicationAdapter {
  private transient final Logger logger = LoggerFactory.getLogger(getClass());
  private final MainForm mainForm;

  public MacOsApplicationAdapter(final MainForm mainForm) {
    this.mainForm = mainForm;
  }

  @Override
  public void handleQuit(final ApplicationEvent event) {
    logger.trace("{}", event);
    mainForm.quit();
  }

  @Override
  public void handleAbout(final ApplicationEvent event) {
    logger.trace("{}", event);
    event.setHandled(true);
    mainForm.displayAboutDialog();
  }
}
