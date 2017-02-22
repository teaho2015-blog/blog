
package com.tea.frame.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tea.frame.ums.model.User;


/**
 * @author wushaojie
 *
 */
public class UserUtil {

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static final User getCurrentLoginUser(HttpServletRequest request) {
		return getCurrentLoginUser(request.getSession());
	}
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	public static final User getCurrentLoginUser(HttpSession session) {
		User user = (User) session.getAttribute(
				User.class.getName());
		if (user instanceof User) {
			return user;
		} else {
			return null;
		}
	}

    public static void removeCurrentLoginUser(HttpServletRequest request) {
        removeCurrentLoginUser(request.getSession());
    }

    public static void removeCurrentLoginUser(HttpSession session) {
        session.removeAttribute(User.class.getName());
    }
}
