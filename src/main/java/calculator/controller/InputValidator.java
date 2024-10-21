package calculator.controller;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputValidator {
    private static final String DEFAULT_DELIMITER_REGEX = "[^0-9"
            + Arrays.stream(Delimiter.values())
            .map(Delimiter::getDelimiter)
            .collect(Collectors.joining())
            + "]";

    private static final Pattern CUSTOM_DELIMITER_REGEX_PATTERN = Pattern.compile("^//.*\\n.*");
    private static final Pattern DEFAULT_DELIMITER_REGEX_PATTERN = Pattern.compile(DEFAULT_DELIMITER_REGEX);

    public void validate(String input) {
        // 커스텀 구분자를 지정하는가
        if (CUSTOM_DELIMITER_REGEX_PATTERN.matcher(input).matches()) {
            customDelimiter(input);
        }
        // 커스텀 구분자를 지정하지 않는데 기본 구분자를 제외한 문자가 존재하면
        else if (DEFAULT_DELIMITER_REGEX_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }

    private void customDelimiter(String input) {
        // 커스텀 구분자 길이가 2 이상이거나 없으면 예외 throw
        if (input.indexOf("\n") > 3 || input.indexOf("\n") == 2) {
            throw new IllegalArgumentException();
        }

        // 커스텀 구분자가 문자가 아니면 예외 throw
        if (!Character.isLetter(input.charAt(2))) {
            throw new IllegalArgumentException();
        }
    }
}
