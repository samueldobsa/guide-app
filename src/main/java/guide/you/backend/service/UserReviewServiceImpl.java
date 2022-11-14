package guide.you.backend.service;

import guide.you.backend.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReviewServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public UserReviewServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
