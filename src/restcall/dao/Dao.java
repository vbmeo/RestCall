package restcall.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Dao {

	/**
	 * insert 
	 * @param oggetto
	 */
//	void create(@Param("oggetto") Object oggetto);
	/**
	 * select
	 * @param id
	 * @return
	 */
	Object read(@Param("id") int id);
//	void update(@Param("oggetto") Object oggetto);
	void delete(@Param("id") int id);
	List<?> selectAll();
	/**
	 * ESEGUE DIRETTAMENTE UNA QUERY IN BATIS<BR>
	 * Avendo creato un modello oggetto che ritorna semplicemente <br>
	 * la stringa passata, batis non sa di che tipo è e quindi lo passa letteralmente senza mettere apici
	 * @param queryXbatis
	 * @return
	 * ATTENZIONE!!!!!! QUI LASCIARE OBJECT!!! ALTRIMENTI NON VA CON IL VERO OGGETTO<br>
	 * va gestita eccezzione BadSqlGrammarException sempre
	 */
	List<?> executeQuery(@Param("sql") Object queryXbatis);
}
