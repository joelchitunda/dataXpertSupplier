package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.model.Promocao;
import br.dataxpert.supplier.model.PromocaoItem;

@Repository
public class PromocaoRepositoryImpl implements PromocaoRepository {

	public String SalvarPromocao(Promocao promocao) {

		// O código da promoção será um código universal único
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

		String ret = "";

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@132.145.163.36:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			preparedStatement.executeQuery();
			ret = promocao.getCodigo();

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception err) {

			String errMsg = err.getMessage();
			// logger.info("Erro geral : {}", errMsg);
			ret = "";

		}

		return ret;

	}

	@Override
	public boolean SalvarPromocaoItens(List<PromocaoItem> itens) {

		boolean ret = false;

		for (PromocaoItem item : itens) {

			String sql = "INSERT INTO MDC_PROMOCAOITEM ";
			sql += " (codigo, codprod, qtde_min, preco_real, perc_desc, preco_promo, prazo_pagto) ";
			sql += " VALUES ( ";
			sql += "'" + item.promocaoId + "',";
			sql += item.codprod + ",";
			sql += item.qtde_min + ", ";
			if (item.preco_real.contains(","))
				item.preco_real = item.preco_real.replace(',', '.');
			sql += item.preco_real + ", ";
			if (item.perc_desc.contains(","))
				item.perc_desc = item.perc_desc.replace(',', '.');
			sql += item.perc_desc + ", ";
			if (item.preco_promo.contains(","))
				item.preco_promo = item.preco_promo.replace(',', '.');
			sql += item.preco_promo + ", ";
			sql += "'" + item.prazo_pagto + "') ";

			try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.0.15:1521/WINTHOR", "WINTHOR",
					"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
				preparedStatement.executeQuery();

				ret = true;

			} catch (SQLException e) {

				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
				
				ret=false;

			} catch (Exception e) {

				ret = false;
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

		return ret;

	}

}
