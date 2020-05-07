/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-4-23
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
package net.teaho.blog.server.util.checker;

import net.teaho.blog.server.util.valueObject.GenericFormat;

public interface Checker {
    /**
     * check fields are valid
     * */
    <T extends GenericFormat> boolean isValid(T valueObject);

}
