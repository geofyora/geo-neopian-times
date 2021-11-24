package com.geo.neopian.times.parser.impl

import com.geo.neopian.times.entry.NTEntry
import com.geo.neopian.times.parser.Parser
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.InputStream

class EntryListParser : Parser {

    val NEOPETS_URL = "https://www.neopets.com/";

    override fun parse(inputStream: InputStream): ArrayList<NTEntry> {
        val entryList = arrayListOf<NTEntry>()

        if (inputStream == null) {
            return entryList
        }
        val doc = Jsoup.parse(inputStream, Charsets.UTF_8.displayName(), NEOPETS_URL)
        val content = doc.getElementsByClass("content")

        if (content.size > 0) {
            val entriesTable = content[0]
            val entries = entriesTable.getElementsByTag("tr")
            entries
                .forEach{ entry ->
                    val parsedEntry = parseEntry(entry)
                    if (parsedEntry != null) {
                        entryList.add(parsedEntry)
                    }
                }
        }
        return entryList
    }

    fun parseEntry(entryElement : Element): NTEntry? {
        val bolds = entryElement.getElementsByTag("b")
        val title = bolds[0].text()
        val summary = entryElement.text()
        val anchors = entryElement.getElementsByTag("a")
        val contentPage = Jsoup.connect(anchors[0].attr("href")).get()
        val contents = contentPage.getElementsByClass("content").text()
        val primaryAuthorElement = anchors.filter { a -> a.attr("href") != null && a.attr("href").contains("randomfriend")}[0]
        val ntEntryBuilder : NTEntry.NTEntryBuilder = NTEntry.NTEntryBuilder.createBuilder()
        ntEntryBuilder.addAuthor(primaryAuthorElement.text())
        ntEntryBuilder.withTitle(title)
        ntEntryBuilder.withSummary(summary)
        ntEntryBuilder.withContents(contents)
        return ntEntryBuilder.build()
        // to get co-author, search for lnstance of "also by" and italics
    }
}