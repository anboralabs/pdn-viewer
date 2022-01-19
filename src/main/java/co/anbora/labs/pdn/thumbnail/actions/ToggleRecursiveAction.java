// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package co.anbora.labs.pdn.thumbnail.actions;

import co.anbora.labs.pdn.thumbnail.ThumbnailView;
import co.anbora.labs.pdn.thumbnail.actionSystem.ThumbnailViewActionUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import org.jetbrains.annotations.NotNull;

/**
 * Toggle recursive flag.
 */
final class ToggleRecursiveAction extends ToggleAction {
  @Override
  public boolean isSelected(@NotNull AnActionEvent e) {
    ThumbnailView view = ThumbnailViewActionUtil.getVisibleThumbnailView(e);
    return view != null && view.isRecursive();
  }

  @Override
  public void setSelected(@NotNull AnActionEvent e, boolean state) {
    ThumbnailView view = ThumbnailViewActionUtil.getVisibleThumbnailView(e);
    if (view != null) {
      view.setRecursive(state);
    }
  }

  @Override
  public void update(@NotNull final AnActionEvent e) {
    super.update(e);
    ThumbnailViewActionUtil.setEnabled(e);
  }
}
