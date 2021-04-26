package br.com.desafiostoom.application.test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles({"test"})
class AddressTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    @Rollback(false)
    void testCreate() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.post("/address/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new StringBuilder("{\"streetName\": \"Rua Antônio Dias\",")
                        .append("\"number\": \"162\",")
                        .append("\"neighbourhood\" : \"Santo Antônio\",")
                        .append("\"city\": \"Belo Horizonte\",")
                        .append("\"state\": \"MG\",")
                        .append("\"country\": \"Brasil\",")
                        .append(" \"zipcode\" : \"30350-150\"}")
                        .toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("streetName").value("Rua Antônio Dias"))
                .andExpect(jsonPath("number").value(162))
                .andExpect(jsonPath("complement").isEmpty())
                .andExpect(jsonPath("neighbourhood").value("Santo Antônio"))
                .andExpect(jsonPath("city").value("Belo Horizonte"))
                .andExpect(jsonPath("state").value("MG"))
                .andExpect(jsonPath("zipcode").value("30350-150"))
                .andExpect(jsonPath("latitude").isNumber())
                .andExpect(jsonPath("longitude").isNumber())
                .andExpect(jsonPath("id").isNumber());
    }

    @Test
    @Order(2)
    void testGet() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/address/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].streetName").value("Rua Antônio Dias"))
                .andExpect(jsonPath("$[0].number").value(162))
                .andExpect(jsonPath("$[0].complement").isEmpty())
                .andExpect(jsonPath("$[0].neighbourhood").value("Santo Antônio"))
                .andExpect(jsonPath("$[0].city").value("Belo Horizonte"))
                .andExpect(jsonPath("$[0].state").value("MG"))
                .andExpect(jsonPath("$[0].zipcode").value("30350-150"))
                .andExpect(jsonPath("$[0].latitude").isNumber())
                .andExpect(jsonPath("$[0].longitude").isNumber())
                .andExpect(jsonPath("$[0].id").isNumber());
    }

    @Test
    @Order(3)
    void testUpdate() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.put("/address/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new StringBuilder("{\"streetName\": \"Rua Antônio Dias\",")
                        .append("\"number\": \"502\",")
                        .append("\"neighbourhood\" : \"Santo Antônio\",")
                        .append("\"city\": \"Belo Horizonte\",")
                        .append("\"state\": \"Minas Gerais\",")
                        .append("\"country\": \"Brasil\",")
                        .append(" \"zipcode\" : \"30350-150\"}")
                        .toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("streetName").value("Rua Antônio Dias"))
                .andExpect(jsonPath("number").value(502))
                .andExpect(jsonPath("complement").isEmpty())
                .andExpect(jsonPath("neighbourhood").value("Santo Antônio"))
                .andExpect(jsonPath("city").value("Belo Horizonte"))
                .andExpect(jsonPath("state").value("Minas Gerais"))
                .andExpect(jsonPath("zipcode").value("30350-150"))
                .andExpect(jsonPath("latitude").isNumber())
                .andExpect(jsonPath("longitude").isNumber())
                .andExpect(jsonPath("id").value(1));
    }

    @Test
    @Order(4)
    void testDelete() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/address/1"))
                .andExpect(status().isOk());
        this.mvc.perform(MockMvcRequestBuilders.get("/address/"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

}
