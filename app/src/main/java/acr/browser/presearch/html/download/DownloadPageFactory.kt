package acr.browser.presearch.html.download

import acr.browser.presearch.R
import acr.browser.presearch.constant.FILE
import acr.browser.presearch.database.downloads.DownloadEntry
import acr.browser.presearch.database.downloads.DownloadsRepository
import acr.browser.presearch.html.HtmlPageFactory
import acr.browser.presearch.html.ListPageReader
import acr.browser.presearch.html.jsoup.*
import acr.browser.presearch.preference.UserPreferences
import android.app.Application
import dagger.Reusable
import io.reactivex.Single
import java.io.File
import java.io.FileWriter
import javax.inject.Inject

/**
 * The factory for the downloads page.
 */
@Reusable
class DownloadPageFactory @Inject constructor(
    private val application: Application,
    private val userPreferences: UserPreferences,
    private val manager: DownloadsRepository,
    private val listPageReader: ListPageReader
) : HtmlPageFactory {

    override fun buildPage(): Single<String> = manager
        .getAllDownloads()
        .map { list ->
            parse(listPageReader.provideHtml()) andBuild {
                title { application.getString(R.string.action_downloads) }
                body {
                    val repeatableElement = id("repeated").removeElement()
                    id("content") {
                        list.forEach {
                            appendChild(repeatableElement.clone {
                                tag("a") { attr("href", createFileUrl(it.title)) }
                                id("title") { text(createFileTitle(it)) }
                                id("url") { text(it.url) }
                            })
                        }
                    }
                }
            }
        }
        .map { content -> Pair(createDownloadsPageFile(), content) }
        .doOnSuccess { (page, content) ->
            FileWriter(page, false).use { it.write(content) }
        }
        .map { (page, _) -> "$FILE$page" }


    private fun createDownloadsPageFile(): File = File(application.filesDir, FILENAME)

    private fun createFileUrl(fileName: String): String = "$FILE${userPreferences.downloadDirectory}/$fileName"

    private fun createFileTitle(downloadItem: DownloadEntry): String {
        val contentSize = if (downloadItem.contentSize.isNotBlank()) {
            "[${downloadItem.contentSize}]"
        } else {
            ""
        }

        return "${downloadItem.title} $contentSize"
    }

    companion object {

        const val FILENAME = "downloads.html"

    }

}
