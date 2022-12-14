package com.example.lottoselfprac.service;


import com.example.lottoselfprac.domain.Lotto;
import com.example.lottoselfprac.domain.Round;
import com.example.lottoselfprac.domain.Store;
import com.example.lottoselfprac.reponse.LottoResponseDto;
import com.example.lottoselfprac.reponse.RankResponseDto;
import com.example.lottoselfprac.reponse.ResponseDto;
import com.example.lottoselfprac.repository.JdbcLottoCombinationRepository;
import com.example.lottoselfprac.repository.JdbcLottoTest;
import com.example.lottoselfprac.repository.LottoRepository;
import com.example.lottoselfprac.repository.roundRepository.RoundRepository;
import com.example.lottoselfprac.repository.storeRepository.StoreRepository;
import com.example.lottoselfprac.request.LottoDto;
import com.example.lottoselfprac.request.RoundDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@RequiredArgsConstructor
@Getter
@Service
public class LottoService {

    private final LottoRepository lottoRepository;
    private final StoreRepository storeRepository;
    private final RoundRepository roundRepository;
    private final HashMap<String, Integer> myMap;
    private final JdbcLottoCombinationRepository jdbcLottoCombinationRepository;
    private final JdbcLottoTest jdbcLottoTest;

    //로또 더미데이터 여러개 자동으로 생성
    @Transactional
    public ResponseDto<?> lottoCreates(Long nums) {

        // 전체 로또 판매점 가져오기
        //List<Store> stores =storeRepository.findAll();
        List<Store> stores = storeRepository.searchAll();

        // 로또 한 게임
        List<Integer> lotto;

        List<Lotto> batchInsertList = new ArrayList<>();

        // 여러 개의 로또를 모아놓은 리스트
        List<LottoResponseDto> allLottoList = new ArrayList<>();

        for(int i=0; i<nums; i++){

            //로또 6자리 생성
            lotto=new ArrayList<>();
            do{
                int num = (int)((Math.random() * 45) + 1);
                if(!lotto.contains(num)){
                    lotto.add(num);
                }
            }while(lotto.size()<6);

            //정렬
            Collections.sort(lotto);
//            double storeId =(Math.random()*6947)+1;
//
//            Store store = storeRepository.findById((long) storeId).orElseThrow();
//            System.out.println("lotto = " + Arrays.toString(lotto));

            Lotto game = Lotto.builder()
                    .firstNum((long) lotto.get(0))
                    .secondNum((long) lotto.get(1))
                    .thirdNum((long) lotto.get(2))
                    .fourthNum((long) lotto.get(3))
                    .fifthNum((long) lotto.get(4))
                    .sixthNum((long) lotto.get(5))
                    .uniqueCode(UUID.randomUUID().toString())
                    .store(stores.get((int) (Math.random()*stores.size())))
                    .build();

            //lottoRepository.save(game);
            batchInsertList.add(game);

            // 반환할 DTO 작성
            LottoResponseDto lottoResponseDto = LottoResponseDto.builder()
                    .firstNum(game.getFirstNum())
                    .secondNum(game.getSecondNum())
                    .thirdNum(game.getThirdNum())
                    .fourthNum(game.getFourthNum())
                    .fifthNum(game.getFifthNum())
                    .sixthNum(game.getSixthNum())
                    .build();

            allLottoList.add(lottoResponseDto);
        }

        jdbcLottoTest.batchInsertLottos(batchInsertList);

        // 여러 개의 로또를 모아놓은 allLottoList 반환
        //return ResponseDto.success(allLottoList); // 결과값이 보기 불편하게 나옴
        return ResponseDto.success("success");

    }


    //로또 한 개의 더미데이터 만들기
    @org.springframework.transaction.annotation.Transactional
    public ResponseDto<?> lottoCreate() {
        List<Store> stores = storeRepository.findAll();
        List<Integer> lotto = new ArrayList<>();
        //로또 6자리 생성
        do {
            int num = (int) ((Math.random() * 45) + 1);
            if (!lotto.contains(num)) {
                lotto.add(num);
            }
        }while(lotto.size() < 6);


        //정렬
        Collections.sort(lotto);

        Lotto game = Lotto.builder()
                .firstNum((long) lotto.get(0))
                .secondNum((long) lotto.get(1))
                .thirdNum((long) lotto.get(2))
                .fourthNum((long) lotto.get(3))
                .fifthNum((long) lotto.get(4))
                .sixthNum((long) lotto.get(5))
                .uniqueCode(UUID.randomUUID().toString())
                .store(stores.get((int) (Math.random()*stores.size())))
                .build();

        lottoRepository.save(game);

        // 반환할 DTO 작성
        LottoResponseDto lottoResponseDto = LottoResponseDto.builder()
                .firstNum(game.getFirstNum())
                .secondNum(game.getSecondNum())
                .thirdNum(game.getThirdNum())
                .fourthNum(game.getFourthNum())
                .fifthNum(game.getFifthNum())
                .sixthNum(game.getSixthNum())
                .build();

        return ResponseDto.success(lottoResponseDto);
    }

    // 수동으로 등록한 로또 번호 가져오기
    @org.springframework.transaction.annotation.Transactional
    public ResponseDto<?> lottoManual(LottoDto lottoDto) {
        List<Store> stores = storeRepository.findAll();

        // 중복처리, 에러 처리 여기서 하기 --------------------------------------------------------  --------------

        // 로또 번호를 오름차순으로 정렬
        List<Long> lottoNum = new ArrayList<Long>();
        lottoNum.add(lottoDto.getFirstNum());
        lottoNum.add(lottoDto.getSecondNum());
        lottoNum.add(lottoDto.getThirdNum());
        lottoNum.add(lottoDto.getFourthNum());
        lottoNum.add(lottoDto.getFifthNum());
        lottoNum.add(lottoDto.getSixthNum());

        Collections.sort(lottoNum);

        lottoRepository.save(Lotto.builder()
                .firstNum(lottoNum.get(0))
                .secondNum(lottoNum.get(1))
                .thirdNum(lottoNum.get(2))
                .fourthNum(lottoNum.get(3))
                .fifthNum(lottoNum.get(4))
                .sixthNum(lottoNum.get(5))
                .uniqueCode(UUID.randomUUID().toString())
                .store(stores.get((int) (Math.random()*stores.size())))
                .build());

        return ResponseDto.success(lottoDto);
        // 어떤 값이 입력 되었는지 response
    }

    //로또 당첨 확인
    @Transactional
    public ResponseDto<?> lottoWins(Long num) {
//        Round round = roundRepository.findById(num).orElseThrow();
        //Round round = roundRepository.findByRoundId(num).orElseThrow();
        RoundDto round = roundRepository.findByRoundId2(num);

        // 6개
        // 로또 번호
        //라운드 로또  추첨 번호
        List<Long> rounds = new ArrayList<>();
        rounds.add(round.getNum1());
        rounds.add(round.getNum2());
        rounds.add(round.getNum3());
        rounds.add(round.getNum4());
        rounds.add(round.getNum5());
        rounds.add(round.getNum6());
//        rounds.add(round.getBonus());

        System.out.println("rounds = " + Arrays.toString(rounds.toArray()));
        List<LottoDto> lottos = lottoRepository.search();
        List<List<Long>> lottoList = new ArrayList<>();

        for (LottoDto l:lottos) {
            List<Long> lottoNum= new ArrayList<>();
            lottoNum.add(l.getFirstNum());
            lottoNum.add(l.getSecondNum());
            lottoNum.add(l.getThirdNum());
            lottoNum.add(l.getFourthNum());
            lottoNum.add(l.getFifthNum());
            lottoNum.add(l.getSixthNum());
            lottoList.add(lottoNum);
        }


        //등수
        int firstRank = 0;
        int secondRank = 0;
        int thirdRank = 0;
        int fourthRank = 0;
        int fifthRank = 0;

        int lottoCnt=0;
        for (List<Long> l:lottoList) {

            HashMap<Long,Integer> map = new HashMap<>();
            for(int i= 0 ; i < rounds.size(); i++) {
                map.put(rounds.get(i), map.getOrDefault(rounds.get(i), 0) +1);
            }
            for(int i = 0; i<l.size(); i++){
                map.put(l.get(i), map.getOrDefault(l.get(i), 0) -1);
            }


            System.out.println(map);
            int cnt = 0;
            for(Long key : map.keySet()) {
                if(map.get(key) > 0) {
                    cnt++;
                }
            }
            System.out.println(cnt);
            if(cnt == 0) {
                firstRank++;
                System.out.println(lottoCnt);
//                myMap.put(lottos.get(lottoCnt).getStore().getStoreName(), myMap.getOrDefault(lottos.get(lottoCnt).getStore().getStoreName(), 0) + 1);
            }
            else if(cnt == 1 && l.contains(round.getBonus())) {
                secondRank++;
//                System.out.println(" 2등 l= " +l );
            }
            else if(cnt == 2){
                thirdRank++;
//                System.out.println(" 3등 l= " +l );
            }
            else if(cnt == 3){
                fourthRank++;
            }
            else if(cnt == 4){
                fifthRank++;
            }

            lottoCnt++;
        }

//        System.out.println("1등 = " + firstRank + " "
//                            +"2등" + secondRank + " "
//                            +"3등" + thirdRank + " "
//                            +"4등" + fourthRank+ " "
//                            +"5등" + fifthRank
//        );

        RankResponseDto rankResponseDto = RankResponseDto.builder()
                .firstRank(firstRank)
                .secondRank(secondRank)
                .thirdRank(thirdRank)
                .fourthRank(fourthRank)
                .fifthRank(fifthRank)
                .build();

        return ResponseDto.success(rankResponseDto);

    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public ResponseDto<?> winningNum(Pageable pageable) {
        Page<Round> winningNum = roundRepository.findAll(pageable);
        return ResponseDto.success(winningNum);
    }

}

// 10, 23, 29, 33 , 37, 40      16


