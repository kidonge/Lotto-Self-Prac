package com.example.lottoselfprac.repository.roundRepository;


import com.example.lottoselfprac.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long>, RoundRepositoryCustom {
}
