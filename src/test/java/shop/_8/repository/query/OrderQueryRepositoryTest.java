package shop._8.repository.query;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderQueryRepositoryTest {

    @Autowired
    OrderQueryRepository orderQueryRepository;

    @Test
    public void fetchTest() throws Exception {
        // given

        // when

        // then
    }
}