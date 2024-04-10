package net.teaho.blog.server.util.checker.impl.ums;


import net.teaho.blog.server.manage.domain.User;

/**
 * check if it fulfilled the required length
 */
public class TokenChecker extends AbstractUmsChecker {
    private final int LENGTH_LIMIT;

    public TokenChecker(int lengthLimit) {
        LENGTH_LIMIT = lengthLimit;
    }

    @Override
    public boolean isValid(User user) {
//        return user.getTokenID()!= null && user.getTokenID().length() >= LENGTH_LIMIT;
       return true;
    }
}
