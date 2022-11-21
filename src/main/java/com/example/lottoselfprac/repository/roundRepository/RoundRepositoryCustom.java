package com.example.lottoselfprac.repository.roundRepository;

import com.example.lottoselfprac.domain.Round;

import java.util.Optional;

public interface RoundRepositoryCustom {
    Optional<Round> findByRoundId(Long num);
}
