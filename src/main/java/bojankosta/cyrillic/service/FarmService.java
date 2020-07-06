package bojankosta.cyrillic.service;


import bojankosta.cyrillic.entity.Farm;
import bojankosta.cyrillic.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {

    @Autowired
    FarmRepository farmRepository;


    public Farm saveFarm (Farm farm) {
        return farmRepository.save(farm);
    }

    public Farm getFarmById (Long id) {
        return farmRepository.getById(id);
    }

    public List<Farm> getAllFarms () {
        return farmRepository.findAll();
    }

}
