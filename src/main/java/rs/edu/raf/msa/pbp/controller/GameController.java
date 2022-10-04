package rs.edu.raf.msa.pbp.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.edu.raf.msa.pbp.model.Play;
import rs.edu.raf.msa.pbp.model.PlayByPlay;
import rs.edu.raf.msa.pbp.model.Player;
import rs.edu.raf.msa.pbp.model.Quarter;
import rs.edu.raf.msa.pbp.services.GameService;

@RestController
public class GameController {

	@Autowired
	GameService gameService;

	@GetMapping("/game/{gameId}")
	public PlayByPlay game(@PathVariable String gameId) throws JsonParseException, JsonMappingException, IOException {
		return gameService.game(gameId);
	}
	
	@GetMapping("/games")
	public List<String> games() {
		return gameService.games();
	}
	
	@GetMapping("/plays/{gameId}/{fromMin}/{toMin}")
	public List<Play> plays(@PathVariable String gameId,@PathVariable String fromMin, @PathVariable String toMin) throws IOException {
		// TODO load and parse the PlayByPlay, then filter fromMin, toMin
		// fromMin, toMin should be relative to the entire game

		return gameService.plays(gameId,fromMin,toMin);
	}

	@GetMapping("/players/{gameId}")
	public List<Player> players(@PathVariable String gameId) throws IOException{
		return gameService.players(gameId);
	}
}
