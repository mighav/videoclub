package videoclub;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByUsername(String username);
	List<User> findById(long id);
}
