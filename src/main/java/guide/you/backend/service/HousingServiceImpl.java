package guide.you.backend.service;

import guide.you.backend.dao.HousingRepository;

public class HousingServiceImpl {

    private final HousingRepository housingRepository;

    public HousingServiceImpl(HousingRepository housingRepository) {
        this.housingRepository = housingRepository;
    }
}
