package directorylister.gui.components;

import directorylister.controllers.JaListerDatabaseController;
import directorylister.controllers.JaListerDatabaseListenerAdapter;
import directorylister.model.FileEntry;
import directorylister.model.metadata.FileEntryMetaData;
import directorylister.model.metadata.MetaDataKey;
import directorylister.model.metadata.MetaDataValue;
import directorylister.model.transformers.SortedMetaDataTransformer;
import directorylister.resources.Localizer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.Collection;

/**
 * Panel with metadata information.
 *
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 19.08.2007 2:22:21
 */
public final class MetaDataPanel extends JPanel {
    /**
     * Field localizer
     */
    private final Localizer localizer;
    /**
     * Field serialVersionUID
     */
    private static final long serialVersionUID = -4979679532670512231L;


    public MetaDataPanel() {
        localizer = new Localizer();


        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JaListerDatabaseController.getInstance().addListener(new JaListerDatabaseListenerAdapter() {
            /**
             * @see directorylister.controllers.JaListerDatabaseListenerAdapter#notifyFileEntrySelected(FileEntry)
             */
            @Override()
            public void notifyFileEntrySelected(final FileEntry fileEntry) {
                removeAll();
                final Collection<FileEntryMetaData> metaDatas = fileEntry.getMetadatas(new SortedMetaDataTransformer());
                for (final FileEntryMetaData metaData : metaDatas) {
                    final MetaDataKey key = metaData.getKey();
                    final MetaDataValue value = metaData.getValue();

                    final JLabel keyLabel = new JLabel();
                    keyLabel.setPreferredSize(new Dimension(150, 0));
                    keyLabel.setName(key.getLocalizationKey());

                    final JLabel valueLabel = new JLabel();
                    final String textValue = String.valueOf(value.getValue());
                    valueLabel.setText(textValue);
                    valueLabel.setToolTipText(textValue);

                    final JPanel panel = new JPanel();
                    final BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);

                    panel.setLayout(boxLayout);
                    panel.add(keyLabel);
                    panel.add(Box.createRigidArea(new Dimension(10, 0)));
                    panel.add(valueLabel);
                    panel.add(Box.createHorizontalGlue());
                    panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
                    add(panel);
                }

                localizer.localize(MetaDataPanel.this);
            }
        });
    }
}
