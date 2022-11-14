package guide.you.backend.service;

import guide.you.backend.dao.FavoriteTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteTripServiceImpl {

    private final FavoriteTripRepository favoriteTripRepository;

    @Autowired
    public FavoriteTripServiceImpl(FavoriteTripRepository favoriteTripRepository) {
        this.favoriteTripRepository = favoriteTripRepository;
    }
}
