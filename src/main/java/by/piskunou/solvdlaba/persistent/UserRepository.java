package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    Optional<User> findUserTickets(long id);

    void register(User user);

    void updateUsernameById(@Param("id") long id, @Param("username") String username);

    void removeById(long id);

    boolean isExistsById(long id);

    boolean isExistsByUsername(String username);

}
