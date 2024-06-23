package com.project.memor2.domain.post.domain.repository.query;

import com.project.memor2.domain.post.client.dto.Post;
import com.project.memor2.global.common.dto.request.PageRequest;
import com.project.memor2.global.exception.BadRequestException;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.memor2.domain.post.domain.QPostEntity.postEntity;

@Repository
@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> postList(PageRequest request){
        return jpaQueryFactory
                .select(postListConstructorExpression())
                .from(postEntity)
                .offset((request.getPage() - 1) * request.getSize())
                .limit(request.getSize())
                .orderBy(postEntity.idx.asc())
                .fetch();
    }

    @Override
    public List<Post> myPostList(PageRequest request,String email){
        return jpaQueryFactory
                .select(postListConstructorExpression())
                .from(postEntity)
                .where(eqEmail(email))
                .offset((request.getPage() - 1) * request.getSize())
                .limit(request.getSize())
                .orderBy(postEntity.idx.asc())
                .fetch();
    }

    private ConstructorExpression<Post> postListConstructorExpression(){
        return Projections.constructor(Post.class,
                postEntity.idx,
                postEntity.title,
                postEntity.content,
                postEntity.author,
                postEntity.createdDateTime,
                postEntity.modifiedDateTime
        );
    }

    private BooleanExpression eqEmail(String email) {
        if(email.isEmpty()){
            throw BadRequestException.EXCEPTION;
        }
        return postEntity.author.eq(email);
    }

}