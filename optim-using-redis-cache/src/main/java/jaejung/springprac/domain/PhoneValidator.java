package jaejung.springprac.domain;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
