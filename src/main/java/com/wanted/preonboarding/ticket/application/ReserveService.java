package com.wanted.preonboarding.ticket.application;

import com.wanted.preonboarding.core.exception.PreonboardingException;
import com.wanted.preonboarding.ticket.application.dto.ReserveRequest;
import com.wanted.preonboarding.ticket.application.dto.ReserveResponse;
import com.wanted.preonboarding.ticket.domain.Performance;
import com.wanted.preonboarding.ticket.domain.PerformanceSeatInfo;
import com.wanted.preonboarding.ticket.domain.Reservation;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.PerformanceSeatInfoRepository;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReserveService {

    private final PerformanceRepository performanceRepository;
    private final PerformanceSeatInfoRepository performanceSeatInfoRepository;
    private final ReservationRepository reservationRepository;

    public ReserveResponse reserve(final ReserveRequest request) {
        final Performance performance = findPerformanceById(request.performanceId());
        final PerformanceSeatInfo performanceSeatInfo = findCanReserveSeatByPerformanceAndSeatInfo(performance,
                request.round(), request.line(), request.seat());

        // todo: 할인 정책 적용

        if (!performance.canPay(request.balance())) {
            throw new PreonboardingException("금액이 부족합니다.");
        }

        // todo: 결제

        performanceSeatInfo.reserve();
        final Reservation reservation = makeReservation(
                request.name(),
                request.phoneNumber(),
                performance,
                performanceSeatInfo
        );
        final Reservation savedReservation = reservationRepository.save(reservation);

        return new ReserveResponse(
                performance.getId(),
                performance.getName(),
                savedReservation.getRound(),
                savedReservation.getLine(),
                savedReservation.getSeat(),
                savedReservation.getName(),
                savedReservation.getPhoneNumber()
        );
    }

    private Performance findPerformanceById(final UUID performanceId) {
        return performanceRepository.findById(performanceId)
                .orElseThrow(() -> new PreonboardingException("존재하지 않는 공연 ID 입니다."));
    }

    private PerformanceSeatInfo findCanReserveSeatByPerformanceAndSeatInfo(
            final Performance performance,
            final int round,
            final String line,
            final int seat
    ) {
        return performanceSeatInfoRepository.findCanReserveSeatByPerformanceAndSeatInfo(performance, round, line, seat)
                .orElseThrow(() -> new PreonboardingException("이미 예약된 좌석입니다."));
    }

    private Reservation makeReservation(
            final String reservationPersonName,
            final String phoneNumber,
            final Performance performance,
            final PerformanceSeatInfo performanceSeatInfo
    ) {
        return new Reservation(
                performance,
                reservationPersonName,
                phoneNumber,
                performanceSeatInfo.getRound(),
                performanceSeatInfo.getGate(),
                performanceSeatInfo.getLine(),
                performanceSeatInfo.getSeat()
        );
    }
}
