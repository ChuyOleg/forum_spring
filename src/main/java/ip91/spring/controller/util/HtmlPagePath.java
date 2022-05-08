package ip91.spring.controller.util;

public class HtmlPagePath {

    private HtmlPagePath() {}

    public static final String GUEST_PREFIX = "guest";
    public static final String USER_PREFIX = "user";
    public static final String ADMIN_PREFIX = "admin";

    public static final String GUEST_LOGIN_PAGE = GUEST_PREFIX + "/loginPage";
    public static final String GUEST_REGISTRATION_PAGE = GUEST_PREFIX + "/registrationPage";
    public static final String GUEST_TOPIC_CATALOG_PAGE = GUEST_PREFIX + "/topicCatalogPage";
    public static final String GUEST_POST_CATALOG_PAGE = GUEST_PREFIX + "/postCatalogPage";

    public static final String USER_CREATE_POST_PAGE = USER_PREFIX + "/postCreationPage";

    public static final String ADMIN_CREATE_TOPIC_PAGE = ADMIN_PREFIX + "topicCreationPage";
    public static final String ADMIN_EDIT_TOPIC_PAGE = ADMIN_PREFIX + "topicEditingPage";

/*
    Guest:
    GET: /login
    GET: /registration
    POST: /registration
    GET: /topics
    GET: /topics/topic_id/posts

    User:
    GET: /logout
    GET: /user/topics/topic_id/posts
    POST: /user/topics/topic_id/posts

    Admin:
    GET: /admin/topics
    POST: /admin/topics
    GET: /admin/topics/topic_id
    PUT: /admin/topics/topic_id
    DELETE: /admin/topics/topic_id

*/

}
