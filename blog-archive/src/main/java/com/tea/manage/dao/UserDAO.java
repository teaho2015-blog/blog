/**
 * Created by 庭亮 on 2016/6/23.
 */
package com.tea.manage.dao;

import com.tea.manage.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserDAO {
   /* @Insert("INSERT INTO ${targetTable} (${targetFields}) Select ${sourceFields} from ${sourceTable} where id > ${idBegin} and id < ${idEnd}")
    public int moveData(@Param("sourceTable") String sourceTableName,@Param("targetTable") String targetTableName,
                        @Param("sourceFields") String sourceFields,@Param("targetFields") String targetFields,
                        @Param("idBegin")int idBegin,@Param("idEnd")int idEnd);
*/
    @Select("SELECT COUNT(*) FROM S_USER")
    public int getNum();

    @Select("SELECT * FROM S_USER WHERE id=#{id}")
    public User getUserById(User user);

    @Select("SELECT * FROM S_USER WHERE username=#{username} limit 1")
    public User getUserByUsername(String username);

}
