package unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

import com.containedcoder.springboot.maven.crud.springbootcrudfullstackwithmaven.article.Article;
import com.containedcoder.springboot.maven.crud.springbootcrudfullstackwithmaven.article.ArticleService;
import com.containedcoder.springboot.maven.crud.springbootcrudfullstackwithmaven.article.ArticleRepository;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ArticleServiceUnitTest {
	
	@Autowired
	private ArticleService articleService;
	
	@MockBean
	private RestTemplate template;
	
	@Test
	public void testGetArticlesByTitle() {
		String article_list = articleService.getArticles();
		//assertArrayEquals(test_array, article_list);
	}
}
