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
package net.desgrange.pwad.service;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import net.desgrange.pwad.Main;

public class EnvironmentService {
    private final Properties properties;

    public EnvironmentService(final String propertiesFilePath) throws IOException {
        properties = new Properties();
        properties.load(Main.class.getResource(propertiesFilePath).openStream());
    }

    public String getVersion() {
        return properties.getProperty("pwad.version");
    }

    public boolean isMacOs() {
        return System.getProperty("os.name").toUpperCase(Locale.ENGLISH).startsWith("MAC OS X");
    }
}
