// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package co.anbora.labs.pdn.thumbnail.actions;

import co.anbora.labs.pdn.thumbnail.ThumbnailView;
import co.anbora.labs.pdn.thumbnail.actionSystem.ThumbnailViewActionUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

final class HideThumbnailsAction extends AnAction {
  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    ThumbnailView view = ThumbnailViewActionUtil.getVisibleThumbnailView(e);
    if (view != null) {
      view.setVisible(false);
    }
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    ThumbnailViewActionUtil.setEnabled(e);
  }
}
