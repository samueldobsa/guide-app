package guide.you.backend.service;

import guide.you.backend.dao.FavoriteTripRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteTripServiceImpl {

    private final FavoriteTripRepository favoriteTripRepository;

    public FavoriteTripServiceImpl(FavoriteTripRepository favoriteTripRepository) {
        this.favoriteTripRepository = favoriteTripRepository;
    }
}
