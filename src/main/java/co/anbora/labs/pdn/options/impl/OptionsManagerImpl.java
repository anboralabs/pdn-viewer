/*
 * Copyright 2004-2005 Alexey Efimov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.anbora.labs.pdn.options.impl;

import co.anbora.labs.pdn.options.Options;
import co.anbora.labs.pdn.options.OptionsManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.RoamingType;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

/**
 * Options configurable manager.
 *
 * @author <a href="mailto:aefimov.box@gmail.com">Alexey Efimov</a>
 */
@State(
  name = "Images.OptionsManager",
  storages = @Storage(value = "images.support.xml", roamingType = RoamingType.DISABLED)
)
final class OptionsManagerImpl extends OptionsManager implements PersistentStateComponent<Element> {
  private final OptionsImpl options = new OptionsImpl();

  @Override
  public Options getOptions() {
    return options;
  }

  @Override
  public Element getState() {
    Element element = new Element("state");
    options.writeExternal(element);
    return element;
  }

  @Override
  public void loadState(@NotNull final Element state) {
    options.readExternal(state);
  }
}
