/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-5-8
 * Time: 下午10:28
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.checker.impl.ums;

import com.tea.frame.ums.model.User;

import java.util.regex.Pattern;

public class EmailAddressChecker extends AbstractUmsChecker {
    private final Pattern pattern = Pattern.compile("([A-Z,a-z,0-9]*)@.*");

    @Override
    public boolean isValid(User user) {
        return user.getEmail() != null
                && pattern.matcher(user.getEmail()).matches();
    }
}
