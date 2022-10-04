package rs.edu.raf.msa.pbp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import rs.edu.raf.msa.pbp.model.Play;
import rs.edu.raf.msa.pbp.model.PlayByPlay;
import rs.edu.raf.msa.pbp.model.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PlayByPlayService playByPlayService;

    public PlayByPlay game(String gameId) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("games/" + gameId + ".json");

        PlayByPlay result = objectMapper.readValue(input, PlayByPlay.class);
        return result;
    }

    public List<String> games() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);

        Resource[] games;
        try {
            games = resolver.getResources("classpath:games/*.json");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error opening games!");
        }

        // TODO fill in the array with Resource.getFilename()
        List<String> result = new ArrayList<>(games.length);
        for(Resource game:games){
            String gameName = game.getFilename();
            result.add(gameName.substring(0,gameName.length()-5));
        }
        return result;
    }

    public List<Play> plays(String gameId, String fromMin, String toMin) throws IOException {
        // TODO load and parse the PlayByPlay, then filter fromMin, toMin
        // fromMin, toMin should be relative to the entire game

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("games/" + gameId + ".json");

        PlayByPlay result = objectMapper.readValue(input, PlayByPlay.class);

        return playByPlayService.play(result,fromMin,toMin);
    }

    public List<Player> players(String gameId) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("games/" + gameId + ".json");

        PlayByPlay result = objectMapper.readValue(input, PlayByPlay.class);

        return new ArrayList<Player>(result.getPlayers().values());
    }
}
