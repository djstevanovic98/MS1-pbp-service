package rs.edu.raf.msa.pbp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Quarter {

	@JsonProperty("q")
	int quarter;
	
	List<Play> plays = new ArrayList<>(100);

	@JsonProperty("atin")
	String startTimeFull;

}