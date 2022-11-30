package com.example.lottoselfprac.repository;

import com.example.lottoselfprac.domain.QTestLotto;
import com.example.lottoselfprac.request.QTestLottoDto;
import com.example.lottoselfprac.request.TestLottoDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.lottoselfprac.domain.QTestLotto.testLotto;

@Repository
@RequiredArgsConstructor
public class TestLottoRepositoryImpl implements TestLottoRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TestLottoDto> searchAll(){
        return queryFactory
                .select(new QTestLottoDto(testLotto.lottoNum))
                .from(testLotto)
                .fetch();
    }

}
