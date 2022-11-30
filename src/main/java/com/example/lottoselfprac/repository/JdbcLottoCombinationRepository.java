package com.example.lottoselfprac.repository;

import com.example.lottoselfprac.domain.TestLotto;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcLottoCombinationRepository {
    private final JdbcTemplate jdbcTemplate;

        public void batchInsertLottos(List<TestLotto> lottos) {
            String sql = "INSERT INTO test_lotto " +
                    "(lotto_num, unique_code, store_id) " +
                    "VALUES (?, ?, ?)";
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                TestLotto lotto = lottos.get(i);
    //            ps.setLong(1, lotto.getLotto_id());
                ps.setString(1, lotto.getLottoNum());
                ps.setString(2,lotto.getUniqueCode());
                ps.setLong(3, lotto.getStore().getId());
            }

            @Override
            public int getBatchSize() {
                return lottos.size();
            }
        });
    }
}
// 10 23 29 33 37 40 16