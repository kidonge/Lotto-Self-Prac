package com.example.lottoselfprac.repository.roundRepository;

import com.example.lottoselfprac.domain.QRound;
import com.example.lottoselfprac.domain.Round;
import com.example.lottoselfprac.request.QRoundDto;
import com.example.lottoselfprac.request.RoundDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.lottoselfprac.domain.QRound.*;

@Repository
@RequiredArgsConstructor
public class RoundRepositoryImpl implements RoundRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Round> findByRoundId(Long num){
        return Optional.ofNullable(queryFactory
                .selectFrom(round)
                .where(round.id.eq(num))
                .fetchOne());
    }

    @Override
    public RoundDto findByRoundId2(Long num){
        return queryFactory
                .select(new QRoundDto(round.num1,
                                round.num2,
                        round.num3,
                        round.num4,
                        round.num5,
                        round.num6,
                        round.bonus))
                .from(round)
                .where(round.id.eq(num)) //getId(num)
                .fetchOne(); //눌값 뜨면 넘어감
    }

//    private BooleanExpression getId(Long num) {
//        if (num == null) {
//            return null;
//        }
//        else if(num == 0){
//            return null;
//        }
//
//        return round.id.eq(num);
//    }


}
