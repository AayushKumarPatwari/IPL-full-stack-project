package com.iplProject.ipl_dashboard.Controller;

import com.iplProject.ipl_dashboard.Model.Match;
import com.iplProject.ipl_dashboard.Model.Team;
import com.iplProject.ipl_dashboard.Repository.MatchRepository;
import com.iplProject.ipl_dashboard.Repository.TeamRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

        private static final Logger log = LoggerFactory.getLogger(TeamController.class);


    private TeamRepository teamRepository;
    public final MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository ,MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }


    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName,4));
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
//        return matchRepository.getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
//                teamName,
//                startDate,
//                endDate,
//                teamName,
//                startDate,
//                endDate
//        );
        return matchRepository.getMatchesByTeamBetweenDates(
                teamName,
                startDate,
                endDate
        );
    }
}
