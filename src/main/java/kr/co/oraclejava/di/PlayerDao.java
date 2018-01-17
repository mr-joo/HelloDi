package kr.co.oraclejava.di;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class PlayerDao extends JdbcDaoSupport{
	//전체 선수 목록
	public List<Player> getPlayerList() {
		String sql = "select * from player";
		List<Player> list = this.getJdbcTemplate().query(sql, new RowMapper() {
			public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
				Player player = new Player();
				player.setId(rs.getString("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getString("age"));
				player.setSalary(rs.getString("salary"));
				return player;
			}
		});
		return list;
	}
	
	//해당 ID의 선수 리턴
	public Player getPlayer(String playerid) {
		String sql = "select * from player where id = ?";
		Player player = null;
		
		player = this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Player>(Player.class), playerid);
		
		return player;
	}
	
	//선수 테이블에 선수 추가
	public void insertPlayer(Player player) {
		String sql = "insert into player(name, age, salary) values(?, ?, ?)";
		this.getJdbcTemplate().update(sql, player.getName(), player.getAge(), player.getSalary());
	}
	
	//선수 테이블에 선수 수정
	public void updatePlayer(Player player) {
		String sql = "update player set name=?, age=?, salary=?" + "wher id=?";
		this.getJdbcTemplate().update(sql, player.getName(), player.getAge(), player.getSalary(), player.getId());
	}
	
	//선수 테이블에 선수 삭제
	public void deletePlayer(Player player) {
		String sql = "delete from player " + "where id=?";
		this.getJdbcTemplate().update(sql, player.getId());
	}
}

