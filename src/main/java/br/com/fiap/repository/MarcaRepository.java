package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.MarcaModel;

@Repository
public class MarcaRepository {

	private static final String GET_ALL = "SELECT id_marca, nome_marca FROM tb_marca ORDER BY nome_marca";
	private static final String SAVE = "INSERT INTO tb_marca (nome_marca) VALUES (?)";
	private static final String GET = "SELECT id_marca, nome_marca FROM tb_marca WHERE id_marca = ?";
	private static final String UPDATE = "UPDATE tb_marca SET nome_marca = ?" + " WHERE id_marca = ?";
	private static final String DELETE = "DELETE FROM tb_marca WHERE id_marca = ?";

	@Autowired
	public JdbcTemplate jdbcTemplate;

	public MarcaRepository() {
		super();
	}

	public List<MarcaModel> findAll() {
		List<MarcaModel> marcas = this.jdbcTemplate.query(GET_ALL,
				new BeanPropertyRowMapper<MarcaModel>(MarcaModel.class));
		return marcas;
	}

	public MarcaModel findById(long id) {
		MarcaModel marca = this.jdbcTemplate.queryForObject(GET,
				new BeanPropertyRowMapper<MarcaModel>(MarcaModel.class), id);
		return marca;
	}

	public void save(MarcaModel marcaModel) {
		jdbcTemplate.update(SAVE, marcaModel.getNomeMarca());
	}

	public void update(MarcaModel marcaModel) {
		jdbcTemplate.update(UPDATE, marcaModel.getNomeMarca(), marcaModel.getIdMarca());
	}

	public boolean deleteById(long id) {
		try {
			jdbcTemplate.update(DELETE, id);
		} catch (DataIntegrityViolationException e) {
			return false;
		}

		return true;
	}
}
