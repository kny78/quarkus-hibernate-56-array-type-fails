package im.kny;

import java.util.List;

public interface WithJsonDao {

    List<WithJson> list();

    void persist(WithJson obj);
}
