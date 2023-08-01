package it.bootcamp;

public class Constants {
    //data database
    public static final String DB_USERS = "users";
    public static final String DB_ROLES = "roles";
    public static final String DB_FIRST_NAME = "first_name";
    public static final String DB_LAST_NAME = "last_name";
    public static final String DB_MIDDLE_NAME = "middle_name";
    public static final String DB_ROLE_ID = "role_id";

    //validation
    public static final String NOT_BLANK = "Поле не должно быть пустым";
    public static final String REG_LATIN = "[a-zA-Z]*";
    public static final String MESSAGE_SYMBOL = "Только латинские буквы";

    public static final int MIN_LENGTH_NAME = 3;
    public static final int MAX_LENGTH_NAME = 20;
    public static final String MESSAGE_LENGTH_NAME = "Длина имени должна быть от 3 до 20 символов";

    public static final int MIN_LENGTH_SURNAME = 3;
    public static final int MAX_LENGTH_SURNAME = 40;
    public static final String MESSAGE_LENGTH_SURNAME = "Длина фамилии должна быть от 3 до 40 символов";

    public static final int MIN_LENGTH_MIDDLE_NAME = 3;
    public static final int MAX_LENGTH_MIDDLE_NAME = 20;
    public static final String MESSAGE_LENGTH_MIDDLE_NAME = "Длина отчества должна быть от 3 до 20 символов";

    public static final String ADMIN_ROLE = "Administrator";
    public static final String SALE_ROLE = "Sale User";
    public static final String CUSTOMER_ROLE = "Customer User";
    public static final String SECURE_API_ROLE = "Secure API User";
    public static final String MESSAGE_ROLES = "Наименование роли должно быть из списка 'Administrator,Sale User,Customer User,Secure API User'";
}
