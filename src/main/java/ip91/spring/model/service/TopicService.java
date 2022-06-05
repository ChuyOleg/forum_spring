package ip91.spring.model.service;

import ip91.spring.model.dto.TopicDto;
import ip91.spring.model.entity.Category;
import ip91.spring.model.entity.Post;
import ip91.spring.model.entity.Topic;
import ip91.spring.model.entity.User;
import ip91.spring.model.repository.TopicRepository;
import ip91.spring.model.service.util.filter.SearchCriteria;
import ip91.spring.model.service.util.filter.SearchOperation;
import ip91.spring.model.service.util.filter.TopicSpecification;
import ip91.spring.model.service.util.pagination.TopicPaginationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
@Log
public class TopicService {

    private final TopicRepository topicRepository;

    private static final int PAGE_SIZE = 4;
    private static final String CREATION_DATE = "creationDate";
    private static final String ID = "id";
    private static final String CATEGORY = "category";
    private static final String IS_ACTUAL = "actual";

    @Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = true)
    public TopicPaginationInfo getPaginationResultData(String[] categoryArray, String isActual, int uiPageNumber) {
        TopicPaginationInfo paginationInfo = new TopicPaginationInfo();

        TopicSpecification specification = buildSpecification(categoryArray, isActual);

        List<Topic> pageOfTopics = getPageBySpecification(specification, uiPageNumber);
        final int pagesNumber = getPagesCountBySpecification(specification);

        paginationInfo.setTopicList(pageOfTopics);
        paginationInfo.setPagesNumber(pagesNumber);

        return paginationInfo;
    }

    public Topic getById(Long id) {
        Topic topic = topicRepository.getById(id);
        topic.getPostList().sort((Post p1, Post p2) -> (int) DAYS.between(p1.getCreationDate(), p2.getCreationDate()));
        return topic;
    }

    public void create(TopicDto topicDto, Long creatorId) {
        Topic topic = new Topic(topicDto);
        User creator = User.builder().id(creatorId).build();

        topic.setCreator(creator);

        topicRepository.save(topic);
    }

    @Transactional
    public void update(TopicDto topicDto, Long topicId) {
        Topic topic = topicRepository.getById(topicId);

        topic.setTitle(topicDto.getTitle());
        topic.setCategory(Category.valueOf(topicDto.getCategory()));

        topicRepository.save(topic);
    }

    public void deleteById(Long id) {
        topicRepository.deleteById(id);
    }

    private List<Topic> getPageBySpecification(TopicSpecification specification, int uiPageNumber) {
        final int dbPageNumber = uiPageNumber - 1;
        Pageable pageRequest = PageRequest.of(dbPageNumber, PAGE_SIZE, Sort.by(IS_ACTUAL, CREATION_DATE, ID).descending());

        return topicRepository.findAll(specification, pageRequest).toList();
    }

    private int getPagesCountBySpecification(TopicSpecification specification) {
        final long topicsCount = topicRepository.count(specification);

        return (int) Math.ceil((double) topicsCount / PAGE_SIZE);
    }

    private TopicSpecification buildSpecification(String[] categoryArray, String isActual) {
        TopicSpecification topicSpecification = new TopicSpecification();

        if (categoryArray != null) {
            List<Category> categoryList = Arrays.stream(categoryArray)
                    .map(Category::valueOf)
                    .collect(Collectors.toList());
            topicSpecification.add(new SearchCriteria(CATEGORY, categoryList, SearchOperation.IN));
        }
        if (isActual != null && !isActual.isEmpty()) {
            final boolean isActualValue = Boolean.parseBoolean(isActual);
            topicSpecification.add(new SearchCriteria(IS_ACTUAL, isActualValue, SearchOperation.EQUAL));
        }

        return topicSpecification;
    }

}
