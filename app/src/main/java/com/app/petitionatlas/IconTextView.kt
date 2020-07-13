package com.app.petitionatlas

import android.content.Context
import android.graphics.Typeface
import java.util.*

class IconTextView {
    private val cachedicons = Hashtable<String, Typeface?>()

    fun geticons(path: String, context: Context): Typeface? {
        var icons = cachedicons[path]
        if (icons == null) {
            icons = Typeface.createFromAsset(context.assets, path)
            cachedicons[path] = icons
        }
        return icons
    }


}