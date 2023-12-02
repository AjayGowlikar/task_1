package work1.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import work1.registration.entity.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls,Integer> {
}
