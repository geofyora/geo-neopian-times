package com.geo.neopian.times.entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NTEntry {

    private String title;
    private List<String> authors;
    private Date date;
    private String summary;
    private String contents;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthor() {
        return authors;
    }

    public Date getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }

    public String getContents() {
        return contents;
    }

    public static final class NTEntryBuilder {
        private String title;
        private Date date;
        private String summary;
        private String contents;
        private List<String> authors;

        private NTEntryBuilder() {
        }

        public static NTEntryBuilder createBuilder() {
            return new NTEntryBuilder();
        }

        public NTEntryBuilder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public NTEntryBuilder addAuthor(final String author) {
            if (authors == null) {
                authors = new ArrayList<>();
            }
            authors.add(author);
            return this;
        }

        public NTEntryBuilder withDate(final Date date) {
            this.date = date;
            return this;
        }

        public NTEntryBuilder withSummary(final String summary) {
            this.summary = summary;
            return this;
        }

        public NTEntryBuilder withContents(final String contents) {
            this.contents = contents;
            return this;
        }

        public NTEntry build() {
            NTEntry nTEntry = new NTEntry();
            nTEntry.summary = this.summary;
            nTEntry.title = this.title;
            nTEntry.authors = this.authors;
            nTEntry.contents = this.contents;
            nTEntry.date = this.date;
            return nTEntry;
        }
    }
}
