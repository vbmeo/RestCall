package restcall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.ModelUser;

public interface UserDao extends Dao {
	void createTable();
	void popolauser();//solo per il primo inserimento
	ModelUser getFromEMail(@Param("email") String email);
	void create(ModelUser modelUser);
	void update(ModelUser modelUser);

}
