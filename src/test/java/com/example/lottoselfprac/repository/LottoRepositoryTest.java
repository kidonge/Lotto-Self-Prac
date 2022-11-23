package com.example.lottoselfprac.repository;

import com.example.lottoselfprac.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩(컨트롤러와 서비스는 로딩되지 않음)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class) //
class LottoRepositoryTest {

    @Autowired
    private LottoRepository lottoRepository;

    // 1. 더미데이터 등록
    @Test
    @DisplayName("더미데이터 등록 테스트")
    public void saveTest(){
        System.out.println("테스트가 잘 되나?");

    }


}

/*
컨트롤러, 서비스, 레포지터리를 전부 다 띄어서 테스트 하는건 통합 테스트
지금은 레포지토리만 테스트 -> 부분 테스트, 단위 테스트
 */
