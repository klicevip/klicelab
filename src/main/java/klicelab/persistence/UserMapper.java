package klicelab.persistence;

import klicelab.model.User;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;

/**
 * Created by hasee on 2017/4/26.
 */

public interface UserMapper {
//    @Select("select * from user where id=#{id}")
    User getUser(int id);

//    @Insert("insert into user (name,password,email,createTime) values (#{name},#{password},#{email},#{createTime})")
    void register(User user) throws DbException;

    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    User getByName(String name);
}
