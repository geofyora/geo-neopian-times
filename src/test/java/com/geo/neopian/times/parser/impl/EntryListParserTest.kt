package com.geo.neopian.times.parser.impl
import org.junit.jupiter.api.Test
import java.io.InputStream

class EntryListParserTest {

    @Test
    fun `example test`(){
        val entryListParser = EntryListParser()
        val classloader = Thread.currentThread().contextClassLoader
        val inStream: InputStream? = classloader.getResourceAsStream("pages/nt_articles.html")
        if (inStream != null) {
            entryListParser.parse(inStream)
        }
    }
}