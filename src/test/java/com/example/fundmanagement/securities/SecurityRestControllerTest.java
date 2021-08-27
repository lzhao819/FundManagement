package com.example.fundmanagement.securities;

import com.example.fundmanagement.FundManagementApplication;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FundManagementApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SecurityRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SecurityServiceImpl securityService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Order(1)
    @Test
    void getAllSecurities() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/securities")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String responseString = mvcResult.getResponse().getContentAsString();


        Assert.assertEquals("wrong request",200,status);
       // Assert.assertEquals("unexpecting result",securityService.getAllSecurities(),responseString);
    }
    @Order(2)
    @Test
    void getSecurity()throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/securities/1")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String responseString = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals("wrong request",200,status);
        Assert.assertEquals("unexpecting result",securityService.findSecurity(1).toString(),responseString);
    }
    @Order(3)
    @Test
    void addSecurity() throws Exception{
        String requestbody ="Test";
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/securities")
                                .contentType(MediaType.APPLICATION_JSON).content(requestbody)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("failed",200,status);
    }
    @Order(4)
    @Test
    void modifySecurity() throws Exception{
        int security_id = securityService.findSecurityBySymbol("Test");
        String requestbody = "Test2";
        String url = "/securities/"+security_id;
        System.out.println(url);
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.put(url)
                                .contentType(MediaType.APPLICATION_JSON).content(requestbody)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("failed",200,status);
    }
    @Order(5)
    @Test
    void removeSecurity()throws Exception {
        int security_id = securityService.findSecurityBySymbol("Test2");
        String url = "/securities/"+security_id;

        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.delete(url)
                                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("failed",200,status);
    }


}