package com.intellij.ui.scale

import java.util.concurrent.atomic.AtomicReference
import java.util.function.Function

class CompatibilityScaleContextCache<T>(
    private val dataProvider: Function<in ScaleContext, out T>
) {

    private val data = AtomicReference<Pair<Double, T>?>(null)

    fun getOrProvide(scaleContext: ScaleContext): T? {
        var data = data.get()
        val scale = scaleContext.getScale(DerivedScaleType.PIX_SCALE)
        if (data == null || scale.compareTo(data.first) != 0) {
            data = scale to dataProvider.apply(scaleContext)
            this.data.set(data)
        }
        return data.second
    }

    fun clear() {
        data.set(null)
    }
}
