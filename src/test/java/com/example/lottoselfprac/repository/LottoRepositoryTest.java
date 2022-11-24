package com.example.lottoselfprac.repository;

import com.example.lottoselfprac.config.TestConfig;
import com.example.lottoselfprac.domain.Lotto;
import com.example.lottoselfprac.domain.Store;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// [데이터 준비 + save테스트] (트랜잰션) , [데이터 준비 + findAll()] 트랜잭션 -> 즉 각 테스트 하나하나가 트랜잭션이다.

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩(컨트롤러와 서비스는 로딩되지 않음) 술러아스 테스트
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class) //
class LottoRepositoryTest {

    @Autowired
    private LottoRepository lottoRepository;

    //@BeforeAll -> 테스트 시작전에 한번 만 실행
    @BeforeEach // 각 테스트 시작 전에 한번 씩 실행
    public void saveDummyData(){
        String uniqueCode = "e0affffe-8938-4475-a5b5-ab7f966bf442tt";
        Long firstNum = 2L;
        Long secondNum = 5L;
        Long thirdNum = 10L;
        Long fourthNum = 33L;
        Long fifthNum = 35L;
        Long sixthNum = 44L;
        Store store = Store.builder()
                .storeName("공용 더미")
                .roadAddress("공용 도로명")
                .lotAddress("공용 주소")
                .build();

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

        Lotto lottoPS = lottoRepository.save(lotto);
        System.out.println("================================================================");
    }

    // 1. 로또 더미데이터 등록
    @Test
    @DisplayName("더미데이터 save() 테스트")
    public void saveTest(){
        //System.out.println("테스트가 잘 되나?");

        /**
         * given(데이터 준비)
         */
        String uniqueCode = "e0affffe-8938-4475-a5b5-ab7f966bf4423";
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

    } // 트랜잭션 종료하면서 저장된 데이터를 초기화하니 밑에 2번 테스트에서 size를 확인해보면 늘어나지 않은 것을 확인해봀 수 있다.
    // 트랜잭션을 종료하지 않고 밑에 2번 테스트로 간다면 데이터가 한 개 들어가겠지만 테스트마다 분리시켜주는 것이 좋기 때문에 2번 테스트에서 데이터를 넣어주는 작업 필요

    // 2. 로또 더미데이터 목록보기
    @Test
    @DisplayName("더미데이터 findAll() 테스트")
    public void findAllTest(){

        /**
         * given(데이터 준비)
         */

        // 지금 DB에 데이터가 하나도 없는 상태이니 데이터 추가
        String uniqueCode = "e0affffe-8938-4475-a5b5-ab7f966bf442tt";
        Long firstNum = 2L;
        Long secondNum = 5L;
        Long thirdNum = 10L;
        Long fourthNum = 33L;
        Long fifthNum = 35L;
        Long sixthNum = 44L;
        Store store = Store.builder()
                .storeName("공용 더미")
                .roadAddress("공용 도로명")
                .lotAddress("공용 주소")
                .build();





        /**
         * when(테스트 실행)
         */
        List<Lotto> lottos = lottoRepository.findAll();
        System.out.println(lottos.get(0).getFirstNum());
        System.out.println(lottos.get(0).getSecondNum());
        System.out.println(lottos.get(0).getThirdNum());
        System.out.println(lottos.get(0).getFourthNum());
        System.out.println(lottos.get(0).getFifthNum());
        System.out.println(lottos.get(0).getSixthNum());
        System.out.println(lottos.get(0).getUniqueCode());
        System.out.println(lottos.get(0).getStore());
        System.out.println("=================================== lottoRepository.findAll()" + lottos.size());


        /**
         *  then(검증)
         */

        assertEquals(firstNum, lottos.get(0).getFirstNum());
        assertEquals(secondNum, lottos.get(0).getSecondNum());
        assertEquals(thirdNum, lottos.get(0).getThirdNum());
        assertEquals(fourthNum, lottos.get(0).getFourthNum());
        assertEquals(fifthNum, lottos.get(0).getFifthNum());
        assertEquals(sixthNum, lottos.get(0).getSixthNum());
//        assertEquals(store, lottos.get(0).getStore());
    }

    // 3. 로또 더미데이터 한건보기
    @Test
    @Sql("classpath:db/tableInit.sql") // id를 찾는 모든 테스트 앞에 붙여주는게 좋음 -> 실제 서버로 테스트 할 때는 테이블이 날라가니깐 id 검증 하지말고 다른 테스트 방법 생각해보기
    @DisplayName("더미데이터 findById() 테스트")
    public void findByIdTest(){

        /**
         * given(데이터 준비)
         */

        // 지금 DB에 데이터가 하나도 없는 상태이니 데이터 추가
        String uniqueCode = "e0affffe-8938-4475-a5b5-ab7f966bf442tt";
        Long firstNum = 2L;
        Long secondNum = 5L;
        Long thirdNum = 10L;
        Long fourthNum = 33L;
        Long fifthNum = 35L;
        Long sixthNum = 44L;
        Store store = Store.builder()
                .storeName("공용 더미")
                .roadAddress("공용 도로명")
                .lotAddress("공용 주소")
                .build();


        /**
         * when(테스트 실행)
         */

        List<Lotto> lottos2 = lottoRepository.findAll();
        System.out.println("===================================" + lottos2.size());
        System.out.println(lottos2.get(0).getLotto_id());
        Lotto lottos = lottoRepository.findById(1L).get();
        System.out.println(lottos.getLotto_id());

        /**
         *  then(검증)
         */

        assertEquals(firstNum, lottos.getFirstNum());
        assertEquals(secondNum, lottos.getSecondNum());
        assertEquals(thirdNum, lottos.getThirdNum());
        assertEquals(fourthNum, lottos.getFourthNum());
        assertEquals(fifthNum, lottos.getFifthNum());
        assertEquals(sixthNum, lottos.getSixthNum());
//        assertEquals(store, lottos.getStore());
    }

    // 로또 삭제
    @Test
    @Sql("classpath:db/tableInit.sql")
    @DisplayName("삭제 테스트")
    public void deleteTest(){
        /**
         * given(데이터 준비)
         */

        Long id = 1L;



        /**
         * when(테스트 실행)
         */

        lottoRepository.deleteById(id);

        /**
         *  then(검증)
         */

       //assertFalse는 안에 값이 false면 테스트 통과
        assertFalse(lottoRepository.findById(id).isPresent());

    }

    // 5. 수정 -> 더티체킹을  할 수 없다. -> 이미 DB에 저장되어 있는 id에 save를 하면 update와 같은 역할을 하게된다. 11강
    @Test
    @Sql("classpath:db/tableInit.sql")
    @DisplayName("로또 수정하는 테스트")
    public void updateTest(){
        /**
         * given(데이터 준비)
         */

        String uniqueCode = "e0affffe-8938-4475-a5b5-ab7f966bf4423";
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

        Lotto lottoPS = lottoRepository.save(lotto);

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

/*
일반적으로 테스트는 순서가 보장되지 않는다. -> @Order를 붙여야지 순서 보장된다.

테스트 메서드가 하나 실행 후 종료되면  데이터가 초기화된다.
데이터는 초기화 되지만 primary key가 auto increment가 되기때문에 id값은 초기화 되지 않는다.

 */