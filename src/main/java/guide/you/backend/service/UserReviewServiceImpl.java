package guide.you.backend.service;

import guide.you.backend.dao.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserReviewServiceImpl {

    private final UserRepository userRepository;

    public UserReviewServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
