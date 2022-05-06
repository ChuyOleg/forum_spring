package ip91.spring.model.service;

import ip91.spring.model.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

}
