package guide.you.backend.service;

import guide.you.backend.dao.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannedTripServiceImpl {

    private final TripRepository tripRepository;

    @Autowired
    public PlannedTripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

}
