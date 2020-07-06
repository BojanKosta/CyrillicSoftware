package bojankosta.cyrillic.repository;

import bojankosta.cyrillic.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {
    Customer getById(Long id);
}
