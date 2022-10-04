package rs.edu.raf.msa.pbp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Player {
	@JsonProperty("c")
	String fullname;
	@JsonProperty("f")
	String firstname;
	@JsonProperty("l")
	String lastname;
	
}
