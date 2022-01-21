package co.anbora.labs.pdn.editor.impl

import co.anbora.labs.pdn.fileTypes.ImageFileTypeManager
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

object ImageFileEditorProvider: FileEditorProvider, DumbAware {

    private const val EDITOR_TYPE_ID = "images-pdn"

    override fun accept(project: Project, file: VirtualFile): Boolean = ImageFileTypeManager.getInstance().isImage(file)

    override fun createEditor(project: Project, file: VirtualFile): FileEditor = ImageFileEditorImpl(project, file)

    override fun getEditorTypeId(): String = EDITOR_TYPE_ID

    override fun getPolicy(): FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR

}