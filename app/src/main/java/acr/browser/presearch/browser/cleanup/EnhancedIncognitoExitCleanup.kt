package acr.browser.presearch.browser.cleanup

import acr.browser.presearch.browser.activity.BrowserActivity
import acr.browser.presearch.log.Logger
import acr.browser.presearch.utils.WebUtils
import android.webkit.WebView
import javax.inject.Inject

/**
 * Exit cleanup that should be run when the incognito process is exited on API >= 28. This cleanup
 * clears cookies and all web data, which can be done without affecting
 */
class EnhancedIncognitoExitCleanup @Inject constructor(
    private val logger: Logger
) : ExitCleanup {
    override fun cleanUp(webView: WebView?, context: BrowserActivity) {
        WebUtils.clearCache(webView)
        logger.log(TAG, "Cache Cleared")
        WebUtils.clearCookies(context)
        logger.log(TAG, "Cookies Cleared")
        WebUtils.clearWebStorage()
        logger.log(TAG, "WebStorage Cleared")
    }

    companion object {
        private const val TAG = "EnhancedIncognitoExitCleanup"
    }
}
