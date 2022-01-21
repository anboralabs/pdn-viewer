// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package co.anbora.labs.pdn.vfs;

import co.anbora.labs.pdn.editor.ImageDocument.ScaledImageProvider;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.reference.SoftReference;
import org.apache.commons.imaging.formats.ico.IcoImageParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.trypticon.pdn.Pdn;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Image loader utility.
 *
 * @author <a href="mailto:aefimov.box@gmail.com">Alexey Efimov</a>
 */
public final class IfsUtil {

  private static final Key<Pair<Long, Long>> TIME_MODIFICATION_STAMP_KEY = Key.create("Image.timeModificationStamp");
  private static final Key<String> FORMAT_KEY = Key.create("Image.format");
  private static final Key<SoftReference<ScaledImageProvider>> IMAGE_PROVIDER_REF_KEY = Key.create("Image.bufferedImageProvider");

  /**
   * Load image data for file and put user data attributes into file.
   *
   * @param file File
   * @return true if file image is loaded.
   * @throws IOException if image can not be loaded
   */
  private static boolean refresh(@NotNull VirtualFile file) throws IOException {
    Pair<Long, Long> loadedTimeModificationStamp = file.getUserData(TIME_MODIFICATION_STAMP_KEY);
    Pair<Long, Long> actualTimeModificationStamp = Pair.create(file.getTimeStamp(), file.getModificationStamp());
    SoftReference<ScaledImageProvider> imageProviderRef = file.getUserData(IMAGE_PROVIDER_REF_KEY);
    if (!actualTimeModificationStamp.equals(loadedTimeModificationStamp) || SoftReference.dereference(imageProviderRef) == null) {
      try {
        final byte[] content = file.contentsToByteArray();
        file.putUserData(IMAGE_PROVIDER_REF_KEY, null);

        InputStream inputStream = new ByteArrayInputStream(content, 0, content.length);
        Pdn pdnImage = Pdn.readFrom(inputStream);

        file.putUserData(FORMAT_KEY, "PDN");
        BufferedImage image = pdnImage.getDocument().toBufferedImage();
        file.putUserData(IMAGE_PROVIDER_REF_KEY, new SoftReference<>((zoom, ancestor) -> image));
        return true;

      } finally {
        // We perform loading no more needed
        file.putUserData(TIME_MODIFICATION_STAMP_KEY, actualTimeModificationStamp);
      }
    }
    return false;
  }

  @Nullable
  public static BufferedImage getImage(@NotNull VirtualFile file) throws IOException {
    return getImage(file, null);
  }

  @Nullable
  public static BufferedImage getImage(@NotNull VirtualFile file, @Nullable Component ancestor) throws IOException {
    ScaledImageProvider imageProvider = getImageProvider(file);
    if (imageProvider == null) return null;
    return imageProvider.apply(1d, ancestor);
  }

  @Nullable
  public static ScaledImageProvider getImageProvider(@NotNull VirtualFile file) throws IOException {
    refresh(file);
    SoftReference<ScaledImageProvider> imageProviderRef = file.getUserData(IMAGE_PROVIDER_REF_KEY);
    return SoftReference.dereference(imageProviderRef);
  }

  @Nullable
  public static String getFormat(@NotNull VirtualFile file) throws IOException {
    refresh(file);
    return file.getUserData(FORMAT_KEY);
  }

  public static @NlsSafe String getReferencePath(Project project, VirtualFile file) {
    ProjectFileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
    VirtualFile sourceRoot = fileIndex.getSourceRootForFile(file);
    if (sourceRoot != null) {
      return getRelativePath(file, sourceRoot);
    }

    VirtualFile root = fileIndex.getContentRootForFile(file);
    if (root != null) {
      return getRelativePath(file, root);
    }

    return file.getPath();
  }

  private static String getRelativePath(final VirtualFile file, final VirtualFile root) {
    if (root.equals(file)) {
      return file.getPath();
    }
    return "/" + VfsUtilCore.getRelativePath(file, root, '/');
  }
}
