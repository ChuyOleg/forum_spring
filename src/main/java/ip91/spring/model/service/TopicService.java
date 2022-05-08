package ip91.spring.model.service;

import ip91.spring.model.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class TopicService {

    private final TopicRepository topicRepository;

}
