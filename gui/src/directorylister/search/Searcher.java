package directorylister.search;

import directorylister.model.FileEntry;
import directorylister.model.FileEntryVisitorAdapter;
import directorylister.model.JaListerDatabase;
import directorylister.model.Service;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.io.Serializable;

/**
 * Lucene searcher.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 31.07.2007 23:43:45
 */
public class Searcher implements Service<JaListerDatabase>, Serializable {

    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(Searcher.class);
    /**
     * Field directory
     */
    private transient RAMDirectory directory;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 5995659523609039692L;
    /**
     * Field database
     */
    private JaListerDatabase database;

    /**
     * Constructs a new Searcher.
     */
    public Searcher() {
        try {
            directory = new RAMDirectory(".");
        } catch(IOException e) {
            logger.error(e);
            throw new RuntimeException("Cannot initialize searcher", e);
        }

    }

    /**
     * Method search ...
     *
     * @param condition of type String
     * @return FileEntry
     */
    public SearchResult search(final String condition) {
        final SearchResult searchResult = new SearchResult();
        IndexSearcher indexSearcher = null;
        try {
            indexSearcher = new IndexSearcher(directory);
            final Hits hits = indexSearcher.search(buildQuery(condition));
            final IndexSearcherFileEntryVisitor fileEntryVisitor = new IndexSearcherFileEntryVisitor(hits);
            database.getRootEntry().acceptVisitor(fileEntryVisitor);

            searchResult.setRoot(fileEntryVisitor.getRoot());
            return searchResult;
        } catch(IOException e) {
            logger.error(e);
        }
        finally {
            if (indexSearcher != null) {
                try {
                    indexSearcher.close();
                } catch(IOException e) {
                    logger.error(e);
                }
            }
        }
        return searchResult;
    }

    /**
     * Method buildQuery ...
     *
     * @param condition of type String
     * @return Query
     */
    private Query buildQuery(final String condition) {
        final String[] strings = StringUtils.split(condition.trim().toLowerCase());
        final BooleanQuery booleanQuery = new BooleanQuery();
        for (final String string : strings) {
            final String searchString = "*" + string + "*";

            final WildcardQuery fileNameQuery = new WildcardQuery(buildTerm(SearchField.FILE_NAME, searchString));
            booleanQuery.add(fileNameQuery, BooleanClause.Occur.SHOULD);

            final WildcardQuery shortNameQuery = new WildcardQuery(buildTerm(SearchField.SHORT_NAME, searchString));
            booleanQuery.add(shortNameQuery, BooleanClause.Occur.SHOULD);

            final TermQuery query = new TermQuery(buildTerm(SearchField.SHORT_NAME, searchString));
            booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        }
        return booleanQuery;
    }

    /**
     * Method buildTerm ...
     *
     * @param shortName    of type SearchField
     * @param searchString of type String
     * @return Term
     */
    private Term buildTerm(final SearchField shortName, final String searchString) {
        return new Term(shortName.name(), searchString);
    }

    /**
     * @see directorylister.model.Nameable#getName()
     */
    public String getName() {
        return "Searcher";
    }


    /**
     * Method notifyAttached ...
     *
     * @param serviceable of type JaListerDatabase
     */
    public void notifyAttached(final JaListerDatabase serviceable) {
        database = serviceable;
        final FileEntry rootEntry = serviceable.getRootEntry();
        executeWithIndexWriter(new Executable<IndexWriter>() {
            /**
             * {@inheritDoc}
             */
            public void execute(final IndexWriter indexWriter) throws IOException {
                if (null != rootEntry) {
                    rootEntry.acceptVisitor(new IndexWriterFileEntryVisitor(indexWriter));
                    indexWriter.optimize();
                }
            }
        });
    }


    /**
     * Class IndexWriterFileEntryVisitor ...
     *
     * @author schakal
     *         Created on 05.08.2007
     */
    private class IndexWriterFileEntryVisitor extends FileEntryVisitorAdapter {
        /**
         * Field indexWriter
         */
        private final IndexWriter indexWriter;

        /**
         * Constructor IndexWriterFileEntryVisitor creates a new IndexWriterFileEntryVisitor instance.
         *
         * @param indexWriter of type IndexWriter
         */
        public IndexWriterFileEntryVisitor(final IndexWriter indexWriter) {
            this.indexWriter = indexWriter;
        }

        /**
         * {@inheritDoc}
         *
         * @see directorylister.model.FileEntryVisitorAdapter#acceptEntry(FileEntry)
         */
        @Override
        public void acceptEntry(final FileEntry fileEntry) {
            final Document document = buildDocument(fileEntry);
            try {
                indexWriter.addDocument(document);
            } catch(IOException e) {
                logger.error(e);

            }
        }

        /**
         * Method buildDocument ...
         *
         * @param fileEntry of type FileEntry
         * @return Document
         */
        private Document buildDocument(final FileEntry fileEntry) {
            final Document document = new Document();
            final SearchField[] searchFields = SearchField.values();
            for (final SearchField searchField : searchFields) {
                addField(document, searchField, fileEntry);
            }
            return document;
        }

        /**
         * Adds field to the document.
         *
         * @param document    - Lucene document.
         * @param searchField - search field to add.
         * @param fileEntry   - file entry to add to document.
         */
        private void addField(final Document document, final SearchField searchField, final FileEntry fileEntry) {
            final Field field = searchField.createField(fileEntry.getFileName());
            if (null != field) {
                document.add(field);
            }
        }

    }

    /**
     * Method executeWithIndexWriter ...
     *
     * @param executable of type Executable<IndexWriter>
     */
    private void executeWithIndexWriter(final Executable<IndexWriter> executable) {
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(directory, new StandardAnalyzer());
            executable.execute(indexWriter);

        } catch(IOException e) {
            logger.error(e);
        } finally {
            if (indexWriter != null) {
                try {
                    indexWriter.close();
                } catch(IOException e) {
                    logger.error(e);
                }
            }
        }
    }
}
