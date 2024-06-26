///**
// * @author teaho2015@gmail.com
// * since 2016/10/11
// */
//package net.teaho.blog.server.manage.service;
//
//import lombok.extern.slf4j.Slf4j;
//import net.teaho.blog.server.manage.dao.UserDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * 一个自定义的service用来和数据库进行操作. 即以后我们要通过数据库保存权限.则需要我们继承UserDetailsService
// *
// * @author liukai
// *
// */
////@Component
//@Slf4j
//public class CustomUserDetailsService implements UserDetailsService {
//
//
//    @Autowired
//    private UserDAO userDAO;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException, DataAccessException {
//
//        UserDetails user = null;
//
//        try {
//
//            // 搜索数据库以匹配用户登录名.
//            // 我们可以通过dao使用JDBC来访问数据库
//            net.teaho.blog.server.manage.domain.User dbUser = userDAO.getUserByUsername(username);
//
//            // Populate the Spring User object with details from the dbUser
//            // Here we just pass the username, password, and access level
//            // getAuthorities() will translate the access level to the correct
//            // role type
//
//            user = new User(dbUser.getUsername(), dbUser.getPassword()
//                    .toLowerCase(), true, true, true, true,
//                    getAuthorities(dbUser.getRole()));
//
//        } catch (Exception e) {
//            log.error("Error in retrieving user");
//            throw new UsernameNotFoundException("Error in retrieving user");
//        }
//
//        return user;
//    }
//
//    /**
//     * 获得访问角色权限
//     *
//     * @param access
//     * @return
//     */
//    public Collection<GrantedAuthority> getAuthorities(Integer access) {
//
//        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
//
//        // 所有的用户默认拥有ROLE_USER权限
//        log.debug("Grant ROLE_USER to this user");
//        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        // 如果参数access为1.则拥有ROLE_ADMIN权限
//        if (access.compareTo(1) == 0) {
//            log.debug("Grant ROLE_ADMIN to this user");
//            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }
//
//        return authList;
//    }
//
//    public UserDAO getUserDAO() {
//        return userDAO;
//    }
//
//    public void setUserDAO(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//}