package com.example.lottoselfprac.repository;


import com.example.lottoselfprac.request.LottoDto;
import com.example.lottoselfprac.request.QLottoDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.lottoselfprac.domain.QLotto.lotto;



//queryDsl 쿼리문 짜는 곳
@Repository
@RequiredArgsConstructor
public class LottoRepositoryImpl implements com.example.lottoselfprac.repository.LottoRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<LottoDto> search() {
        return  queryFactory
                .select(new QLottoDto(lotto.firstNum,
                        lotto.secondNum,
                        lotto.thirdNum,
                        lotto.fourthNum,
                        lotto.fifthNum,
                        lotto.sixthNum))
                .from(lotto)
                .fetch();
    }
}
