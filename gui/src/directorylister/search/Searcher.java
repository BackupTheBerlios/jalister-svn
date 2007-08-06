package directorylister.search;

import directorylister.controllers.FileEntryController;
import directorylister.controllers.FileEntryListenerAdapter;
import directorylister.model.FileEntry;
import directorylister.model.FileEntryVisitorAdapter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.io.Serializable;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 31.07.2007 23:43:45
 */
public class Searcher implements Serializable {

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
     * Constructs a new Searcher.
     */
    public Searcher() {
        try {
            directory = new RAMDirectory(".");
        } catch(IOException e) {
            logger.error(e);
            throw new RuntimeException("Cannot initialize searcher", e);
        }

        FileEntryListener listener = new FileEntryListener();
        FileEntryController.getInstance().addListener(listener);

    }

    /**
     * Method search ...
     *
     * @param oldFileEntry of type FileEntry
     * @param condition    of type String
     * @return FileEntry
     */
    public FileEntry search(final FileEntry oldFileEntry, final String condition) {

        IndexSearcher indexSearcher = null;
        try {
            indexSearcher = new IndexSearcher(directory);
            final Hits hits = indexSearcher.search(buildQuery(condition));
            IndexSearcherFileEntryVisitor fileEntryVisitor = new IndexSearcherFileEntryVisitor(hits);
            oldFileEntry.acceptVisitor(fileEntryVisitor);

            return fileEntryVisitor.getRoot();
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
        return oldFileEntry;
    }

    /**
     * Method buildQuery ...
     *
     * @param condition of type String
     * @return Query
     */
    private Query buildQuery(String condition) {
        String[] strings = StringUtils.split(condition.trim().toLowerCase());
        BooleanQuery booleanQuery = new BooleanQuery();
        for (String string : strings) {
            final String searchString = "*" + string + "*";

            WildcardQuery fileNameQuery = new WildcardQuery(new Term(SearchField.FILE_NAME.name(), searchString));
            booleanQuery.add(fileNameQuery, BooleanClause.Occur.SHOULD);

            WildcardQuery shortNameQuery = new WildcardQuery(new Term(SearchField.SHORT_NAME.name(), searchString));
            booleanQuery.add(shortNameQuery, BooleanClause.Occur.SHOULD);

            TermQuery query = new TermQuery(new Term(SearchField.SHORT_NAME.name(), searchString));
            booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        }
        return booleanQuery;
    }

    /**
     * Class FileEntryListener ...
     *
     * @author schakal
     *         Created on 05.08.2007
     */
    private class FileEntryListener extends FileEntryListenerAdapter {
        /**
         * {@inheritDoc}
         *
         * @see directorylister.controllers.FileEntryListenerAdapter#notifyCurrentFileEntryChanged(FileEntry,FileEntry)
         */
        @Override
        public void notifyCurrentFileEntryChanged(FileEntry currentEntry, final FileEntry newEntry) {
            executeWithIndexWriter(new Executable<IndexWriter>() {
                /**
                 * {@inheritDoc}
                 */
                public void execute(IndexWriter indexWriter) throws IOException {
                    newEntry.acceptVisitor(new IndexWriterFileEntryVisitor(indexWriter));
                    indexWriter.optimize();
                }
            });
        }
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
        public void acceptEntry(FileEntry fileEntry) {
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
        private Document buildDocument(FileEntry fileEntry) {
            Document document = new Document();
            document.add(SearchField.ID.createField(fileEntry.getFileName()));
            document.add(SearchField.FILE_NAME.createField(fileEntry.getFileName()));
            document.add(SearchField.SHORT_NAME.createField(fileEntry.getShortName()));
            return document;
        }

    }

    /**
     * Method executeWithIndexWriter ...
     *
     * @param executable of type Executable<IndexWriter>
     */
    private void executeWithIndexWriter(Executable<IndexWriter> executable) {
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
