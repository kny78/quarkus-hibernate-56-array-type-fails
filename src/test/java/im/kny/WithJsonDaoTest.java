package im.kny;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class WithJsonDaoTest {
    @Inject
    WithJsonDao withJsonDao;

    @Inject
    TransactionManager transactionManager;

    @Test
    void testPersist() throws JsonProcessingException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        transactionManager.begin();
        try {
            ObjectMapper om = new ObjectMapper();
            JsonNode content = om.readTree("{\"k1\": \"v1\"}");
            withJsonDao.persist(new WithJson(content, new Part[]{Part.FORD, Part.VW}));
            transactionManager.commit();

            transactionManager.begin();
            List<WithJson> list = withJsonDao.list();
            assertTrue(!list.isEmpty(), "one element");
        } finally {
            transactionManager.commit();
        }
    }
}