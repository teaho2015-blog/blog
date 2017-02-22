package com.tea.frame.checker.impl.ums;

import com.tea.frame.checker.Checker;
import com.tea.frame.exception.IncompatibleObjectException;
import com.tea.frame.ums.model.User;
import com.tea.frame.valueObject.GenericFormat;

/**
 * dedicated for UserInfo
 */
public abstract class AbstractUmsChecker implements Checker {
    @Override
    public boolean isValid(GenericFormat valueObject) {
        if(valueObject == null) {
            throw new NullPointerException("targetObject null");
        }else if(valueObject instanceof User){
            return isValid((User) valueObject);
        }
        throw new IncompatibleObjectException("object incompatible" , valueObject);
    }

    public abstract boolean isValid(User user);
}
