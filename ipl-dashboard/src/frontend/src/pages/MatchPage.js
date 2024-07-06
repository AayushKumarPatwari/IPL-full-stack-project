import {React, useEffect, useState} from 'react';
import { useParams, Link } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import './MatchPage.scss';
import { YearSelector } from '../components/YearSelector';

export const MatchPage = () => {
    const [matches, setMatches] = useState([]);
    const {teamName, year} = useParams();

    useEffect(
        () => {
            const fetchMathes = async() => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                const data = await response.json();
                setMatches(data);
            };
            fetchMathes();
        },[teamName,year]
    );
   

  return (
    <div>
      <Link to="/">HOME</Link>
    
      <div className="MatchPage">
        <div className="year-selector">
          <h3>Select Year</h3>
          <YearSelector teamName={teamName}/>
        </div>
        <div>
          <h1 className="page-heading">{teamName} matches in {year}</h1>
          {matches.map(match => <MatchDetailCard teamName = {teamName} match={match}/>)}
        </div>
      </div>
    </div>
  );
}
