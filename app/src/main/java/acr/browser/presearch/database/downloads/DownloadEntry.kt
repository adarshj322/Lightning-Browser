package acr.browser.presearch.database.downloads

/**
 * An entry in the downloads database.
 *
 * @param url The URL of the original download.
 * @param title The file name.
 * @param contentSize The user readable content size.
 */
data class DownloadEntry(
    val url: String,
    val title: String,
    val contentSize: String
)
