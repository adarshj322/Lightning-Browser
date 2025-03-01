@file:Suppress("NOTHING_TO_INLINE")

package acr.browser.presearch.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat
import java.util.*

/**
 * Returns the dimension in pixels.
 *
 * @param dimenRes the dimension resource to fetch.
 */
inline fun Context.dimen(@DimenRes dimenRes: Int): Int = resources.getDimensionPixelSize(dimenRes)

/**
 * Returns the [ColorRes] as a [ColorInt]
 */
@ColorInt
inline fun Context.color(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

/**
 * Shows a toast with the provided [StringRes].
 */
inline fun Context.toast(@StringRes stringRes: Int) = Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()

/**
 * The [LayoutInflater] available on the [Context].
 */
inline val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

/**
 * Gets a drawable from the context.
 */
inline fun Context.drawable(@DrawableRes drawableRes: Int): Drawable = ContextCompat.getDrawable(this, drawableRes)!!

/**
 * The preferred locale of the user.
 */
val Context.preferredLocale: Locale
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales[0]
    } else {
        @Suppress("DEPRECATION")
        resources.configuration.locale
    }
