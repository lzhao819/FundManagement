package com.example.fundmanagement.manager;

import com.example.fundmanagement.FundManagementApplication;
import com.example.fundmanagement.securities.SecurityServiceImpl;
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
class ManagerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private SecurityServiceImpl securityService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Order(10)
    @Test
    void getManagers() throws Exception{
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/managers")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("wrong request",200,status);

    }

    @Order(11)
    @Test
    void getManager() throws Exception{
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/managers/1")
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();


        Assert.assertEquals("wrong request",200,status);
    }

    @Order(12)
    @Test
    void registerNewManager()throws Exception {
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

    @Order(13)
    @Test
    void updateManager()throws Exception {
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

    @Order(14)
    @Test
    void deleteManager() throws Exception{
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