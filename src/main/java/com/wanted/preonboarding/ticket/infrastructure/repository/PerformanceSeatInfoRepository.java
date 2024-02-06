package com.wanted.preonboarding.ticket.infrastructure.repository;

import com.wanted.preonboarding.ticket.domain.Performance;
import com.wanted.preonboarding.ticket.domain.PerformanceSeatInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PerformanceSeatInfoRepository extends JpaRepository<PerformanceSeatInfo, Long> {

    @Query("SELECT ps FROM PerformanceSeatInfo ps "
            + "WHERE ps.performance = :performance AND ps.round = :round AND ps.line = :line AND ps.seat = :seat "
            + "AND ps.isReserve = 'DISABLE'")
    Optional<PerformanceSeatInfo> findCanReserveSeatByPerformanceAndSeatInfo(Performance performance, int round,
                                                                             String line, int seat);
}
