package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.application.ReserveService;
import com.wanted.preonboarding.ticket.application.dto.ReserveRequest;
import com.wanted.preonboarding.ticket.application.dto.ReserveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;

    @PostMapping
    public ResponseEntity<ResponseHandler<ReserveResponse>> reserve(@RequestBody final ReserveRequest request) {
        final ReserveResponse response = reserveService.reserve(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ResponseHandler.<ReserveResponse>builder()
                                .message("공연 예약이 정상적으로 처리되었습니다.")
                                .statusCode(HttpStatus.OK)
                                .data(response)
                                .build()
                );
    }
}
