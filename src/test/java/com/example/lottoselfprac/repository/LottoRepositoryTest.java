package com.example.lottoselfprac.repository;

import com.example.lottoselfprac.config.TestConfig;
import com.example.lottoselfprac.domain.Lotto;
import com.example.lottoselfprac.domain.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩(컨트롤러와 서비스는 로딩되지 않음) 술러아스 테스트
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class) //
class LottoRepositoryTest {

    @Autowired
    private LottoRepository lottoRepository;

    // 1. 더미데이터 등록
    @Test
    @DisplayName("더미데이터 save() 테스트")
    public void saveTest(){
        //System.out.println("테스트가 잘 되나?");

        /**
         * given(데이터 준비)
         */
        String uniqueCode = "e0affffe-8938-4475-a5b5-ab7f966bf442";
        Long firstNum = 1L;
        Long secondNum = 3L;
        Long thirdNum = 15L;
        Long fourthNum = 23L;
        Long fifthNum = 35L;
        Long sixthNum = 42L;
        Store store = Store.builder()
                .storeName("테스트 스토어")
                .roadAddress("도로명")
                .lotAddress("주소")
                .build();

        // Repository 관련 테스트이니 레포지터리는 실제로 엔티티가 들어온다. 그러니 save 테스트를 할 엔티티 생성
        Lotto lotto = Lotto.builder()
                .uniqueCode(uniqueCode)
                .firstNum(firstNum)
                .secondNum(secondNum)
                .thirdNum(thirdNum)
                .fourthNum(fourthNum)
                .fifthNum(fifthNum)
                .sixthNum(sixthNum)
                .store(store)
                .build();


        /**
         * when(테스트 실행)
         */
        /*
         db에 저장 ->(primary키 생성 = id 생성 완료)
         -> save 메서드가 DB에 저장된 Book을 return(DB 데이터와 동기화)
         */
        Lotto lottoPS = lottoRepository.save(lotto); // PS는 persistence의미

        /**
         *  then(검증)
         */
        assertEquals(firstNum, lottoPS.getFirstNum());
        assertEquals(secondNum, lottoPS.getSecondNum());
        assertEquals(thirdNum, lottoPS.getThirdNum());
        assertEquals(fourthNum, lottoPS.getFourthNum());
        assertEquals(fifthNum, lottoPS.getFifthNum());
        assertEquals(sixthNum, lottoPS.getSixthNum());
        assertEquals(store, lottoPS.getStore());

    }


}

/*
컨트롤러, 서비스, 레포지터리를 전부 다 띄어서 테스트 하는건 통합 테스트
지금은 레포지토리만 테스트 -> 부분 테스트, 단위 테스트
 */
