package com.example.lottoselfprac.repository.storeRepository;

import com.example.lottoselfprac.domain.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.lottoselfprac.domain.QStore.store;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Store> searchAll(){
        return queryFactory
                .selectFrom(store)
                .fetch();
    }
}
