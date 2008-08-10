package de.berlios.jalister.model.transformers;

/**
 * Interface for defining transformation of one entities to another.
 *
 * @author Oleg Atamanenko dark.schakal@gmail.com
 * @version 1.0
 * @since 17.07.2007 0:02:21
 */
public interface Transformer<Source, Target> {
    /**
     * Performs transformation of object of class <code>Source</code>
     * to the object of class <code>Target</code>.
     *
     * @param source object to transform.
     * @return transformed object of class <code>Target</code>.
     */
    Target transform(Source source);
}
