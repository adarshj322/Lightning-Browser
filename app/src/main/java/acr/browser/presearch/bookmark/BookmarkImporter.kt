package acr.browser.presearch.bookmark

import acr.browser.presearch.database.Bookmark
import java.io.InputStream

/**
 * An importer that imports [Bookmark.Entry] from an [InputStream]. Supported formats are details of
 * the implementation.
 */
interface BookmarkImporter {

    /**
     * Synchronously converts an [InputStream] to a [List] of [Bookmark.Entry].
     */
    fun importBookmarks(inputStream: InputStream): List<Bookmark.Entry>

}
