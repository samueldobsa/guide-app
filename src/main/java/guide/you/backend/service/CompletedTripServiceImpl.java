package guide.you.backend.service;

import guide.you.backend.dao.CompletedTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletedTripServiceImpl {

    private final CompletedTripRepository completedTripRepository;

    @Autowired
    public CompletedTripServiceImpl(CompletedTripRepository completedTripRepository) {
        this.completedTripRepository = completedTripRepository;
    }
}
