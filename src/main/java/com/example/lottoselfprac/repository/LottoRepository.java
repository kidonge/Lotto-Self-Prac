package com.example.lottoselfprac.repository;


import com.example.lottoselfprac.domain.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository extends JpaRepository<Lotto, Long> {
}
