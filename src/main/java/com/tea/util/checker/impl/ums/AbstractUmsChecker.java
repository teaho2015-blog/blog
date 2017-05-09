package com.tea.util.checker.impl.ums;

import com.tea.manage.domain.User;
import com.tea.util.checker.Checker;
import com.tea.util.exception.IncompatibleObjectException;
import com.tea.util.valueObject.GenericFormat;

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
