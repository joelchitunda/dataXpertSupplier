package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import br.dataxpert.supplier.conexaoBD.Conexao;
import br.dataxpert.supplier.model.Promocao;

public class PromocaoRepositoryImpl implements PromocaoRepository {

	public boolean SalvarPromocao(Promocao promocao) {

		Connection connection = null;
		
		//O código da promoção será um código universal único
		UUID uuid = UUID.randomUUID();
        promocao.setCodigo(uuid.toString());
        
		String sql = "INSERT INTO MDC_PROMOCAO ";
		sql += " (codigo, titulo, inicio, termino, fornecedor, data, usuario) ";
		sql += " VALUES ( ";
		sql += " '" + promocao.getCodigo() + "',";
		sql += "'" + promocao.getTitulo() + "',";
		sql += "TO_DATE('" + promocao.getInicio() + "','DD-MM-YYYY'),";
		sql += "TO_DATE('" + promocao.getTermino() + "','DD-MM-YYYY'),";
		sql += "'" + promocao.getFornecedor() + "',";
		sql += " SYSDATE,";
		sql += promocao.getUsuario() + ") ";

		boolean ret = false;

		try {

			connection = Conexao.getConexao();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeQuery();
			ret = true;

		} catch (Exception err) {

			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);
			ret = false;

		} finally {

			Conexao.close();

		}

		return ret;
	}
}
