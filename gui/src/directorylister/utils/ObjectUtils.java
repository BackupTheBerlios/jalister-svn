package directorylister.utils;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author schakal Oleg Atamanenko
 * @since 01.04.2007 12:34:50
 */
public class ObjectUtils {
    /**
     * Do not instantiate ObjectUtils.
     */
    private ObjectUtils() {
    }

    /**
     * Method buildToString ...
     *
     * @param o of type Object
     * @return String
     */
    public static String buildToString(final Object o) {
        return ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE);
    }
}
