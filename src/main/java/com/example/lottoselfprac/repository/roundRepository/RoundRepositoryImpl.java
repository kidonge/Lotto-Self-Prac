package com.example.lottoselfprac.repository.roundRepository;

import com.example.lottoselfprac.domain.QRound;
import com.example.lottoselfprac.domain.Round;
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


}
