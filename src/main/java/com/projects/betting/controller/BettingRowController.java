package com.projects.betting.controller;


import java.util.List;

import com.projects.betting.GameBaseDto;
import com.projects.betting.dto.BetPredictionRequest;
import com.projects.betting.dto.GeneratePredictionRequest;
import com.projects.betting.dto.MultipleBetResponse;
import com.projects.betting.dto.MultiplePixRequest;
import com.projects.betting.dto.PredictionResponse;
import com.projects.betting.request.BettingPixRequest;
import com.projects.betting.request.BettingRowRequest;
import com.projects.betting.response.BettingRowResponse;
import com.projects.betting.response.PixResponse;
import com.projects.betting.service.BettingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class BettingRowController implements BettingRowControllerApi {

    private final BettingService bettingService;

    @Override
    public BettingRowResponse createGameCoupon(BettingRowRequest request) {
        return bettingService.createBettingRow(request);
    }

    @Override
    public PixResponse createPixGameCoupon(BettingPixRequest request) {
        return bettingService.createBettingPixRow(request);
    }

    @Override
    public List<GameBaseDto> createModPixGameCoupon(BettingPixRequest request) {
        return bettingService.createModBettingPixRow(request);
    }

    @Override
    public PixResponse createPixGoalsGameCoupon() {
        return bettingService.createBettingPixGoals();
    }

    @Override
    public ResponseEntity<PredictionResponse> getGeneratedPixCoupon(MultiplePixRequest request) {
        return new ResponseEntity<>(new PredictionResponse(bettingService.generateMultipleCustomPixCoupons(request)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PredictionResponse> createBetCoupon(BetPredictionRequest request) {
        return new ResponseEntity<>(new PredictionResponse(bettingService.createBetCoupon(request)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MultipleBetResponse> getMultipleBetCoupons(GeneratePredictionRequest request) {
        return new ResponseEntity<>(new MultipleBetResponse(bettingService.generateMultipleBetCoupons(request)), HttpStatus.OK);
    }
}
