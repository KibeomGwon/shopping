package shop._8.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import shop._8.dto.condition.OrderSearchCondition;
import shop._8.entity.Order;
import shop._8.entity.QMember;
import shop._8.entity.QOrder;

import java.util.List;

import static shop._8.entity.QMember.*;
import static shop._8.entity.QOrder.*;

@Repository
public class OrderQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory factory;

    @Autowired
    public OrderQueryRepository(EntityManager em) {
        this.em = em;
        this.factory = new JPAQueryFactory(em);
    }

    public Page<Order> findOrderByCondition(OrderSearchCondition condition, Pageable pageable) {
        List<Order> content = factory
                .select(order)
                .from(order)
                .join(order.member, member)
                .fetchJoin()
                .where(
                        member.memberId.eq(condition.getMemberId()),
                        order.status.eq(condition.getOrderStatus()),
                        member.grade.eq(condition.getGrade())
                )
                .orderBy(order.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = factory
                .select(order.count())
                .from(order)
                .join(order.member, member)
                .fetchJoin()
                .where(
                        member.memberId.eq(condition.getMemberId()),
                        order.status.eq(condition.getOrderStatus()),
                        member.grade.eq(condition.getGrade())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

}
