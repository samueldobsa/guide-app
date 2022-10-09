package guide.you.backend.service;

import org.springframework.stereotype.Service;

@Service
public class PlannedTripServiceImpl {

    private final PlannedTripServiceImpl plannedTripService;

    public PlannedTripServiceImpl(PlannedTripServiceImpl plannedTripService) {
        this.plannedTripService = plannedTripService;
    }
}
