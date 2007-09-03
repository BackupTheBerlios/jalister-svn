/*
 * Formatter.java
 *
 * Created on 3 Сентябрь 2007 г., 20:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package directorylister.model.format;

import directorylister.model.transformers.Transformer;

/**
 * @author schakal
 */
public interface Formatter<Source, Target> extends Transformer<Source, Target> {
    Target format(Source source);
}
