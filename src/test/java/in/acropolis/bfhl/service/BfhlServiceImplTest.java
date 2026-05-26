package in.acropolis.bfhl.service;

import in.acropolis.bfhl.config.BfhlUserProperties;
import in.acropolis.bfhl.dto.BfhlRequest;
import in.acropolis.bfhl.dto.BfhlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BfhlServiceImplTest {

    private BfhlServiceImpl bfhlService;

    @BeforeEach
    void setUp() {
        BfhlUserProperties properties = new BfhlUserProperties();
        properties.setFullName("Anushka Yadav");
        properties.setEmail("anushkayadav230422@acropolis.in");
        properties.setRollNumber("0827IT231027");
        properties.setDob("18/02/2006");
        bfhlService = new BfhlServiceImpl(properties);
    }

    @Test
    void process_exampleA() {
        BfhlRequest request = requestOf("a", "1", "334", "4", "R", "$");
        BfhlResponse response = bfhlService.process(request);

        assertTrue(response.isSuccess());
        assertEquals("anushka_yadav_18022006", response.getUserId());
        assertEquals("anushkayadav230422@acropolis.in", response.getEmail());
        assertEquals("0827IT231027", response.getRollNumber());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    void process_exampleB() {
        BfhlRequest request = requestOf("2", "a", "y", "4", "&", "-", "*", "5", "92", "b");
        BfhlResponse response = bfhlService.process(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
        assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    @Test
    void process_exampleC() {
        BfhlRequest request = requestOf("A", "ABCD", "DOE");
        BfhlResponse response = bfhlService.process(request);

        assertTrue(response.isSuccess());
        assertEquals(Collections.emptyList(), response.getOddNumbers());
        assertEquals(Collections.emptyList(), response.getEvenNumbers());
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertEquals(Collections.emptyList(), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    void process_emptyData() {
        BfhlRequest request = new BfhlRequest();
        request.setData(Collections.emptyList());

        BfhlResponse response = bfhlService.process(request);

        assertTrue(response.isSuccess());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    private static BfhlRequest requestOf(String... items) {
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList(items));
        return request;
    }
}
