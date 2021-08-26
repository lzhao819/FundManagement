package com.example.fundmanagement.positions;

import com.example.fundmanagement.FundManagementApplication;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FundManagementApplication.class)
class PositionsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PositionsService positionsService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPositions()throws Exception  {
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/positions/1")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("wrong request",200,status);

    }

    @Test
    void postNewPositions()throws Exception  {
        String requestbody = "{\"security_id\":10,\"symbol\":\"Test\"}";
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/securities")
                                .contentType(MediaType.APPLICATION_JSON).content(requestbody)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("failed",200,status);
    }



    @Test
    void updatePositions() throws Exception {
        String requestbody = "{\"security_id\":10,\"symbol\":\"Test1\"}";
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/securities/10")
                                .contentType(MediaType.APPLICATION_JSON).content(requestbody)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("failed",200,status);
    }
    @Test
    void deletePositions()throws Exception  {
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.delete("/securities/10")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("failed",200,status);
    }
}