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
package co.anbora.labs.pdn.editor.actions;

import co.anbora.labs.pdn.editor.ImageZoomModel;
import co.anbora.labs.pdn.editor.actionSystem.ImageEditorActionUtil;
import co.anbora.labs.pdn.options.Options;
import co.anbora.labs.pdn.options.OptionsManager;
import co.anbora.labs.pdn.options.ZoomOptions;
import co.anbora.labs.pdn.ui.ImageComponentDecorator;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import org.jetbrains.annotations.NotNull;

final class FitZoomToWindowAction extends AnAction implements DumbAware {
  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    ImageComponentDecorator decorator =
        ImageEditorActionUtil.getImageComponentDecorator(e);
    if (decorator != null) {
      ImageZoomModel zoomModel = decorator.getZoomModel();
      zoomModel.fitZoomToWindow();
    }
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    if (ImageEditorActionUtil.setEnabled(e)) {
      Options options = OptionsManager.getInstance().getOptions();
      ZoomOptions zoomOptions = options.getEditorOptions().getZoomOptions();
      ImageComponentDecorator decorator =
          ImageEditorActionUtil.getImageComponentDecorator(e);
      ImageZoomModel zoomModel = decorator.getZoomModel();
      e.getPresentation().setEnabled(zoomModel.isZoomLevelChanged() ||
                                     !zoomOptions.isSmartZooming());
    }
  }

  @Override
  public @NotNull ActionUpdateThread getActionUpdateThread() {
    return ActionUpdateThread.EDT;
  }
}
