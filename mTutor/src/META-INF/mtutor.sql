SET @rank = 1, @prev_val = NULL, @prev_rank = NULL

SELECT @rank := IF(@prev_val!=points,@prev_rank+1,@rank) AS rank, @prev_val := points AS points, @prev_rank := @rank AS prevRank, t.studentId, t.firstName
     , t.lastName
  FROM 
     (
      SELECT studentId, sc.points as points,firstName,lastName
             FROM StudentConcept sc INNER JOIN Student s ON sc.studentId = s.id
			ORDER BY points DESC
     ) AS t
 where studentId =1