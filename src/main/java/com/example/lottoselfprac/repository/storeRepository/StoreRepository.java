package com.example.lottoselfprac.repository.storeRepository;


import com.example.lottoselfprac.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom{
    Optional<Store> findById(Long id);
}
