package com.example.lottoselfprac.repository;

import com.example.lottoselfprac.request.TestLottoDto;

import java.util.List;

public interface TestLottoRepositoryCustom {
    List<TestLottoDto> searchAll();
}
