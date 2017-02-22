/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-23
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.checker;

import com.tea.frame.valueObject.GenericFormat;

public interface Checker {
    /**
     * check fields are valid
     * */
    <T extends GenericFormat> boolean isValid(T valueObject);

}
