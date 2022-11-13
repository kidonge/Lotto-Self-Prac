package com.example.lottoselfprac.service;


import com.example.lottoselfprac.aop.ExecutionTime;
import com.example.lottoselfprac.domain.Lotto;
import com.example.lottoselfprac.domain.Round;
import com.example.lottoselfprac.domain.Store;
import com.example.lottoselfprac.reponse.ResponseDto;
import com.example.lottoselfprac.repository.LottoRepository;
import com.example.lottoselfprac.repository.RoundRepository;
import com.example.lottoselfprac.repository.StoreRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Getter
public class LottoService {

    private final LottoRepository lottoRepository;
    private final StoreRepository storeRepository;
    private final RoundRepository roundRepository;
    private final HashMap<String, Integer> myMap;

//    static HashMap <String, Integer> famousStore = new HashMap<String, Integer>();


    @Transactional
    @ExecutionTime
    public void createLotto(Long count) {

        ArrayList<Lotto> list1 = new ArrayList<>();

        // 모든 복권 판매점 데이터 가져옴.
        List<Store> allStore = storeRepository.findAll();
        for(int i = 0; i < count; i++){
            int min = 1;
            int max = 45;

            List<Integer> list = new ArrayList<>();

            int ranNum = 0;
            while(true){
                ranNum = (int) ((Math.random() * (max)) + min);
                if (list.size() == 6)
                    break;
                if(!list.contains(ranNum))
                    list.add(ranNum);
            }

            Collections.sort(list);

//            max = 6947;
//            int ranStoreNum = (int) ((Math.random() * (max)) + min);


//            Store store = storeRepository.findById((long) ranStoreNum).orElseThrow(
//                    () -> new IllegalArgumentException("이상해"));

            String id = UUID.randomUUID().toString();

            Lotto lotto = Lotto.builder()
                    .firstNum(Long.parseLong(Integer.toString(list.get(0))))
                    .secondNum(Long.parseLong(Integer.toString(list.get(1))))
                    .thirdNum(Long.parseLong(Integer.toString(list.get(2))))
                    .fourthNum(Long.parseLong(Integer.toString(list.get(3))))
                    .fifthNum(Long.parseLong(Integer.toString(list.get(4))))
                    .sixthNum(Long.parseLong(Integer.toString(list.get(5))))
                    .uniqueCode(id)
                    .store(allStore.get((int) (Math.random()*allStore.size())))
                    .build();

            //lottoRepository.save(lotto);
            list1.add(lotto);
        }
//        Collections.sort(list1);
        lottoRepository.saveAll(list1);

    }

    // n회차에 정보를 집어넣엇을 때 lotto 테이블에서 1등이 몇 명, 2등이 몇 명인지
    // 1등은 보너스 숫자 제외하고 전부 다 맞았을 때, 2등은 6개 중 5개를 맞추고 보너스 숫자를 맞췄을 때
    @Transactional
    public ResponseDto<?> getLottoRank(Long roundId) {



        // 1등과 2등 개수를 나타내는 변수 선언
        int firstRank = 0;
        int secondRank = 0;
        int thirdRank = 0;

        // 로또 1, 8, 10, 39, 42, 45      유니크 코드, 스토어
        // 당첨번호 10, 23, 29, 33, 37, 40      보너스: 16

        // roundId 회차에 당첨번호와 보너스 번호, 날짜 관련 내용이 있음
        Round round = roundRepository.findById(roundId).orElseThrow();

        List<Long> roundNumber = new ArrayList<>();
        roundNumber.add(round.getNum1());
        roundNumber.add(round.getNum2());
        roundNumber.add(round.getNum3());
        roundNumber.add(round.getNum4());
        roundNumber.add(round.getNum5());
        roundNumber.add(round.getNum6());

        List<Lotto> allLotto = lottoRepository.findAll();

        for(int i = 0 ; i < allLotto.size() ; i++) {

            // 회차에 당첨 숫자를 HashMap에 넣어줌(보너스 빼고)
            HashMap<Long, Integer> map = new HashMap<>();

            for(int j = 0; j < roundNumber.size(); j++) {
                map.put(roundNumber.get(j), map.getOrDefault(roundNumber.get(j), 0) + 1);
            }

            // 순수 로또 번호가 담겨있는 리스트   1 2 3 4 5 7
            List<Long> lottoNumber = new ArrayList<>();
            lottoNumber.add(allLotto.get(i).getFirstNum());
            lottoNumber.add(allLotto.get(i).getSecondNum());
            lottoNumber.add(allLotto.get(i).getThirdNum());
            lottoNumber.add(allLotto.get(i).getFourthNum());
            lottoNumber.add(allLotto.get(i).getFifthNum());
            lottoNumber.add(allLotto.get(i).getSixthNum());

            for(int j= 0 ; j < lottoNumber.size(); j++) {
                map.put(lottoNumber.get(j), map.getOrDefault(lottoNumber.get(j), 0) -1);
            }

            int cnt = 0;
            for(Long key : map.keySet()) {
                if(map.get(key) > 0) {
                    cnt++;
                }
            }

            if(cnt == 0) {
                firstRank++;
                //famousStore.put(allLotto.get(i).getStore().getStoreName(), famousStore.getOrDefault(allLotto.get(i).getStore().getStoreName(), 0) + 1);
                myMap.put(allLotto.get(i).getStore().getStoreName(), myMap.getOrDefault(allLotto.get(i).getStore().getStoreName(), 0) + 1);
                System.out.println(lottoNumber);
                System.out.println(i);
            }
            else if(cnt == 1 && lottoNumber.contains(round.getBonus())) {
                secondRank++;
                System.out.println("2등 : " + lottoNumber);
                System.out.println(i);
            }
            else if(cnt == 2){
                thirdRank++;
                System.out.println("3등 : " + lottoNumber);
                System.out.println(i);
            }



        }

        return ResponseDto.success("1등 : " + firstRank + " " + "2등 : " + secondRank + " 3등 : " + thirdRank + " " + myMap);
    }

    //" " + famousStore

}

// 10, 23, 29, 33 , 37, 40      16

