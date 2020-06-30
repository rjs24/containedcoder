package unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import com.containedcoder.springboot.maven.crud.springbootcrudfullstackwithmaven.article.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.containedcoder.springboot.maven.crud.springbootcrudfullstackwithmaven.article.Article;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerUnitTest {
	@MockBean
	private ArticleService articleService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		//setup fake data
		Date mockDate = new Date(69533484);
		ArrayList<String> mocklist = new ArrayList<String>();
		mocklist.add("django");
		mocklist.add("db protection");
		mocklist.add("regex validation");
		Article mockArticle = new Article(1, "Protecting your sql database", 
				"Protecting your db is critical to prevent sql injection and a massive data breach", "Bob", mockDate, mocklist);
		doReturn(Optional.of(mockArticle)).when(articleService).findById(0);
		Date mockDate2 = new Date(651551650);
		ArrayList<String> mocklist2 = new ArrayList<String>();
		mocklist2.add("springboot");
		mocklist2.add("java");
		mocklist2.add("netbeans");
		Article mockArticle2 = new Article(1, "Spring boot tutorial: How to get started and produce a working api", 
				"Spring boot fast tracks the initialisation stage of the Spring framework", "Bill", mockDate, mocklist);
		doReturn(Optional.of(mockArticle)).when(articleService).findById(1);
	}
	
	public static String ConvertToJsonString(final Object obj) {
		// method to convert objects to json string
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	@DisplayName("GET /api/article/1 - Found")
	public void testGetArticleById() throws Exception {
		//test if data retrievable through get request with pk
		mockMvc.perform(get("api/article/{id}", 1))
		.andExpect(status().isOk()) //checks response code
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)) // checks response type
		.andExpect(header().string(HttpHeaders.ETAG, "\"1\"")) // check header tag
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.title").value("Protecting your sql database"))
		.andExpect(jsonPath("$.text").value("Protecting your db is critical to prevent sql injection and a massive data breach"))
		.andExpect(jsonPath("$.author").value("Bob"));
	}
	
	@Test
	@DisplayName("GET /api/article - Success")
	public void testGetAllArticles() throws Exception {
		//test to see if all articles returned as json
		mockMvc.perform(get("/api/article/"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.article.length").value(2));
	}
	
	@Test
	@DisplayName("POST /api/article - Success")
	public void testCreateArticle() throws Exception {
		Date mockDate = new Date(64654531);
		ArrayList<String> mocklist3 = new ArrayList<String>();
		mocklist3.add("node");
		mocklist3.add("express");
		mocklist3.add("npm");
		Article postArticle = new Article(1, "Express Tutorial: Getting node up and running", 
				"So, are you ready to code your first microservice in node and express?", "Ben", mockDate, mocklist3);
		doReturn(Optional.of(postArticle)).when(articleService).save(any());
		
		mockMvc.perform(post("/api/article")
			.contentType(MediaType.APPLICATION_JSON)
			.content(ConvertToJsonString(postArticle)))
			
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(header().string(HttpHeaders.ETAG, "\"3\""))
		.andExpect(header().string(HttpHeaders.LOCATION,  "/api/article/3"))
		.andExpect(jsonPath("$.id").value(3))
		.andExpect(jsonPath("$.title").value("Express Tutorial: Getting node up and running"))
		.andExpect(jsonPath("$.date").value(mockDate))
		.andExpect(jsonPath("$.tags").value(mocklist3));
	}
	
	
	

	}
