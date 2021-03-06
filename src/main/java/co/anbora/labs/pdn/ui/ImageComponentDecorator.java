/*
 * Copyright 2000-2009 JetBrains s.r.o.
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
package co.anbora.labs.pdn.ui;

import com.intellij.openapi.actionSystem.DataKey;
import co.anbora.labs.pdn.editor.ImageZoomModel;

import java.awt.*;

/**
 * Image Component manager. It can toggle backround transparency, grid, etc.
 *
 * @author Alexey Efimov
 */
public interface ImageComponentDecorator {
  DataKey<ImageComponentDecorator> DATA_KEY = DataKey.create(ImageComponentDecorator.class.getName());

  void setTransparencyChessboardVisible(boolean visible);

  boolean isTransparencyChessboardVisible();

  /**
   * Return {@code true} if this decorator is enabled for this action place.
   *
   * @param place Action place
   * @return {@code true} is decorator is enabled
   */
  boolean isEnabledForActionPlace(String place);

  ImageZoomModel getZoomModel();

  void setGridVisible(boolean visible);

  boolean isGridVisible();
  
  default boolean isFileSizeVisible() {
    return true;
  }
  
  default void setFileSizeVisible(boolean visible) {}
  
  default boolean isFileNameVisible() {
    return true;
  }
  
  default void setFileNameVisible(boolean visible) {}

  default void setBorderVisible(boolean visible) {}

  default void setEditorBackground(Color color) {}
}
