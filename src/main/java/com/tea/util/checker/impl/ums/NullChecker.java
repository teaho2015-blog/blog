package com.tea.util.checker.impl.ums;

import com.tea.frame.ums.model.User;
import org.springframework.util.StringUtils;

/**
 * Check null
 */
public class NullChecker extends AbstractUmsChecker {

    @Override
    public boolean isValid(User user) {
        return isNotNull(user.getEmail()) && isNotNull(user.getPassword());
    }

    private boolean isNotNull(String value) {
        return !StringUtils.isEmpty(value);
    }
}
