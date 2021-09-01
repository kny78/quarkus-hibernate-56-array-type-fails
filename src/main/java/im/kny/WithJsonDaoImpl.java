package im.kny;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class WithJsonDaoImpl implements WithJsonDao {

    private final EntityManager em;

    @Inject
    public WithJsonDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<WithJson> list() {
        return em
                .createQuery("SELECT j from WithJson j", WithJson.class)
                .getResultList();
    }

    @Override
    public void persist(WithJson obj) {
        em.persist(obj);
    }
}
