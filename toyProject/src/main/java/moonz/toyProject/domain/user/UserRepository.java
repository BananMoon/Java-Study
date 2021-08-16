package moonz.toyProject.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@NotEmpty String name);

    Optional<User> findByEmail(@NotNull String email);

    //Boolean existsByUsername(@NotEmpty String name);

    //회원가입 시
    Boolean existsByEmail(@NotNull String email);

    //lecture repository에 적어야하나?
    Optional<User> findByUsernameOrEmail(String name, String email);

}
