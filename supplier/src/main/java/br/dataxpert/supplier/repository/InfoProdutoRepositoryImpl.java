package br.dataxpert.supplier.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.dataxpert.supplier.model.InfoProduto;

@Repository
public class InfoProdutoRepositoryImpl implements InfoProdutoRepository {

	
	public List<InfoProduto> ObterInfoProdutoPorEAN(String filial, String ean) {

		// Eliminar zero a esquerda
		Long eanNumber = Long.parseLong(ean);
		ean = eanNumber.toString();
		
		String sql = " SELECT PCEST.CODPROD COD_INTERNO  ";
        sql += " , PCPRODUT.CODAUXILIAR EAN  ";
        sql += " , PCPRODUT.CODAUXILIAR2 DUN  ";
        sql += " , NVL(PCPRODUT.DESCRICAO_ECOMMERCE, PCPRODUT.DESCRICAO) PRODUTO  ";
        sql += " , PCMARCA.MARCA  ";
        sql += " , PCLINHAPROD.DESCRICAO LINHA  ";
        sql += " , CASE WHEN NVL(PCPRODUT.OBS2,'X') = 'FL'  ";
        sql += "          OR NVL(PCTABPR.PVENDA,0)  = 0  ";
        sql += "          OR NVL(PCEST.QTESTGER,0) -NVL(PCEST.QTRESERV, 0) - NVL(PCEST.QTBLOQUEADA, 0) - NVL(PCEST.QTPENDENTE, 0) <= 0  ";
        sql += "          OR NVL(PCPRODUT.ENVIAECOMMERCE,'N') = 'N'  ";
        sql += "          OR PCPRODUT.DESCRICAO_ECOMMERCE IS NULL ";
        sql += "         THEN 'NÃO'  ";
        sql += "        ELSE 'SIM'  ";
        sql += "   END PARTICIPA_ECOMMERCE  ";
        sql += " , CASE WHEN NVL(PCPRODUT.RAPPI,'N') = 'N' THEN 'NÃO'  ";
        sql += "        ELSE 'SIM'  ";
        sql += "   END PARTICIPA_RAPPI  ";
        sql += " , UPPER(PCPRODUT.RESUMO_ECOMMERCE) RESUMO_ITEM  ";
        sql += " , UPPER(PCPRODUT.INFORMACOESTECNICAS1_ECOMMERCE) INDICACAO  ";
        sql += " , UPPER(PCPRODUT.INFORMACOESTECNICAS2_ECOMMERCE) ENRIQUECIDO_COM  ";
        sql += " , UPPER(PCPRODUT.INFORMACOESTECNICAS3_ECOMMERCE) APLICACAO  ";
        sql += " , UPPER(PCPRODUT.INFORMACOESTECNICAS4_ECOMMERCE) RESULTADO  ";
        sql += " , NVL(PCTABPR.PVENDA, 0) PRECO  ";
        sql += " , NVL(PCEST.QTESTGER, 0) ESTOQUE_FISICO  ";
        sql += " , NVL(PCEST.QTINDENIZ, 0) ESTOQUE_AVARIA  ";
        sql += " , NVL(PCEST.QTBLOQUEADA, 0) ESTOQUE_BLOQUEADO  ";
        sql += " , NVL(PCEST.QTRESERV, 0) ESTOQUE_RESERVADO  ";
        sql += " , NVL(PCEST.QTESTGER, 0) - NVL(PCEST.QTRESERV, 0) - NVL(PCEST.QTBLOQUEADA, 0) - NVL(PCEST.QTPENDENTE, 0) ESTOQUE_DISPONIVEL  ";
        sql += "  FROM PCPRODUT  ";
        sql += "     LEFT JOIN PCEST     ON(PCPRODUT.CODPROD = PCEST.CODPROD)  ";
        sql += " LEFT JOIN PCFILIAL ON(PCEST.CODFILIAL = PCFILIAL.CODIGO)  ";
        sql += " LEFT JOIN(  ";
        sql += "                SELECT PCREGIAO.NUMREGIAO  ";
        sql += "                     , PCREGIAO.REGIAO  ";
        sql += "                     , PCREGIAO.UF  ";
        sql += "                     , PCFILIAL.CODIGO CODFILIAL  ";
        sql += "                     , PCFILIAL.FANTASIA LOJA  ";
        sql += "                  FROM PCREGIAO  ";
        sql += "                    , (  ";
        sql += "                            SELECT PCPARAMFILIAL.CODFILIAL  ";
        sql += "                                 , PCPARAMFILIAL.VALOR NUMREGIAO  ";
        sql += "                              FROM PCPARAMFILIAL  ";
        sql += "                             WHERE 1 = 1  ";
        sql += "                               AND PCPARAMFILIAL.NOME = 'NUMREGIAOPADRAOVAREJO'  ";
        sql += "                        ) PARAM  ";
        sql += "                     , PCFILIAL  ";
        sql += "                 WHERE 1 = 1  ";
        sql += "                   AND PCREGIAO.NUMREGIAO = PARAM.NUMREGIAO  ";
        sql += "                   AND PARAM.CODFILIAL = PCFILIAL.CODIGO  ";
        sql += "                   AND PCFILIAL.DTEXCLUSAO IS NULL  ";
        sql += "                   AND NVL(PCREGIAO.STATUS, 'A') = 'A'  ";
        sql += "       ) REGIAO ON(REGIAO.CODFILIAL = PCFILIAL.CODIGO)  ";
        sql += " LEFT JOIN PCTABPR ON(PCTABPR.NUMREGIAO = REGIAO.NUMREGIAO AND PCTABPR.CODPROD = PCEST.CODPROD)  ";
        sql += " LEFT JOIN PCMARCA ON(PCPRODUT.CODMARCA = PCMARCA.CODMARCA)  ";
        sql += " LEFT JOIN PCLINHAPROD ON(PCPRODUT.CODLINHAPROD = PCLINHAPROD.CODLINHA)  ";
        sql += " WHERE 1 = 1  ";
        sql += " AND PCFILIAL.DTEXCLUSAO IS NULL  ";
        sql += " AND PCFILIAL.CODIGO NOT IN(99)  ";
        sql += " AND(PCPRODUT.CODAUXILIAR  IN(" + ean + ")  ";
        sql += "       OR PCPRODUT.CODAUXILIAR2 IN(" + ean + ")  ";
        sql += "     )  ";
        sql += " AND PCEST.CODFILIAL IN(" + filial + ")  ";

		List<InfoProduto> results = new ArrayList<InfoProduto>();

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.0.15:1521/WINTHOR", "WINTHOR",
				"WINTHOR"); PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				InfoProduto item = new InfoProduto();
				String cod_Interno = resultSet.getString("COD_INTERNO").toString();
				item.setCod_interno(cod_Interno);
				String eanItem = resultSet.getString("EAN").toString();
				item.setEan(eanItem);
				String dun = resultSet.getString("DUN").toString();
				item.setDun(dun);
				String produto = resultSet.getString("PRODUTO").toString();
				item.setProduto(produto);
				String marca = resultSet.getString("MARCA").toString();
				item.setMarca(marca);
				String linha = resultSet.getString("LINHA").toString();
				item.setLinha(linha);
				String participa_ecommerce = resultSet.getString("PARTICIPA_ECOMMERCE").toString();
				item.setParticipa_ecommerce(participa_ecommerce);
				String participa_rappi = resultSet.getString("PARTICIPA_RAPPI").toString();
				item.setParticipa_rappi(participa_rappi);
				String resumo_item = resultSet.getString("RESUMO_ITEM").toString();
				item.setResumo_item(resumo_item);
				String indicacao = resultSet.getString("INDICACAO").toString();
				item.setIndicacao(indicacao);
				String enriquecido_com = resultSet.getString("ENRIQUECIDO_COM").toString();
				item.setEnriquecido_com(enriquecido_com);
				String aplicacao = resultSet.getString("APLICACAO").toString();
				item.setAplicacao(aplicacao);
				String resultado = resultSet.getString("RESULTADO").toString();
				item.setResultado(resultado);
				String preco = resultSet.getString("PRECO").toString();
				item.setPreco(preco);
				String estoque_fisico = resultSet.getString("ESTOQUE_FISICO").toString();
				item.setEstoque_fisico(estoque_fisico);
				String estoque_avaria = resultSet.getString("ESTOQUE_AVARIA").toString();
				item.setEstoque_avaria(estoque_avaria);
				String estoque_bloqueado = resultSet.getString("ESTOQUE_BLOQUEADO").toString();
				item.setEstoque_bloqueado(estoque_bloqueado);
				String estoque_reservado = resultSet.getString("ESTOQUE_RESERVADO").toString();
				item.setEstoque_reservado(estoque_reservado);
				String estoque_disponivel = resultSet.getString("ESTOQUE_DISPONIVEL").toString();
				item.setEstoque_disponivel(estoque_disponivel);
				results.add(item);
		
			}

		} catch (SQLException e) {

			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return results;

	}

}
