package ip91.spring.model.service.util.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperation operation;

}
