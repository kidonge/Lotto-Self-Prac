package com.example.lottoselfprac.repository.roundRepository;

import com.example.lottoselfprac.domain.Round;
import com.example.lottoselfprac.request.RoundDto;

import java.util.Optional;

public interface RoundRepositoryCustom {
    Optional<Round> findByRoundId(Long num);

    RoundDto findByRoundId2(Long num);
}
