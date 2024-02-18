package com.projects.betting.service;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.projects.betting.GameBaseDto;
import com.projects.betting.dto.BettingDto;
import com.projects.betting.dto.GameCouponDto;
import com.projects.betting.dto.GameDto;
import com.projects.betting.dto.GeneratePredictionRequest;
import com.projects.betting.dto.Prediction;
import com.projects.betting.dto.PredictionResponse;
import com.projects.betting.mapper.GameCouponMapper;
import com.projects.betting.mapper.GameTranslator;
import com.projects.betting.dto.BetDto;
import com.projects.betting.dto.BetPredictionRequest;
import com.projects.betting.dto.GamePredictionDto;
import com.projects.betting.dto.MultiplePixRequest;
import com.projects.betting.dto.PixCouponDto;
import com.projects.betting.dto.PredictionDto;
import com.projects.betting.entity.Game;
import com.projects.betting.entity.GameCoupon;
import com.projects.betting.repository.BettingRepository;
import com.projects.betting.repository.GameCouponRepository;
import com.projects.betting.request.BettingPixRequest;
import com.projects.betting.request.BettingRowRequest;
import com.projects.betting.response.BettingRowResponse;
import com.projects.betting.response.PixResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BettingServiceImpl implements BettingService {

    private BettingRepository bettingRepository;
    private GameCouponRepository gameCouponRepository;

    @Override
    public BettingRowResponse createBettingRow(BettingRowRequest request) {
        //TODO: create a coupon
        GameCoupon gameCoupon = GameCoupon.builder()
                .couponName(request.getName())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        GameCoupon persistedGameCoupon = gameCouponRepository.save(gameCoupon);

        //TODO: save the games

        List<Game> games = request.getRow().entrySet().stream().map(it -> new Game(it.getKey(), 1, it.getValue(), persistedGameCoupon)).toList();

        List<Game> persistedGames = bettingRepository.saveAll(games);

        return BettingRowResponse.builder()
                .id(persistedGameCoupon.getId())
                .games(persistedGames.stream().map(GameTranslator::gameToGameDto).toList())
                .createdAt(Instant.now())
                .build();
    }

    @Override
    public PixResponse createBettingPixRow(BettingPixRequest request) {
        PixCouponDto dto = generatePixCoupon(request);

        return new PixResponse("Pix 1", dto.getCoupon());
    }

    @Override
    public List<GameBaseDto> createModBettingPixRow(BettingPixRequest request) {
        List<GameBaseDto> generateMatches = generateAllMatches(request.getTotalGames());
        List<GameBaseDto> predictedMatches = predictMatches(generateMatches, request.getHomePrediction(), request.getDrawPrediction(), request.getAwayPrediction());

        predictedMatches.sort(Comparator.comparing(GameBaseDto::getMatchName));

        return predictedMatches;
    }

    @Override
    public PixResponse createBettingPixGoals() {
        Map<String, String> pixGoals = generatePixGoals();

        return new PixResponse("Pix 1", pixGoals);
    }

    @Override
    public List<PredictionDto> generateMultipleCustomPixCoupons(MultiplePixRequest request) {
        List<Integer> hedgedGames = gamesToBeHedged(request);

        return Collections.emptyList();/*request.getGamePredictions().stream()
                .map(game -> new PredictionDto(game.getGameIndex(), generateHedging(hedgedGames, game)))
                .collect(Collectors.toList());*/
    }

    @Override
    public List<PredictionDto> createBetCoupon(BetPredictionRequest request) {
        //TODO validate the input
        validateBetCoupon(request);

        //TODO create entity
        GameCoupon gameCoupon = new GameCoupon(null, request.getCoupon(), Instant.now(), Instant.now());
        GameCoupon persistedCoupon = gameCouponRepository.save(gameCoupon);

        List<Game> games = request.getBetDto().stream()
                .map(game -> new Game(game.getGameName(), game.getGameIndex(), String.join("", game.getPredictions()), persistedCoupon))
                .toList();

        List<Game> persistedGames = bettingRepository.saveAll(games);

        List<GameDto> gameDtos = GameTranslator.gamesToGameDtos(persistedGames);
        //TODO map from entity to dto
        GameCouponDto gameCouponDto = GameCouponMapper.gameToGameCouponDto(persistedCoupon);

        return List.of(new PredictionDto(gameCouponDto, gameDtos));
    }

    @Override
    public List<BettingDto> generateMultipleBetCoupons(GeneratePredictionRequest request) {
        List<BettingDto> gameCoupons = new ArrayList<>();

        for (int i = 0; i < request.getCoupons(); i++) {
            BettingDto bettingDto = generateBets(request.getGames(), request.getHalfHedges());
            gameCoupons.add(bettingDto);

        }

        return gameCoupons;
    }

    private BettingDto generateBets(int games, int halfHedges) {
        final List<String> results = List.of("1", "X", "2");
        List<Prediction> bets = new ArrayList<>();

        for (int i = 0; i < games; i++) {
            int result = new Random().nextInt(3);

            bets.add(new Prediction(i, results.get(result), null));
        }

        addHedging(bets, halfHedges, results);

        return new BettingDto(bets);
    }

    private void addHedging(List<Prediction> bets, int halfHedges, List<String> possibleResults) {
        List<Integer> gamesToBeHedged = generateHedgingGames(bets.size(), halfHedges);

        for (int matchIndex : gamesToBeHedged) {
            Prediction existingPrediction = bets.get(matchIndex);

            existingPrediction.randomizeHedging(possibleResults);
        }
    }

    private List<Integer> generateHedgingGames(int games, int halfHedges) {
        List<Integer> matches = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < halfHedges ; i++) {

            int matchNumber;

            do {
                matchNumber = random.nextInt(games);
            } while (matches.contains(matchNumber));
            matches.add(matchNumber);
        }
        return matches;
    }

    private void validateBetCoupon(BetPredictionRequest request) {
        List<String> validPredictions = List.of("12", "1X", "X2", "1", "X", "2", "1X2");

        for (BetDto betDto: request.getBetDto()) {
            if (betDto.getPredictions().stream().noneMatch(validPredictions::contains)){
                throw new IllegalArgumentException("Invalid predictions found in the bet coupon");
            }
        }
    }

    private List<String> generateHedging(List<Integer> hedgedGamesIndexes, GamePredictionDto gamePrediction) {
        if(!hedgedGamesIndexes.contains(gamePrediction.getGameIndex()) && gamePrediction.getPredictions().size() > 1) {
            return List.of(generatePrediction(gamePrediction.getPredictions()));
        } else {
            if(gamePrediction.getPredictions().size() > 2) {
                removePrediction(gamePrediction.getPredictions());
                return gamePrediction.getPredictions();
            } else {
                return gamePrediction.getPredictions();
            }
        }
    }

    private String generatePrediction(List<String> predictions) {
        Random random = new Random();
        return predictions.get(random.nextInt(predictions.size()));
    }

    private void removePrediction(List<String> predictions) {
        Random random = new Random();
        int indexToBeRemoved = random.nextInt(predictions.size());
        predictions.remove(indexToBeRemoved);
    }

    private List<Integer> gamesToBeHedged(MultiplePixRequest request) {
        Random random = new Random();

        Set<Integer> gamesToStayHedged = new HashSet<>();
        while (gamesToStayHedged.size() < request.getGamesToBeHedged()) {
            int gameIndex = random.nextInt(request.getGamePredictions().size());
            if(!gamesToStayHedged.contains(gameIndex) && request.getGamePredictions().get(gameIndex).getPredictions().size() > 1) {
                gamesToStayHedged.add(gameIndex);
            }
        }

        return new ArrayList<>(gamesToStayHedged);
    }



    private PixCouponDto generatePixCoupon(BettingPixRequest request) {

        Map<String, String> pixBase = generatePixBase(request.getTotalGames());


        List<String> outcomes = List.of("1", "X", "2");
        Random random = new Random();
        for (Map.Entry<String, String> entry : pixBase.entrySet()) {
            int prediction = random.nextInt(3);

            entry.setValue(outcomes.get(prediction));
        }

        return new PixCouponDto(pixBase);
    }

    private Map<String, String> generatePixBase(int totalGames) {
        Map<String, String> pix = new TreeMap<>();
        for (int i = 1; i<totalGames+1; i++) {
            pix.put("Match " + i, "");
        }

        return pix;
    }

    private Map<String, String> generatePixGoals() {
        Map<String, String> pix = new TreeMap<>();
        Random random = new Random();

        for (int i = 1; i<13+1; i++) {
            int goals = random.nextInt(8);
            pix.put("Match " + i, String.valueOf(goals));
        }

        return pix;
    }


    private List<GameBaseDto> predictMatches(List<GameBaseDto> games, int homeWins, int draw, int awayWins) {
        List<GameBaseDto> predictedGames = new ArrayList<>(games);

        int homeResult = 0;
        int drawResult = 0;
        int awayResult = 0;

        // Shuffle the list to randomize the order
        Collections.shuffle(predictedGames);

        for (GameBaseDto selectedMatch : predictedGames) {

                if (selectedMatch.getPrediction() == null && homeResult < homeWins) {
                    selectedMatch.setPrediction("1");
                    homeResult++;
                } else if (selectedMatch.getPrediction() == null && drawResult < draw) {
                    selectedMatch.setPrediction("X");
                    drawResult++;
                } else if (selectedMatch.getPrediction() == null && awayResult < awayWins) {
                    selectedMatch.setPrediction("2");
                    awayResult++;
                }
        }

        return predictedGames;
    }

    private List<GameBaseDto> generateAllMatches(int totalMatches) {
        List<GameBaseDto> allMatches = new ArrayList<>();
        for (int i = 1; i <= totalMatches; i++) {
            allMatches.add(new GameBaseDto("Match " + i));
        }
        return allMatches;
    }
}
