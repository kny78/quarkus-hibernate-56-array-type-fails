package im.kny;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.transaction.TransactionManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @Inject
    WithJsonDao withJsonDao;

    @Inject
    TransactionManager transactionManager;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        try {
            transactionManager.begin();
            JsonNode content = new ObjectMapper().readTree("{\"k2\": \"v2\"}");
            withJsonDao.persist(new WithJson(content,
                    new Part[]{Part.FORD}));
            transactionManager.commit();
            return "Hello RESTEasy";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}