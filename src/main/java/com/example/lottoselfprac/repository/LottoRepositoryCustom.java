package com.example.lottoselfprac.repository;




import com.example.lottoselfprac.request.LottoDto;

import java.util.List;

//쿼리디에스엘 정의 하는 곳
public interface LottoRepositoryCustom {
    List<LottoDto> search();
}
