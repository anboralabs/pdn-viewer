package co.anbora.labs.pdn.utils

import org.jdom.Element
import org.jetbrains.annotations.NonNls
import com.intellij.util.xmlb.Constants

fun Element.addOptionTag(@NonNls name: String, value: String, @NonNls elementName: String = Constants.OPTION) {
    val element = Element(elementName)
    element.setAttribute(Constants.NAME, name)
    element.setAttribute(Constants.VALUE, value)
    addContent(element)
}