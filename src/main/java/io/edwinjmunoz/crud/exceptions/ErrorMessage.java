package io.edwinjmunoz.crud.exceptions;

public final class ErrorMessage {

    private ErrorMessage() {
    }

    public static final String EMAIL_FORMAT_ERROR = "The email format does not comply with the established conditions";

    public static final String PASSWORD_FORMAT_ERROR = "The password format does not comply with the established conditions";

    public static final String EMAIL_ALREADY_EXIST = "El correo ya registrado";

    public static final String NAME_ERROR = "The field name is required";

    public static final String PHONE_ERROR = "The phone is required";

    public static final String INVALID_USERNAME_PASSWORD = "Invalid username y/o password";

}
