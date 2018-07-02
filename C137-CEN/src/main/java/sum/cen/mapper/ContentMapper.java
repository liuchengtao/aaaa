package sum.cen.mapper;

/**
 * 
 * @author cen    2018年6月25日下午3:03:58
 *
 * @param <T>
 */
public interface ContentMapper<T> extends BaseMapper<T>{
       public int queryByNovelId(Object novelId);
	
}
