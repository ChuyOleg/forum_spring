package ip91.spring.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserDto {

    @NotBlank
    @Size(min = 5, max = 64)
    private String username;

    @NotBlank
    @Size(min = 8, max = 64)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).+$")
    private String password;

    private String passwordCopy;

    @NotBlank
    @Size(max = 128)
    @Pattern(regexp = "^[A-Za-z0-9._]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    private String email;

}
