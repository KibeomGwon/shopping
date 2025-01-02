package shop._8.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop._8.entity.QMember;

import static shop._8.entity.QMember.*;

@Repository
public class MemberQueryRepository {

    private final EntityManager em;
    private JPAQueryFactory factory;

    @Autowired
    public MemberQueryRepository(EntityManager em) {
        this.em = em;
        this.factory = new JPAQueryFactory(em);
    }
}
