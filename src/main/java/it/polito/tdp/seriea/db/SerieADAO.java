package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.seriea.model.Arco;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {
	
	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons" ;
		
		List<Season> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				result.add( new Season(res.getInt("season"), res.getString("description"))) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams" ;
		
		List<Team> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				result.add( new Team(res.getString("team"))) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<String> getVertex(Integer season){
		String sql = "select distinct HomeTeam\n" + 
				"from matches \n" + 
				"where Season = ?";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection() ;
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, season);
			ResultSet res = st.executeQuery() ;
			
			while (res.next()) {
				result.add(res.getString("HomeTeam"));
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Arco> getArchi (Integer season){
		String sql = "select HomeTeam, AwayTeam, FTR\n" + 
				"from matches \n" + 
				"where Season = ?";
		List<Arco> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection() ;
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, season);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				if (res.getString("FTR").compareTo("H")==0) {
					Arco a = new Arco(res.getString("HomeTeam"), res.getString("AwayTeam"), 1);
					result.add(a);
				} else if (res.getString("FTR").compareTo("A")==0) {
					Arco a = new Arco(res.getString("HomeTeam"), res.getString("AwayTeam"), -1);
					result.add(a);
				} else {
					Arco a = new Arco(res.getString("HomeTeam"), res.getString("AwayTeam"), 0);
					result.add(a);
				}	
			}
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}


}
