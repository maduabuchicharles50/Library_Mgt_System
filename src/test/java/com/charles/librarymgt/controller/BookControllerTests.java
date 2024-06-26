//package com.charles.librarymgt.controller;
//
//import com.charles.librarymgt.models.Book;
//import com.charles.librarymgt.repository.BookRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@SpringBootTest
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//public class BookControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Test
//    void  contextLoads() {
//
//    }
//    @Test
//    public void givenBook_whenGetBooks_thenReturnJsonArray() throws Exception {
//        var book = bookRepository.save(new Book("Alex", "Rare Man", "111111111", "2016"));
//
//        mockMvc.perform(
//                        get("/api/books")
//                ).andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].title", is("Rare Man")))
//                .andExpect(jsonPath("$[0].author", is("Alex")))
//                .andExpect(jsonPath("$[0].isbn", is("111111111")));
//    }
//
//    @Test
//    public void givenBook_whenGetBook_thenReturnJson() throws Exception {
//        var book = bookRepository.save(new Book("Rare Man", "Alex George", "111111111", "2016"));
//
//        mockMvc.perform(
//                        get("/api/books/{id}", book.getId())
//                                .contentType(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isOk())
//                .andExpect(jsonPath("$.title", is("Rar Man")))
//                .andExpect(jsonPath("$.author", is("Alex George ")))
//                .andExpect(jsonPath("$.isbn", is("111111111")));
//    }
//
//    @Test
//    public void givenBook_whenPostBook_thenStoreBook() throws Exception {
//        var book = new Book("Rare Man", "Alex George", "111111111", "2016");
//
//        var payload = objectMapper.writeValueAsString(book);
//
//        mockMvc.perform(
//                post("/api/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(payload)
//        ).andExpect(status().isOk());
//
//        Assertions.assertEquals(1, bookRepository.count(), "Check if book was actually stored in the Database");
//    }
//
//    @Test
//    public void givenBook_whenPutBook_thenUpdateBook() throws Exception {
//        var book = bookRepository.save(new Book("Game of Thrones", "John Wick", "1288-2828-2228", "2016"));
//
//        var updateBook = new Book("Game of Thrones updated", "John Walker", "1233-3423-4545", "2018");
//
//        var updatePayload = objectMapper.writeValueAsString(updateBook);
//
//        mockMvc.perform(
//                        put("/api/books/{id}", book.getId())
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(updatePayload)
//                ).andExpect(status().isOk())
//                .andExpect(jsonPath("$.title", is("Game of Thrones updated")));
//
//        Assertions.assertEquals("Game of Thrones updated", bookRepository.findById(book.getId()).get().getTitle(), "Confirm that book title was updated in the DB");
//        Assertions.assertEquals("John Walker", bookRepository.findById(book.getId()).get().getAuthor(), "Confirm that book author was updated in the DB");
//    }
//
//    @Test
//    public void givenBook_whenDeleteBook_thenDeleteBook() throws Exception {
//        var book = bookRepository.save(new Book("Game of Thrones", "John Wick", "1288-2828-2228", "2016"));
//
//        Assertions.assertEquals(1, bookRepository.count(), "Check if that book was saved to the Database");
//
//        mockMvc.perform(
//                delete("/api/books/{id}", book.getId())
//        ).andExpect(status().isOk());
//
//        Assertions.assertEquals(0, bookRepository.count(), "Check if book no longer exists in the Database");
//    }
//
//}