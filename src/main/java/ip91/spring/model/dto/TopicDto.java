package ip91.spring.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TopicDto {

    @NotBlank
    @Size(min = 5, max = 256)
    private String title;

    @NotBlank
    @Size(min = 3, max = 256)
    private String category;

}
