package com.example.bitoasistest.repo;

import com.example.bitoasistest.pojo.Ticker;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerRepository extends PagingAndSortingRepository<Ticker, Long> {

}
