package guide.you.backend.service;
import guide.you.backend.dao.TripRepository;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl {

    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }
}
