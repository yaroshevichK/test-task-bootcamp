package it.bootcamp;

public class Constants {
    public static final String REST_API = "/api";
    public static final String REST_USERS = "/users";
    public static final String MESSAGE_EMPTY_USERS_LIST = "Список пользователей в базе данных пуст";
    public static final String MESSAGE_BAD_PAGE_NUMBER = "Номер страницы больше общего количества (%d)";
    public static final String LOG_NEW_USER = "new user for adding in database {} ";
    public static final String LOG_USER_BY_EMAIL = "find user by email user {} ";
    public static final String LOG_EMAIL_NOT_UNIQUE = "Email пользователя не уникальный: %s";
    public static final String LOG_SAVE_USER = "new user {} saved in DB";
    public static final String NAME_API_NEW_USER = "Добавление пользователя";
    public static final String NAME_API_LIST_USERS = "Получение списка всех пользователей";
    public static final String NAME_API = "Users Endpoints";
    public static final String ATTR_PAGE_NUMBER = "pageNumber";
    public static final String DEFAULT_PAGE_NUMBER = "1";
}
