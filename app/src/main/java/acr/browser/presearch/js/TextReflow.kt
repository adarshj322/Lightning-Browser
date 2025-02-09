package acr.browser.presearch.js

import com.anthonycr.mezzanine.FileStream

/**
 * Force the text to reflow.
 */
@FileStream("app/src/main/js/TextReflow.js")
interface TextReflow {

    fun provideJs(): String

}