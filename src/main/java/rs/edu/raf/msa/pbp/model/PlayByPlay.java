package rs.edu.raf.msa.pbp.model;

import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class PlayByPlay {

	Map<String, Player> players = new LinkedHashMap<>();
	
	List<Quarter> quarters = new ArrayList<>(4);

	String endTime;

	
}
