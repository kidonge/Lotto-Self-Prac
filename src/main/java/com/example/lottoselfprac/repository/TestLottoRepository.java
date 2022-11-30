package com.example.lottoselfprac.repository;

import com.example.lottoselfprac.domain.TestLotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestLottoRepository extends JpaRepository<TestLotto, Long>, TestLottoRepositoryCustom {
}
