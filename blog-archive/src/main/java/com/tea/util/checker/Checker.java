/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-4-23
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
package com.tea.util.checker;

import com.tea.util.valueObject.GenericFormat;

public interface Checker {
    /**
     * check fields are valid
     * */
    <T extends GenericFormat> boolean isValid(T valueObject);

}
