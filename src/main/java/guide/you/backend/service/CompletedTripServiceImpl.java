package guide.you.backend.service;

import guide.you.backend.dao.CompletedTripRepository;
import org.springframework.stereotype.Service;

@Service
public class CompletedTripServiceImpl {

    private final CompletedTripRepository completedTripRepository;

    public CompletedTripServiceImpl(CompletedTripRepository completedTripRepository) {
        this.completedTripRepository = completedTripRepository;
    }
}
