package com.iplProject.ipl_dashboard.Repository;

import com.iplProject.ipl_dashboard.Model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByTeamName(String teamName);
}
