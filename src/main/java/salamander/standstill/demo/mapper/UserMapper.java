package salamander.standstill.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import salamander.standstill.demo.model.User;

import javax.annotation.security.PermitAll;

@Repository
@Mapper
public interface UserMapper {
    @Insert("insert into user (ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{ACCOUNT_ID},#{NAME},#{TOKEN},#{GMT_CREATE},#{GMT_MODIFIED})")
    void insert(User user);

    @Select("select * from user where TOKEN=#{TOKEN}")
    User findByToken(@Param("TOKEN") String token);
}
