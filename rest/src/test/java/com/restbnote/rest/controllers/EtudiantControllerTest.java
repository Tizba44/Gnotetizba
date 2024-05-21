//package com.restbnote.rest.controllers;
//
//
//import com.restbnote.rest.services.EtudiantService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(EtudiantController.class)
//public class EtudiantControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private EtudiantService etudiantService; // Assuming there's a service class
//
//    @Test
//    public void testGetEtudiants() throws Exception {
//        mockMvc.perform(get("/etudiants"))
//               .andExpect(status().isOk())
//               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void testAddEtudiant() throws Exception {
//        String etudiantJson = "{\"id\": \"tst\", \"mailID\": \"etudian4@gmail.com\", \"nom\": \"tst\", \"prenom\": \"tst\", \"telephone\": \"0428459317\"}";
//        mockMvc.perform(post("/etudiants")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(etudiantJson))
//               .andExpect(status().isOk());
//    }
//}