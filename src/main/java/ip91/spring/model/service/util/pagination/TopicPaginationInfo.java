package ip91.spring.model.service.util.pagination;

import ip91.spring.model.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicPaginationInfo {

    private List<Topic> topicList;
    private int pagesNumber;

}
