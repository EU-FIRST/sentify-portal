package test.sentify.search;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryparser.classic.ParseException;

import eu.first.sentify.search.model.SentimentObject;
import eu.first.sentify.searchsentimentobjects.CompanySearch;

public class SearchURIs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompanySearch cs = CompanySearch.getInstance();

		String list = 
				"http://project-first.eu/ontology#comp_AXA_S_A\r\n" + 
				"http://project-first.eu/ontology#comp_ANZ_BANKING_GRP\r\n" + 
				"http://project-first.eu/ontology#comp_BANK_OF_AMERICA_CORP\r\n" + 
				"http://project-first.eu/ontology#comp_BARCLAYS_PLC\r\n" + 
				"http://project-first.eu/ontology#comp_BANCO_BILBAO_VIZCAYA_ARGENTARIA_S_A\r\n" + 
				"http://project-first.eu/ontology#comp_BNP_PARIBAS_S_A\r\n" + 
				"http://project-first.eu/ontology#comp_CITIGROUP_INC\r\n" + 
				"http://project-first.eu/ontology#comp_COMMERZBANK_AG\r\n" + 
				"http://project-first.eu/ontology#comp_CREDIT_AGRICOLE_S_A\r\n" + 
				"http://project-first.eu/ontology#comp_CREDIT_SUISSE_GROUP_AG\r\n" + 
				"http://project-first.eu/ontology#comp_DEUTSCHE_BANK_AG\r\n" + 
				"http://project-first.eu/ontology#comp_HSBC_HOLDINGS_PLC\r\n" + 
				"http://project-first.eu/ontology#comp_ING_GROEP_N_V\r\n" + 
				"http://project-first.eu/ontology#comp_INTESA_SANPAOLO_S_P_A\r\n" + 
				"http://project-first.eu/ontology#comp_JPMORGAN_CHASE_CO\r\n" + 
				"http://project-first.eu/ontology#comp_LLOYDS_BANKING_GROUP_PLC\r\n" + 
				"http://project-first.eu/ontology#comp_MEDIOBANCA_S_P_A\r\n" + 
				"http://project-first.eu/ontology#comp_BANCA_MONTE_DEI_PASCHI_DI_SIENA_S_P_A\r\n" + 
				"http://project-first.eu/ontology#comp_MORGAN_STANLEY\r\n" + 
				"http://project-first.eu/ontology#comp_NATL_AUST_BANK\r\n" + 
				"http://project-first.eu/ontology#comp_Cooperatieve_Centrale_Raiffeisen-Boerenleenbank_BANetherlands\r\n" + 
				"http://project-first.eu/ontology#comp_Raiffeisen_Zentralbank_Oesterreich_AG\r\n" + 
				"http://project-first.eu/ontology#comp_BANCO_SANTANDER_S_A\r\n" + 
				"http://project-first.eu/ontology#comp_SOCIETE_GENERALE_S_A\r\n" + 
				"http://project-first.eu/ontology#comp_STANDARD_CHARTERED_PLC\r\n" + 
				"http://project-first.eu/ontology#comp_UBS_AG\r\n" + 
				"http://project-first.eu/ontology#comp_UNICREDIT_S_P_A\r\n" + 
				"";
		
		String[] counterparts = list.split("\r\n");
		for (String s : counterparts) {
			String company = cs.getCompanyNameByURI(s);
			System.out.println(company);

		}
		

	}

}
