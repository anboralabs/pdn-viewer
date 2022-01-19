// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package co.anbora.labs.pdn.fileTypes.impl;

import com.intellij.openapi.fileTypes.UserBinaryFileType;
import co.anbora.labs.pdn.icons.ImagesIcons;
import co.anbora.labs.pdn.ImagesBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class ImageFileType extends UserBinaryFileType {
  public static final ImageFileType INSTANCE = new ImageFileType();

  private ImageFileType() {
  }

  @NotNull
  @Override
  public String getName() {
    return "Image";
  }

  @NotNull
  @Override
  public String getDescription() {
    return ImagesBundle.message("filetype.images.description");
  }

  @Nls
  @Override
  public @NotNull String getDisplayName() {
    return ImagesBundle.message("filetype.images.display.name");
  }

  @Override
  public Icon getIcon() {
    return ImagesIcons.ImagesFileType;
  }
}
