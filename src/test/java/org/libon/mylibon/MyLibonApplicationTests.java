package org.libon.mylibon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.libon.mylibon.model.*;
import org.libon.mylibon.service.MowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.libon.mylibon.model.Orientation.E;
import static org.libon.mylibon.model.Orientation.N;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class MyLibonApplicationTests {
    private MowerService mowerService = new MowerService();
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void turnRight() {
        assertEquals(E, N.turnRight());
    }

    @Test
    void turnLeft() {
        assertEquals(Orientation.W, N.turnLeft());
    }

    @Test
    void testGetThePositionsAndOrientations() {
        Field field = new Field(5, 5);
        Position position1 = new Position(1, 2, N);
        Position position2 = new Position(3, 3, E);
        List<String> instructionsListForMowerOne = List.of("G", "A", "G", "A", "G", "A", "G", "A", "A");
        List<String> instructionsListForMowerTwo = List.of("A", "A", "D", "A", "A", "D", "A", "D", "D");
        MowerDto mowerDto1 = new MowerDto("1", instructionsListForMowerOne, position1, position1.getOrientation().toString());
        MowerDto mowerDto2 = new MowerDto("2", instructionsListForMowerTwo, position2, position2.getOrientation().toString());
        LibonRequest libonRequest = new LibonRequest(field, List.of(mowerDto1, mowerDto2));
        List<MowerPositionResponse> mowersPositions = mowerService.getTheMowersPostions(libonRequest);

        assertEquals(2, mowersPositions.size());
        commonTestsNewPositions(mowersPositions);
    }

    void testWebEndpoint() throws JsonProcessingException {
        String libonRequestMowerPosition = """
                    {
                        "field": {
                            "max_x": 5,
                            "max_y": 5
                        },
                        "mowers": [
                            {
                                "id": "mower1",
                                "start_position": {
                                    "x": 1,
                                    "y": 2
                                },
                                "orientation": "N",
                                "instructions": ["G", "A", "G", "A", "G", "A", "G", "A", "A"]
                            },
                            {
                                "id": "mower2",
                                "start_position": {
                                    "x": 3,
                                    "y": 3
                                },
                                "orientation": "E",
                                "instructions": ["A", "A", "D", "A", "A", "D", "A", "D", "D", "A"]
                            }
                        ]
                    }
                """;




        // Call the endpoint POST libon/api/mowers
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8090/libon/api/mowers",
                libonRequestMowerPosition,
                String.class
        );
        assertEquals(200, response.getStatusCode().value());
        // ObjectMapper instantiation
        ObjectMapper objectMapper = new ObjectMapper();
        MowerPositionResponse[] mowerDtos = objectMapper.readValue(response.getBody(), MowerPositionResponse[].class);

        List<MowerPositionResponse> mowerPositionResponseList = Arrays.stream(mowerDtos).toList();
        commonTestsNewPositions(mowerPositionResponseList);


    }

    void testWebEndpointWithBadRequest() {
        String libonRequestMowerPosition = """
                        {
                            "field": {
                                "max_x": 5,
                                "max_y": 5
                            }
                
                
                }""";

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8090/libon/api/mowers",
                libonRequestMowerPosition,
                String.class
        );
        assertEquals(400, response.getStatusCode().value());


    }

    void commonTestsNewPositions(List<MowerPositionResponse> mowersNewPositions) {
        for (MowerPositionResponse mowerPositionResponse : mowersNewPositions) {
            if (mowerPositionResponse.getId().equals("mower1")) {
                assertEquals(1, mowerPositionResponse.getPosition().getX());
                assertEquals(3, mowerPositionResponse.getPosition().getX());
                assertEquals("N", mowerPositionResponse.getPosition().getOrientation().toString());
            }
            if (mowerPositionResponse.getId().equals("mower2")) {
                assertEquals(5, mowerPositionResponse.getPosition().getX());
                assertEquals(1, mowerPositionResponse.getPosition().getX());
                assertEquals("E", mowerPositionResponse.getPosition().getOrientation().toString());
            }
        }
    }


}
