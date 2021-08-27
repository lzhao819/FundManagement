package com.example.fundmanagement.fund;

import com.example.fundmanagement.FundManagementApplication;
import org.junit.Assert;
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FundControllerTest {


    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FundService fundService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Order(15)
    @Test
    void getFunds()throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/funds")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("wrong request",200,status);
    }

    @Order(16)
    @Test
    void getFund() throws Exception{
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/funds/1")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals("wrong request",200,status);

    }

    @Order(17)
    @Test
    void registerNewFund() throws Exception{

        String requestbody ="{\n" +
                "    \"fund_id\": 1000,\n" +
                "    \"name\": \"Test\",\n" +
                "    \"employee_id\": 1\n" +
                "}";
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/funds")
                                .contentType(MediaType.APPLICATION_JSON).content(requestbody)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals("failed",200,status);
    }

    @Order(18)
    @Test
    void deleteFund()  throws Exception{
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.delete("/funds/1000")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("failed",200,status);
    }

    @Order(19)
    @Test
    void updateFund() throws Exception{
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/funds/1")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals("wrong request",200,status);
    }
}