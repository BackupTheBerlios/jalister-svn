package directorylister.model.transformers;

/**
 * @version 1.0
 * @author: Oleg Atamanenko dark.schakal@gmail.com
 * @since 17.07.2007 0:02:21
 */
public interface Transformer<Source, Target> {
    public Target transform(Source source);
}
