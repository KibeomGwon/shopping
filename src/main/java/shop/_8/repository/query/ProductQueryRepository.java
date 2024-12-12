package shop._8.repository.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import shop._8.entity.*;

import java.util.List;

import static shop._8.entity.QCategory.*;
import static shop._8.entity.QCategoryProduct.*;
import static shop._8.entity.QProduct.*;

@Repository
public class ProductQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory factory;

    @Autowired
    public ProductQueryRepository(EntityManager em) {
        this.em = em;
        this.factory = new JPAQueryFactory(em);
    }

    public Page<Product> findPageByMemberAndCategory(List<Category> categories, Pageable pageable) {
        List<Product> content = factory
                .select(product)
                .from(product)
                .join(product.cpList, categoryProduct).join(categoryProduct.category, category1)
                .where(categoryIn(categories))
                .distinct()
                .orderBy(product.makingDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = factory
                .select(product.count())
                .from(product)
                .join(product.cpList, categoryProduct).join(categoryProduct.category, category1)
                .where(categoryIn(categories))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    private BooleanExpression categoryIn(List<Category> categories) {
        return categories != null ? category1.in(categories) : null;
    }

}
