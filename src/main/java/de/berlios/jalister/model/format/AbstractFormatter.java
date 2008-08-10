/*
 * FormatterImpl.java
 *
 * Created on 3 Сентябрь 2007 г., 20:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.berlios.jalister.model.format;

/**
 * @author schakal
 */
public abstract class AbstractFormatter<Source, Target> implements Formatter<Source, Target> {

    /**
     * Creates a new instance of FormatterImpl
     */
    public AbstractFormatter() {
    }

    public final Target format(final Source source) {
        return transform(source);
    }

}
