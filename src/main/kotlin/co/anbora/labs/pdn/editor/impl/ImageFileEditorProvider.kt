package co.anbora.labs.pdn.editor.impl

import co.anbora.labs.pdn.fileTypes.ImageFileTypeManager
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

private const val EDITOR_TYPE_ID = "3e5a2f35-5f34-4fe0-9ba6-5753ab11ca04_images-pdn"

class ImageFileEditorProvider: FileEditorProvider, DumbAware {

    override fun accept(project: Project, file: VirtualFile): Boolean = ImageFileTypeManager.getInstance().isImage(file)

    override fun createEditor(project: Project, file: VirtualFile): FileEditor = ImageFileEditorImpl(project, file)

    override fun getEditorTypeId(): String = EDITOR_TYPE_ID

    override fun getPolicy(): FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR

}