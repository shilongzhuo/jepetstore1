package org.csu.myjpetstore.persistence;

import java.util.List;

import org.csu.myjpetstore.domain.Category;

public interface CategoryDAO {
	String getCategoryListSQL = "SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY";
	String getCategorySQL = "SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY WHERE CATID=?";
	
	List<Category> getCategoryList();

	Category getCategory(String categoryId);
}
