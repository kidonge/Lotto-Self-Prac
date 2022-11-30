package com.example.lottoselfprac.service;

import com.example.lottoselfprac.domain.Round;
import com.example.lottoselfprac.domain.Store;
import com.example.lottoselfprac.domain.TestLotto;
import com.example.lottoselfprac.reponse.ResponseDto;
import com.example.lottoselfprac.repository.JdbcLottoCombinationRepository;
import com.example.lottoselfprac.repository.TestLottoRepository;
import com.example.lottoselfprac.repository.roundRepository.RoundRepository;
import com.example.lottoselfprac.repository.storeRepository.StoreRepository;
import com.example.lottoselfprac.request.TestLottoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Service
public class TestLottoService {

    private final StoreRepository storeRepository;
    private final TestLottoRepository testLottoRepository;
    private final RoundRepository roundRepository;
    private final JdbcLottoCombinationRepository jdbcLottoCombinationRepository;

    @Transactional
    public ResponseDto<?> test(Long num) {
        String uniqueCode = UUID.randomUUID().toString();

        List<Store> stores = storeRepository.searchAll();

        String[] lottoArr = new String[46];

        List<Integer> ranNumList;

        List<TestLotto> batchInsertList = new ArrayList<>();

        for(int i = 0 ; i < num; i++){
            Arrays.fill(lottoArr, "0");
            ranNumList = new ArrayList<Integer>();



            while(ranNumList.size() < 6){
                int n = (int) ((Math.random() * 45) + 1);
                if(ranNumList.contains(n))
                    continue;
                else
                    ranNumList.add(n);
            }

            for(int j = 0; j <ranNumList.size(); j++){
                lottoArr[ranNumList.get(j)] = "1";
            }

            String test = Arrays.toString(lottoArr);
            test = test.substring(1,test.length() - 1);


            TestLotto testLotto = TestLotto.builder()
                    .lottoNum(test)
                    .uniqueCode(uniqueCode)
                    .store(stores.get((int) (Math.random() * stores.size())))
                    .build();

            //testLottoRepository.save(testLotto);
            batchInsertList.add(testLotto);
        }

        jdbcLottoCombinationRepository.batchInsertLottos(batchInsertList);


        return ResponseDto.success("success");

    }
    //괄호 찍아보ㅓ기
 //Integer 배열로 바꿔서 해보자
    @Transactional
    public ResponseDto<?> rankCount(Long num) {
        Round round = roundRepository.findByRoundId(num).orElseThrow();

        List<Long> rounds = new ArrayList<>();
        rounds.add(round.getNum1());
        rounds.add(round.getNum2());
        rounds.add(round.getNum3());
        rounds.add(round.getNum4());
        rounds.add(round.getNum5());
        rounds.add(round.getNum6());

        int firstRank = 0;
        int secondRank = 0;
        int thirdRank = 0;
        int fourthRank = 0;
        int fifthRank = 0;

        List<TestLottoDto> testLottoDtoList = testLottoRepository.searchAll();
        //System.out.println(testLottoDtoList);
        List<String[]> testLottoDtoListAll = new ArrayList<>();

        //System.out.println(testLottoDtoList);

        for(int i = 0 ; i < testLottoDtoList.size() ; i++){
            String[] testLottoArr = testLottoDtoList.get(i).getLottoNum().split(", ");
            testLottoDtoListAll.add(testLottoArr);
        }

        int cnt;
        // 10 23 29 33 37 40        16
        for(int i = 0; i < testLottoDtoListAll.size(); i++){
            //System.out.println(testLottoDtoListAll.size() + "   =================================== 사이즈");
            cnt = 0;
            for(int j = 0; j < rounds.size(); j++){
                //System.out.println(rounds.get(j) + " -------------------------------------------------------- 라운드 당첨 번호" + j);
                //System.out.println(testLottoDtoListAll.get(i)[(rounds.get(j)).intValue()] + "------------------------------------------------- 더미데이터의 값");
                if(testLottoDtoListAll.get(i)[(rounds.get(j)).intValue()].equals("1")) {
                    //System.out.println(testLottoDtoListAll.get(i)[(rounds.get(j)).intValue()] + " -------------------------------- 더미데이터");
                    cnt++;
                }
            }
            //System.out.println(cnt + " =================================  cnt");
            if(cnt == 6) {
                firstRank++;
                //System.out.println(Arrays.toString(testLottoDtoListAll.get(i)) + "111111111111111111111111111111111111111111");
            }
            else if(cnt == 5 && testLottoDtoListAll.get(i)[round.getBonus().intValue()].equals("1")) {
                secondRank++;
                //System.out.println(Arrays.toString(testLottoDtoListAll.get(i)) + "2222222222222222222222222222222222222222222");
            }
            else if(cnt == 5) {
                thirdRank++;
                //System.out.println(Arrays.toString(testLottoDtoListAll.get(i)) + "3333333333333333333333333333333333333333");
            }
            else if(cnt == 4) {
                fourthRank++;
                //System.out.println(Arrays.toString(testLottoDtoListAll.get(i)) + "444444444444444444444444444444444444444");
            }
            else if(cnt == 3) {
                fifthRank++;
                //System.out.println(Arrays.toString(testLottoDtoListAll.get(i)) + "555555555555555555555555555555555555");
            }
        }

//        System.out.println("1등 = " + firstRank + "================================================================");
//        System.out.println("2등 = " + secondRank + "================================================================");
//        System.out.println("3등 = " + thirdRank + "================================================================");
//        System.out.println("4등 = " + fourthRank + "================================================================");
//        System.out.println("5등 = " + fifthRank + "================================================================");
//

    // 내가 넣은 로또 번호 1 2 3 4 5 7
        // 당첨번호  1 2 3 4 5 6

        /*
              1 2 3 4 5 6 7
              0 0 0 0 0 1 -1
         */



        return ResponseDto.success("success");

    }

//    @Transactional
//    public ResponseDto<?> test2(Long num) {
//        String uniqueCode = UUID.randomUUID().toString();
//
//        List<Store> stores = storeRepository.searchAll();
//
//
//    }
}
