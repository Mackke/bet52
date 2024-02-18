package com.projects.betting.controller;

import java.util.List;

import com.projects.betting.GameBaseDto;
import com.projects.betting.dto.BetPredictionRequest;
import com.projects.betting.dto.GeneratePredictionRequest;
import com.projects.betting.dto.MultipleBetResponse;
import com.projects.betting.dto.PredictionResponse;
import com.projects.betting.dto.MultiplePixRequest;
import com.projects.betting.request.BettingPixRequest;
import com.projects.betting.request.BettingRowRequest;
import com.projects.betting.response.BettingRowResponse;
import com.projects.betting.response.PixResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface BettingRowControllerApi {

    @PostMapping("/coupons")
    BettingRowResponse createGameCoupon(@RequestBody BettingRowRequest request);

    @PostMapping("/coupons/pix")
    PixResponse createPixGameCoupon(@RequestBody BettingPixRequest request);

    @PostMapping("/coupons/modpix")
    List<GameBaseDto> createModPixGameCoupon(@RequestBody BettingPixRequest request);

    @PostMapping("/coupons/pix-goals")
    PixResponse createPixGoalsGameCoupon();

    @GetMapping("/coupons/generate")
    ResponseEntity<PredictionResponse> getGeneratedPixCoupon(@RequestBody MultiplePixRequest request);

    @PostMapping("/coupons/create")
    ResponseEntity<PredictionResponse> createBetCoupon(@RequestBody BetPredictionRequest request);

    @GetMapping("/coupons/generate/multiple")
    ResponseEntity<MultipleBetResponse> getMultipleBetCoupons(@RequestBody GeneratePredictionRequest request);
}
