package bojankosta.cyrillic.repository;

import bojankosta.cyrillic.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
    Farm getById(Long id);

}
