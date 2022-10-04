package rs.edu.raf.msa.pbp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Play {

	// TODO add fields
    int p;

    // tajmer jos koliko je ostalo do kraja quartera
    @JsonProperty("c")
    String clock;

    @JsonProperty("d")
    String description;

    @JsonProperty("t")
    String team;

    @JsonProperty("atin") //gamelenght
    String realTime;

    int x;
    int y;
    @JsonProperty("hs")
    int homeScore;

    int id;

    @JsonProperty("vs")
    int visitorScore;

    //rebound steal
    @JsonProperty("et")
    String action;
}
