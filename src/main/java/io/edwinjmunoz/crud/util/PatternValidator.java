package io.edwinjmunoz.crud.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PatternValidator {


    public static final String DEFAULT_EMAIL_PATTERN = "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$";

    public static final String DEFAULT_PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    private PatternValidator() {
    }

    public static boolean validateEmail(String emailPattern, String email) {
        Pattern pattern;
        if (emailPattern != null && !emailPattern.trim().isEmpty()) {
            pattern = Pattern.compile(emailPattern);
        } else {
            pattern = Pattern.compile(DEFAULT_EMAIL_PATTERN);
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static boolean validatePassword(String passwordPattern, String password) {
        Pattern pattern;
        if (passwordPattern != null && !passwordPattern.trim().isEmpty()) {
            pattern = Pattern.compile(passwordPattern);
        } else {
            pattern = Pattern.compile(DEFAULT_PASSWORD_PATTERN);
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
