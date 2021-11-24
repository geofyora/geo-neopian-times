package com.geo.neopian.times.parser

import com.geo.neopian.times.entry.NTEntry
import java.io.InputStream

interface Parser {

    fun parse(inputStream: InputStream): ArrayList<NTEntry>
}