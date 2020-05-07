package net.teaho.blog.server.util.checker.impl.ums;

import net.teaho.blog.server.manage.domain.User;
import net.teaho.blog.server.util.checker.Checker;
import net.teaho.blog.server.util.exception.IncompatibleObjectException;
import net.teaho.blog.server.util.valueObject.GenericFormat;

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
