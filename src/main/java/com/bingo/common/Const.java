package com.bingo.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Role{
        int ROLE_CUSTOMER = 0;
        int ROLE_ADMIN = 1;
    }

    public interface Gender{
        int GENDER_MALE = 0;
        int GENDER_FEMALE = 1;
    }
}
