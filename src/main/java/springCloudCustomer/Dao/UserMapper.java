package springCloudCustomer.Dao;

import org.springframework.stereotype.Component;
import springCloudCustomer.domain.User;
@Component
public interface UserMapper {

    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}