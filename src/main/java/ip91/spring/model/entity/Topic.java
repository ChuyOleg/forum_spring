package ip91.spring.model.entity;

import ip91.spring.model.dto.TopicDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "topics")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Topic {

    @Id
    @SequenceGenerator(
            name = "topic_id_generator",
            sequenceName = "topic_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "topic_id_generator"
    )
    private Long id;

    @Column(unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private LocalDate creationDate;

    @Column
    private boolean actual;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Post> postList;

    public Topic(TopicDto topicDto) {
        this.title = topicDto.getTitle();
        this.category = Category.valueOf(topicDto.getCategory());
        this.creationDate = LocalDate.now();
        this.actual = true;
    }

}
