package DAOclass;

import org.json.JSONException;

public interface DAOClassImp {

	public void findAll() throws JSONException;
	public void  findById(String empId) throws JSONException;
	public String update(String theEmployee) throws JSONException;
	public String delete(String id) throws JSONException;
}
