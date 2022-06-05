package ip91.spring.controller.guest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ip91.spring.controller.util.HtmlPagePath;
import ip91.spring.controller.util.UriPath;
import ip91.spring.model.entity.Category;
import ip91.spring.model.service.TopicService;
import ip91.spring.model.service.util.pagination.TopicPaginationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;

import static ip91.spring.controller.util.Attribute.*;

@Controller
@RequiredArgsConstructor
@Log
public class GuestTopicController {

    private final TopicService topicService;

    @GetMapping(UriPath.TOPICS)
    @Operation(
            operationId = "topicCatalog",
            tags = {"GUEST-USER-ADMIN"},
            summary = "Get page of topics using filtration",
            responses = {@ApiResponse(responseCode = "200", description = "Successful request", content = {@Content(mediaType = MediaType.TEXT_HTML_VALUE)})}
    )
    public String getTopicCatalogPage(@RequestParam(name = CATEGORY, required = false) String[] categoryArray,
                                      @RequestParam(name = IS_ACTUAL, required = false) String isActual,
                                      @RequestParam(name = PAGE, required = false) String pageNumber,
                                      Model model) {

        final int activePageNumber = getActivePageNumber(pageNumber);

        TopicPaginationInfo paginationInfo = topicService.getPaginationResultData(categoryArray, isActual, activePageNumber);

        model.addAttribute(ACTIVE_PAGE_NUMBER, activePageNumber);
        model.addAttribute(PAGES_NUMBER, paginationInfo.getPagesNumber());
        model.addAttribute(TOPIC_LIST, paginationInfo.getTopicList());

        model.addAttribute(CATEGORY_LIST, Category.values());
        insertFilterParamsIntoModel(categoryArray, isActual, model);

        return HtmlPagePath.GUEST_TOPIC_CATALOG_PAGE;
    }

    private int getActivePageNumber(String pageNumber) {
        if (pageNumber != null && !pageNumber.equals("")) {
            return Integer.parseInt(pageNumber);
        } else {
            return START_PAGE_NUMBER;
        }
    }

    private void insertFilterParamsIntoModel(String[] categoryArray,
                                             String isActual,
                                             Model model) {

        model.addAttribute(IS_ACTUAL, isActual);
        if (categoryArray != null) model.addAttribute(CHECKED_CATEGORY_LIST, new ArrayList<>(Arrays.asList(categoryArray)));
    }

}
