package acr.browser.presearch

import android.os.Build

/**
 * Capabilities that are specific to certain API levels.
 */
enum class Capabilities {
    FULL_INCOGNITO,
    WEB_RTC,
    THIRD_PARTY_COOKIE_BLOCKING
}

/**
 * Returns true if the capability is supported, false otherwise.
 */
val Capabilities.isSupported: Boolean
    get() = when (this) {
        Capabilities.FULL_INCOGNITO -> Build.VERSION.SDK_INT >= 28
        Capabilities.WEB_RTC -> Build.VERSION.SDK_INT >= 21
        Capabilities.THIRD_PARTY_COOKIE_BLOCKING -> Build.VERSION.SDK_INT >= 21
    }
