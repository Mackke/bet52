package com.projects.betting.service;

import java.util.List;

import com.projects.betting.GameBaseDto;
import com.projects.betting.dto.BetPredictionRequest;
import com.projects.betting.dto.BettingDto;
import com.projects.betting.dto.GeneratePredictionRequest;
import com.projects.betting.dto.MultiplePixRequest;
import com.projects.betting.dto.PredictionDto;
import com.projects.betting.request.BettingPixRequest;
import com.projects.betting.request.BettingRowRequest;
import com.projects.betting.response.BettingRowResponse;
import com.projects.betting.response.PixResponse;


public interface BettingService {

    BettingRowResponse createBettingRow(BettingRowRequest request);

    PixResponse createBettingPixRow(BettingPixRequest request);

    List<GameBaseDto> createModBettingPixRow(BettingPixRequest request);

    PixResponse createBettingPixGoals();

    List<PredictionDto> generateMultipleCustomPixCoupons(MultiplePixRequest request);

    List<PredictionDto> createBetCoupon(BetPredictionRequest request);

    List<BettingDto> generateMultipleBetCoupons(GeneratePredictionRequest request);
}
