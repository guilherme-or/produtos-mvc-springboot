package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.CategoriaModel;

@Repository
public class CategoriaRepository {

	private static final String GET_ALL = "SELECT * FROM tb_categoria";
	private static final String SAVE = "INSERT INTO tb_categoria (nome_categoria) VALUES (?)";
	private static final String GET = "SELECT * FROM tb_categoria WHERE id_categoria = ?";
	private static final String UPDATE = "UPDATE tb_categoria SET nome_categoria = ?" + " WHERE id_categoria = ?";
	private static final String DELETE = "DELETE FROM tb_categoria WHERE id_categoria = ?";

	@Autowired
	public JdbcTemplate jdbcTemplate;

	public CategoriaRepository() {
		super();
	}

	public List<CategoriaModel> findAll() {
		List<CategoriaModel> categorias = this.jdbcTemplate.query(GET_ALL,
				new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class));
		return categorias;
	}

	public CategoriaModel findById(long id) {
		CategoriaModel categoria = this.jdbcTemplate.queryForObject(GET,
				new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class), id);
		return categoria;
	}

	public void save(CategoriaModel categoriaModel) {
		jdbcTemplate.update(SAVE, categoriaModel.getNomeCategoria());
	}

	public void update(CategoriaModel categoriaModel) {
		jdbcTemplate.update(UPDATE, categoriaModel.getNomeCategoria(), categoriaModel.getIdCategoria());
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
