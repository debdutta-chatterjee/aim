package sm.api.pojo;



import com.fasterxml.jackson.annotation.JsonAlias;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class ClubData{
	@JsonAlias({ "ENG", "ESP" })
    public DivisionContainer divisionContainer;
}