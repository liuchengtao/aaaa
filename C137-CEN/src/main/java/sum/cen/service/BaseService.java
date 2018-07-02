package sum.cen.service;

import java.util.List;



import sum.cen.mapper.BaseMapper;
/**
 * 
 * @author cen    2018��6��6������11:29:20
 *
 * @param <T>
 */
public abstract class BaseService<T> {
	 public  abstract BaseMapper<T> getMapper();
     //public ����public���εĻ�  �����޷����ø÷���
     public   void  add(T t){
    	 getMapper().add(t);
     };
     public     void  delete(Object id){
    	 getMapper().delete(id);
     }
     public    void  update(T t){
    	 getMapper().update(t);
     }
     public    void updateBySelective(T t){
 		getMapper().updateBySelective(t);
 	}
     public    void deletes(Object... ids){
    	  if(ids==null||ids.length<1){
    		  return;
    	  }
    	  for(Object id:ids){
    		  this.getMapper().delete(id);
    	  }
      }
      public int queryByCount(T model)throws Exception{
  		return getMapper().queryByCount(model);
  	}
  	
  	public List<T> queryByList(T model) throws Exception{
  		//Integer rowCount = queryByCount(model);
  		//model.getPager().setRowCount(rowCount);
  		return getMapper().queryByList(model);
  	}

  	public T queryById(Object id) throws Exception{
  		return getMapper().queryById(id);
  	}
}
