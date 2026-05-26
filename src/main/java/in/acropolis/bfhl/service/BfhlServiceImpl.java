package in.acropolis.bfhl.service;

import in.acropolis.bfhl.config.BfhlUserProperties;
import in.acropolis.bfhl.dto.BfhlRequest;
import in.acropolis.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private final BfhlUserProperties userProperties;

    public BfhlServiceImpl(BfhlUserProperties userProperties) {
        this.userProperties = userProperties;
    }

    @Override
    public BfhlResponse process(BfhlRequest request) {
        List<String> data = request.getData() != null ? request.getData() : Collections.emptyList();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        List<Character> alphaCharsForConcat = new ArrayList<>();
        long sum = 0;

        for (String item : data) {
            if (item == null || item.isEmpty()) {
                continue;
            }

            if (isNumeric(item)) {
                long value = Long.parseLong(item);
                sum += value;
                if (value % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetic(item)) {
                alphabets.add(item.toUpperCase());
                for (char c : item.toCharArray()) {
                    if (Character.isLetter(c)) {
                        alphaCharsForConcat.add(c);
                    }
                }
            } else {
                specialCharacters.add(item);
            }
        }

        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(buildUserId());
        response.setEmail(userProperties.getEmail());
        response.setRollNumber(userProperties.getRollNumber());
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(sum));
        response.setConcatString(buildConcatString(alphaCharsForConcat));
        return response;
    }

    private String buildUserId() {
        String namePart = userProperties.getFullName()
                .trim()
                .toLowerCase()
                .replaceAll("\\s+", "_");
        String dobPart = userProperties.getDob()
                .replace("/", "");
        return namePart + "_" + dobPart;
    }

    private boolean isNumeric(String value) {
        if (value.startsWith("-")) {
            return value.length() > 1 && value.substring(1).chars().allMatch(Character::isDigit);
        }
        return value.chars().allMatch(Character::isDigit);
    }

    private boolean isAlphabetic(String value) {
        return value.chars().allMatch(Character::isLetter);
    }

    private String buildConcatString(List<Character> chars) {
        if (chars.isEmpty()) {
            return "";
        }

        List<Character> reversed = new ArrayList<>(chars);
        Collections.reverse(reversed);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.size(); i++) {
            char c = reversed.get(i);
            result.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        return result.toString();
    }
}
