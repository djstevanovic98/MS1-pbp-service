package rs.edu.raf.msa.pbp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import rs.edu.raf.msa.pbp.model.Play;
import rs.edu.raf.msa.pbp.model.PlayByPlay;
import rs.edu.raf.msa.pbp.model.Player;

@SpringBootTest
@Slf4j
class GameControllerTest {

	@Autowired
	GameController gameController;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void gameLoaded() throws IOException {
		PlayByPlay pbp = gameController.game("20200924LALDEN");
		assertNotNull(pbp);

		String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pbp);
		log.debug(formattedJson);
	}

	@Test
	void gamesLoaded() throws IOException {
		List<String> games = gameController.games();
		System.out.println("----------->" + games);
		assertNotNull(games);

		log.debug("{}", games);
		assertThat(games).contains("20200924LALDEN", "20200930MIALAL", "20201002MIALAL");
	}

	@Test
	void plays() throws IOException {
		List<Play> plays = gameController.plays("20200924LALDEN", "00:10", "00:30");
		List<Play> supposed = new ArrayList<>();

		Play play = new Play(0,"11:35","[DEN] Murray Turnover : Bad Pass (1 TO) Steal:Howard (1 ST)","DEN","00:03:07:08",-137,115,0,7,0,"s");
		Play play1 = new Play(0,"11:31","[LAL] Caldwell-Pope Running Finger Roll Layup Shot: Missed","LAL","00:03:11:08",9,10,0,9,0,null);

		supposed.add(play);supposed.add(play1);

		log.debug("{}", plays);
		assertNotNull(plays);
		assertThat(plays).size().isEqualTo(2);
		assertThat(plays).isEqualTo(supposed);
	}

	@Test
	void players() throws IOException {
		List<Player> players = gameController.players("20200924LALDEN");
		log.debug("{}", players);

		Player jokic = new Player();
		jokic.setFirstname("Nikola");
		jokic.setLastname("Jokic");
		jokic.setFullname("nikola_jokic");

		assertNotNull(players);
		assertThat(players).size().isEqualTo(34);
		assertThat(players).contains(jokic);
	}
}
