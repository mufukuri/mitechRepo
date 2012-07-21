package zw.co.mitech.mtutor.session;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import zw.co.mitech.mtutor.util.Rank;
import zw.co.mitech.mtutor.util.RankRowMapper;

public class RankingsFacade {
	
  private SimpleJdbcTemplate jdbcTemplate;
  
  public RankingsFacade() {
	super();
  }

  public void setDataSource(DataSource dataSource) {
          this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
  }
  
  public List<Rank> getStudentRankingsByAcademicLevel(long academicLevelId,int numOfStudents){
	  	StringBuffer sql = new StringBuffer();
		
	  	String initSql = "SET @rank = 1, @prev_val = NULL, @prev_rank = NULL;";
	  	
	  	sql.append(" SELECT @rank :=IF(@prev_val != points,@prev_rank+1,@rank) AS rank, @prev_val :=points AS points, @prev_rank :=@rank AS prevRank, t.studentId, t.firstName");
		sql.append(" FROM ( ");
			sql.append(" SELECT studentId, sc.points as points,firstName,lastName");
			sql.append(" FROM StudentSubject sc INNER JOIN Student s ON sc.studentId = s.id");
			sql.append(" WHERE sc.academicLevelId = ? ");
			sql.append(" ORDER BY points DESC");
		sql.append(" ) AS t LIMIT 0," + numOfStudents);
		
		//initialise variables
		jdbcTemplate.update(initSql);
		
		//get rankings
	    return jdbcTemplate.query(sql.toString(), new RankRowMapper(),academicLevelId);
	  
  }

public Rank getStudentRankingByQuiz(long studentId, long quizid) {
	StringBuffer sql = new StringBuffer();
	
  	String initSql = "SET @rank = 1, @prev_val = NULL, @prev_rank = NULL;";
  	
  	sql.append(" SELECT @rank :=IF(@prev_val != points,@prev_rank+1,@rank) AS rank, @prev_val :=points AS points, @prev_rank :=@rank AS prevRank, t.studentId, t.firstName");
	sql.append(" FROM ( ");
		sql.append(" SELECT studentId, sc.points as points,firstName,lastName");
		sql.append(" FROM StudentQuiz sc INNER JOIN Student s ON sc.studentId = s.id");
		sql.append(" WHERE sc.studentId = ?  AND sc.quizId = ?");
		sql.append(" ORDER BY points DESC");
	sql.append(" ) AS t LIMIT 0,1" );
	
	//initialise variables
	jdbcTemplate.update(initSql);
	
	//get rankings
    return jdbcTemplate.queryForObject(sql.toString(), new RankRowMapper(),studentId,quizid);
}

public int getNumberOfStudentsThatTookQuiz(Long quizId) {
	String sql = "SELECT Count(*) FROM StudentQuiz s WHERE s.quizId = ?";
	return jdbcTemplate.queryForInt(sql, quizId);
}
  
  

}
