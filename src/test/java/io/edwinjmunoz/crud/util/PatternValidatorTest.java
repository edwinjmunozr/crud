package io.edwinjmunoz.crud.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatternValidatorTest {


    final static String CUSTOM_EMAIL_PATTERN_1 = "^([a-z]+[-._+&])*[a-z]+@([a-z]+[.])+[a-z]{2}$";

    static final String CUSTOM_PASSWORD_PATTERN_1 = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$";

    static final String CUSTOM_PASSWORD_PATTERN_2 = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*_)(?!.* ).{8,16}$";

    @Test
    void testValidateEmailPattern() {
        try {
            Assertions.assertTrue(PatternValidator.validateEmail(PatternValidator.DEFAULT_EMAIL_PATTERN, "edwin123@tucorreo.com"));
            Assertions.assertFalse(PatternValidator.validateEmail(PatternValidator.DEFAULT_EMAIL_PATTERN, "edwi1234NoDamain@tucorreocom"));

            Assertions.assertTrue(PatternValidator.validateEmail(CUSTOM_EMAIL_PATTERN_1, "edwin@tucorreo.co"));
            Assertions.assertFalse(PatternValidator.validateEmail(CUSTOM_EMAIL_PATTERN_1, "edwiN@tucorreo.co"));
        } catch (Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    void testValidatePasswordPattern() {
        try {
            Assertions.assertTrue(PatternValidator.validatePassword(PatternValidator.DEFAULT_PASSWORD_PATTERN, "ed!in1T3@tu.com"));
            Assertions.assertFalse(PatternValidator.validatePassword(PatternValidator.DEFAULT_PASSWORD_PATTERN, "eA1@#."));

            Assertions.assertTrue(PatternValidator.validatePassword(CUSTOM_PASSWORD_PATTERN_1, "Tetero1#com"));
            Assertions.assertFalse(PatternValidator.validatePassword(CUSTOM_PASSWORD_PATTERN_1, "casa12345#"));

            Assertions.assertTrue(PatternValidator.validatePassword(CUSTOM_PASSWORD_PATTERN_2, "edwin123A_com"));
            Assertions.assertFalse(PatternValidator.validatePassword(CUSTOM_PASSWORD_PATTERN_2, "1aB324!"));
        } catch (Exception ex) {
            Assertions.fail();
        }
    }

}
