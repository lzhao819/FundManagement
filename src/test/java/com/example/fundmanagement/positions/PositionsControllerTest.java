package com.example.fundmanagement.positions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PositionsController.class)
public class PositionsControllerTest {

    @MockBean
    PositionsService positionsService;

    @MockBean
    PositionsRepository positionsRepository;

    @Autowired
    MockMvc mockMvc;

    List<Positions> defaultPositions = List.of(
            new Positions(1,200,
                    LocalDate.of(1970, 1, 1),1,1),
        new Positions(2,400,
                LocalDate.of(1971, 2, 2),2,2)
    );

    @Test
    void getPositions() throws Exception{
        when(positionsService.getPositions(1)).thenReturn(defaultPositions.get(0));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/positions/1"))
                .andExpect(status().isOk());
    }
}