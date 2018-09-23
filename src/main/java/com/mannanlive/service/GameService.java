package com.mannanlive.service;

import com.mannanlive.builder.SpecificationBuilder;
import com.mannanlive.entity.GameEntity;
import com.mannanlive.model.SearchCriterion;
import com.mannanlive.model.console.Consoles;
import com.mannanlive.model.game.Game;
import com.mannanlive.model.game.Games;
import com.mannanlive.model.genre.Genres;
import com.mannanlive.repository.ConsoleRepository;
import com.mannanlive.repository.GameRepository;
import com.mannanlive.translator.ConsoleTranslator;
import com.mannanlive.translator.GameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    private GameTranslator gameTranslator;

    @Autowired
    private ConsoleTranslator consoleTranslator;

    private SpecificationBuilder<GameEntity> specificationBuilder = new SpecificationBuilder<>();

    public Game findById(long gameId) {
        Optional<GameEntity> result = gameRepository.findById(gameId);
        if (result.isPresent()) {
            return gameTranslator.translate(result.get());
        }
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    public Genres listAllGenres(int pageNumber, int pageSize) {
        int safeGetPageSize = getSafeGetPageSize(pageSize);
        return new Genres(gameRepository.findAllGenres(PageRequest.of(pageNumber, safeGetPageSize)));
    }

    public Consoles listAllConsoles() {
        return new Consoles(consoleRepository.findAll()
                .stream()
                .map(consoleEntity -> consoleTranslator.translate(consoleEntity))
                .collect(toList()));
    }

    public Games searchGames(List<SearchCriterion> searchCriteria, int pageNumber, int pageSize) {
        int safeGetPageSize = getSafeGetPageSize(pageSize);
        Specification<GameEntity> specification = specificationBuilder.createSpecification(searchCriteria);
        Page<GameEntity> gameEntities = gameRepository.findAll(specification, PageRequest.of(pageNumber, safeGetPageSize));
        return new Games(gameEntities.getContent()
                .stream()
                .map(gameEntity -> gameTranslator.translate(gameEntity))
                .collect(toList()));
    }

    private int getSafeGetPageSize(int pageSize) {
        return pageSize < 1 ? Integer.MAX_VALUE : pageSize;
    }
}
