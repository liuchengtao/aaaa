package sum.cen.mapper;

import java.util.List;


/**
 * 
 * @author cen    2018年6月6日上午9:18:42
 *
 * @param <T>
 */
public interface BaseMapper<T> { 
	void add(T t);
	void update(T t);
    void updateBySelective(T t); 
    void delete(Object id);
    
     int queryByCount(T t);
	
	 List<T> queryByList(T t);
	
	
	 T queryById(Object id);

}
