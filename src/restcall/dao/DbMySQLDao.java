package restcall.dao;

import org.apache.ibatis.annotations.Param;

public interface DbMySQLDao {
	void Create(@Param("nomeDb") String nomeDb);
}
