package com.example.lottoselfprac.repository;


import com.example.lottoselfprac.domain.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LottoRepository extends JpaRepository<Lotto, Long> ,LottoRepositoryCustom{
    //List<Lotto> findAllByRound(Long round);
    //List<Lotto> findAllByRound(Long round);
}
