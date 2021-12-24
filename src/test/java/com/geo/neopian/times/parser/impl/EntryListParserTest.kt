package com.geo.neopian.times.parser.impl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.InputStream

class EntryListParserTest {

    @Test
    fun `example test`(){
        val entryListParser = EntryListParser()
        val classloader = Thread.currentThread().contextClassLoader
        val inStream: InputStream? = classloader.getResourceAsStream("pages/nt_articles.html")
        if (inStream != null) {
            val parsed = entryListParser.parse(inStream)
            assertThat(parsed).hasSize(4)
        }
    }
}