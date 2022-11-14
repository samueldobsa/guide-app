package guide.you.backend.service;

import guide.you.backend.dao.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuideServiceImpl {

    private final GuideRepository guideRepository;

    @Autowired
    public GuideServiceImpl(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }
}
