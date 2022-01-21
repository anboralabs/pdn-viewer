package co.anbora.labs.pdn.fileTypes.impl

import co.anbora.labs.pdn.ImagesBundle
import co.anbora.labs.pdn.icons.ImagesIcons
import com.intellij.openapi.fileTypes.UserBinaryFileType
import javax.swing.Icon

object ImageFileType: UserBinaryFileType() {

    override fun getName(): String = "Paint DotNet Image"

    override fun getDescription(): String = ImagesBundle.message("filetype.images.description")

    override fun getDisplayName(): String = ImagesBundle.message("filetype.images.display.name");

    override fun getIcon(): Icon = ImagesIcons.ImagesFileType
}