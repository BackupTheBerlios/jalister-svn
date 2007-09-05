package directorylister.search;

import directorylister.model.FileEntry;
import directorylister.model.FileEntryVisitorAdapter;
import directorylister.model.JaListerDatabase;
import directorylister.model.Nameable;
import directorylister.model.Service;
import directorylister.model.metadata.FileEntryMetaData;
import directorylister.model.metadata.MetadataProviderFactory;
import directorylister.model.metadata.key.MetaDataKey;
import directorylister.model.metadata.key.SearchableMetaDataKey;
import directorylister.utils.SwingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Lucene searcher.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 31.07.2007 23:43:45
 */
public class Searcher implements Service<JaListerDatabase> {

    /**
     * Field logger
     */
    private static final Log logger = LogFactory.getLog(Searcher.class);
    /**
     * Field directory
     */
    private transient Directory directory;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = 5995659523609039692L;
    /**
     * Field database
     */
    private JaListerDatabase database;
    /**
     * Field MAX_CLAUSE_COUNT
     */
    private static final int MAX_CLAUSE_COUNT = 65536;

    /**
     * Constructs a new Searcher.
     */
    public Searcher() {
        try {
            // TODO:
            final File tempDir = new File(System.getProperty("java.io.tmpdir"), String.valueOf(Math.random()));
            tempDir.deleteOnExit();
            tempDir.mkdirs();
            directory = FSDirectory.getDirectory(tempDir);
//            directory = new RAMDirectory(".");

        }
        catch(IOException e) {
            logger.error(e);
            throw new RuntimeException("Cannot initialize searcher", e);
        }
        BooleanQuery.setMaxClauseCount(MAX_CLAUSE_COUNT);
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
            final long startTime = System.currentTimeMillis();
            final Hits hits = indexSearcher.search(buildQuery(condition));
            searchResult.setSearchTime(System.currentTimeMillis() - startTime);
            searchResult.setResultCount(hits.length());
            final IndexSearcherFileEntryVisitor fileEntryVisitor = new IndexSearcherFileEntryVisitor(hits);
            database.getRootEntry().acceptVisitor(fileEntryVisitor);

            searchResult.setRoot(fileEntryVisitor.getRoot());
            return searchResult;
        }
        catch(IOException e) {
            SwingUtils.showError(e.getMessage());
            logger.error(e);
        }
        finally {
            if (indexSearcher != null) {
                try {
                    indexSearcher.close();
                }
                catch(IOException e) {
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
        // TODO: Refactor and add support for query language in condition.
        final String[] strings = StringUtils.split(condition.trim().toLowerCase());
        final BooleanQuery booleanQuery = new BooleanQuery();

        final Collection<MetaDataKey> metaDataKeys = MetadataProviderFactory.getInstance().getMetaDataKeys();

        for (final String string : strings) {
            final String searchString = "*" + string + "*";

            final WildcardQuery fileNameQuery = new WildcardQuery(buildTerm(SearchField.FILE_NAME, searchString));
            booleanQuery.add(fileNameQuery, BooleanClause.Occur.SHOULD);

            final WildcardQuery shortNameQuery = new WildcardQuery(buildTerm(SearchField.SHORT_NAME, searchString));
            booleanQuery.add(shortNameQuery, BooleanClause.Occur.SHOULD);

            final TermQuery termQuery = new TermQuery(buildTerm(SearchField.SHORT_NAME, searchString));
            booleanQuery.add(termQuery, BooleanClause.Occur.SHOULD);

            for (final MetaDataKey metaDataKey : metaDataKeys) {
                if (metaDataKey instanceof SearchableMetaDataKey) {
                    final SearchableMetaDataKey key = (SearchableMetaDataKey) metaDataKey;
                    final WildcardQuery query = new WildcardQuery(buildTerm(key.getName(), searchString));
                    booleanQuery.add(query, BooleanClause.Occur.SHOULD);
                }
            }
        }
        return booleanQuery;
    }

    private static Term buildTerm(final String name, final String searchString) {
        return new Term(name, searchString);
    }

    /**
     * Method buildTerm ...
     *
     * @param searchField  of type SearchField
     * @param searchString of type String
     * @return Term
     */
    private static Term buildTerm(final SearchField searchField, final String searchString) {
        return new Term(searchField.name(), searchString);
    }

    /**
     * @see Nameable#getName()
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
        executeWithIndexWriter(new IndexWriterExecutable(rootEntry));
    }


    /**
     * Class IndexWriterFileEntryVisitor ...
     *
     * @author schakal
     *         Created on 05.08.2007
     */
    private static class IndexWriterFileEntryVisitor extends FileEntryVisitorAdapter {
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
         * @see FileEntryVisitorAdapter#acceptEntry(FileEntry)
         */
        @Override
        public void acceptEntry(final FileEntry fileEntry) {
            final Document document = buildDocument(fileEntry);
            try {
                indexWriter.addDocument(document);
            }
            catch(IOException e) {
                SwingUtils.showError(e.getMessage());
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

            final Collection<FileEntryMetaData> metaDatas = fileEntry.getMetadatas();
            for (final FileEntryMetaData metaData : metaDatas) {
                if (metaData.getKey() instanceof SearchableMetaDataKey) {
                    final SearchableMetaDataKey key = (SearchableMetaDataKey) metaData.getKey();
                    final Field field = key.createField(String.valueOf(metaData.getValue().getValue()));
                    if (null != field) {
                        document.add(field);
                    }
                }
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
    private void executeWithIndexWriter(final Executable<IndexWriter, IOException> executable) {
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(directory, new StandardAnalyzer());
            //indexWriter.setMergeFactor(DEFAULT_MERGE_FACTOR);
            executable.execute(indexWriter);

        }
        catch(IOException e) {
            SwingUtils.showError(e.getMessage());
            logger.error(e);
        }
        finally {
            if (indexWriter != null) {
                try {
                    indexWriter.close();
                }
                catch(IOException e) {
                    logger.error(e);
                }
            }
        }
    }

    private static class IndexWriterExecutable implements Executable<IndexWriter, IOException> {
        private final FileEntry rootEntry;

        public IndexWriterExecutable(final FileEntry rootEntry) {
            this.rootEntry = rootEntry;
        }

        /**
         * {@inheritDoc}
         */
        public void execute(final IndexWriter indexWriter) throws IOException {
            if (null != rootEntry) {
                rootEntry.acceptVisitor(new IndexWriterFileEntryVisitor(indexWriter));
                indexWriter.optimize();
            }
        }
    }
}
