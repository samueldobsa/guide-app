package guide.you.backend.service;

import guide.you.backend.dao.HousingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class HousingServiceImpl {

    private final HousingRepository housingRepository;

    @Autowired
    public HousingServiceImpl(HousingRepository housingRepository) {
        this.housingRepository = housingRepository;
    }
}
