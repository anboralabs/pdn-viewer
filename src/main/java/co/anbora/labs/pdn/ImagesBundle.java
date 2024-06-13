// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by
// the Apache 2.0 license that can be found in the LICENSE file.
package co.anbora.labs.pdn;

import com.intellij.DynamicBundle;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public final class ImagesBundle extends DynamicBundle {
  @NonNls private static final String BUNDLE = "messages.ImagesBundle";
  private static final ImagesBundle INSTANCE = new ImagesBundle();

  private ImagesBundle() { super(BUNDLE); }

  @NotNull
  public static @Nls
  String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key,
                 Object... params) {
    return INSTANCE.getMessage(key, params);
  }

  @NotNull
  public static Supplier<String>
  messagePointer(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key,
                 Object... params) {
    return INSTANCE.getLazyMessage(key, params);
  }
}