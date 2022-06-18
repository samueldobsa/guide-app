package guide.you.backend.service;

import guide.you.backend.dao.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository posts;



}
