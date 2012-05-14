package SusansCitCloudApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class jdbcSwapRepository {

	private JdbcTemplate jdbcTemplate;

	public jdbcSwapRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void save(Swap swap) {
		jdbcTemplate.update("insert into SWAP (text, done) values(?,?)",
				swap.getText(), swap.isDone());
	}

	public Swap get(int id) {
		return jdbcTemplate.queryForObject(
				"select id, text, done from SWAP where id=?", new swapMapper(),
				id);
	}

	public List<Swap> getAll() {
		return jdbcTemplate.query("select id, text, done from Swap",
				new swapMapper());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from SWAP where id=?", id);
	}

	public void update(Swap swap) {
		jdbcTemplate.update("update TODO set text=?, done=? where id=?",
				swap.getText(), swap.isDone(), swap.getId());
	}
}

class TodoMapper implements RowMapper<Swap> {

	public Swap mapRow(ResultSet rs, int rowNum) throws SQLException {
		Swap swap = new Swap();
		swap.setId(rs.getInt("id"));
		swap.setText(rs.getString("text"));
		swap.setDone(rs.getBoolean("done"));
		return swap;
	}

}


