package com.bingo.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final String PY_URL = System.getProperty("user.dir");
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Role{
        int ROLE_CUSTOMER = 0;
        int ROLE_ADMIN = 1;
    }

    public interface Sex{
        int SEX_MALE = 0;
        int SEX_FEMALE = 1;
    }
}
