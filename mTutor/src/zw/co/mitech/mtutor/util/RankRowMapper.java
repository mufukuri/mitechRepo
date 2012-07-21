package zw.co.mitech.mtutor.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class RankRowMapper implements RowMapper<Rank>{

	@Override
	public Rank mapRow(ResultSet rs, int rowNum) throws SQLException {
		Rank rank = new Rank();
		
		try {
			rank.setFirstName(rs.getString("firstName"));
		} catch (Exception e) {
			//if column not found ignore and continue
		}
		
		try {
			rank.setLastName(rs.getString("lastName"));
		} catch (Exception e) {
			//if column not found ignore and continue
		}
		
		try {
			rank.setPoints(rs.getLong("points"));
		} catch (Exception e) {
			//if column not found ignore and continue
		}
		
		try {
			rank.setPrevRank(rs.getLong("prevRank"));
		} catch (Exception e) {
			//if column not found ignore and continue
		}
		try {
			rank.setRank(rs.getLong("rank"));
		} catch (Exception e) {
			//if column not found ignore and continue
		}
		
		try {
			rank.setStudentId(rs.getLong("studentId"));
		} catch (Exception e) {
			//if column not found ignore and continue
		}
		return rank;
	}

}
