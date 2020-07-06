package bojankosta.cyrillic.repository;

import bojankosta.cyrillic.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Long> {
    Account getById(Long id);

}
