package ip91.spring.model.entity;

import ip91.spring.model.dto.PostDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Post {

    @Id
    @SequenceGenerator(
            name = "post_id_generator",
            sequenceName = "post_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_id_generator"
    )
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    public Post(PostDto postDto) {
        this.text = postDto.getText();
        this.creationDate = LocalDate.now();
    }

}
