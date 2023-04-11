package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.mapper.ProdutoRowMapper;

@Repository
public class ProdutoRepository {

	private static final String GET_ALL = "SELECT * FROM tb_produto p "
			+ "INNER JOIN tb_categoria c ON p.id_categoria = c.id_categoria "
			+ "INNER JOIN tb_marca m ON p.id_marca = m.id_marca ORDER BY p.nome";
	private static final String GET = "SELECT * FROM tb_produto p "
			+ "INNER JOIN tb_categoria c ON p.id_categoria = c.id_categoria "
			+ "INNER JOIN tb_marca m ON p.id_marca = m.id_marca WHERE p.id = ?";
	private static final String SAVE = "INSERT INTO tb_produto (nome, sku, descricao, preco, caracteristicas, id_categoria, id_marca) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE tb_produto SET nome = ?, sku = ?, descricao = ?, "
			+ "preco = ?, caracteristicas = ?, id_categoria = ?, id_marca = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM tb_produto WHERE id = ?";

	@Autowired
	public JdbcTemplate jdbcTemplate;

	public ProdutoRepository() {
		super();
	}

	public List<ProdutoModel> findAll() {
		List<ProdutoModel> produtos = this.jdbcTemplate.query(GET_ALL, new ProdutoRowMapper());
		return produtos;
	}

	public ProdutoModel findById(long id) {
		ProdutoModel produto = this.jdbcTemplate.queryForObject(GET, new ProdutoRowMapper(), id);
		return produto;
	}

	public void save(ProdutoModel produtoModel) {
		jdbcTemplate.update(SAVE, produtoModel.getNome(), produtoModel.getSku(), produtoModel.getDescricao(),
				produtoModel.getPreco(), produtoModel.getCaracteristicas(),
				produtoModel.getCategoriaModel().getIdCategoria(), produtoModel.getMarcaModel().getIdMarca());
	}

	public void update(ProdutoModel produtoModel) {
		jdbcTemplate.update(UPDATE, produtoModel.getNome(), produtoModel.getSku(), produtoModel.getDescricao(),
				produtoModel.getPreco(), produtoModel.getCaracteristicas(),
				produtoModel.getCategoriaModel().getIdCategoria(), produtoModel.getMarcaModel().getIdMarca(),
				produtoModel.getId());
	}

	public void deleteById(long id) {
		jdbcTemplate.update(DELETE, id);
	}
}
