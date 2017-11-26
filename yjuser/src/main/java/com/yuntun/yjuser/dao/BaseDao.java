package com.yuntun.yjuser.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuntun.utils.MyPage;

public class BaseDao<T> {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private Class<T> clazz;

	@PersistenceContext
	private EntityManager entityManager;
	
	public String getTableName() {
		return clazz.getAnnotation(Entity.class).name();
	}

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	/**
	 * 反射 通过构造方法指定DAO的具体实现类
	 * 
	 * getClass().getGenericSuperclass()
	 * 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type，然后将其转换ParameterizedType。
	 * 
	 * getActualTypeArguments()
	 * 返回表示此类型实际类型参数的 Type 对象的数组。[0]就是这个数组中第一个了。简而言之就是获得超类的泛型参数的实际类型。
	 */
	@SuppressWarnings("unchecked")
	public BaseDao() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public MyPage<T> findPageByCriteria(final DetachedCriteria detachedCriteria) {
		return findPageByCriteria(detachedCriteria, MyPage.PAGESIZE, 0);
	}
	
	public MyPage<T> findPageByCriteria(final DetachedCriteria detachedCriteria, int page) {
		return findPageByCriteria(detachedCriteria, MyPage.PAGESIZE, page);
	}
	
	@SuppressWarnings("unchecked")
	public MyPage<T> findPageByCriteria(final DetachedCriteria detachedCriteria, int pageSize, int page) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		
		Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();
		MyPage<T> ps = new MyPage<T>((Integer) object, pageSize, page);
		
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<T> items = criteria.setFirstResult(ps.getStartindex()).setMaxResults(pageSize).list();
		
		ps.setItems(items);
		return ps;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		return (List<T>) detachedCriteria.getExecutableCriteria(getSession());
	}
	
	/**
	 * 插入对象
	 * @param entity
	 */
	public void save(T entity) {
		log.debug("saving "+ getTableName() +" instance");
		try {
			getSession().save(entity);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	/**
	 * 删除对象
	 * @param entity
	 */
	public void delete(T entity) {
		log.debug("deleting "+ getTableName() +" instance");
		try {
			getSession().delete(entity);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据主键删除对象
	 * @param id
	 */
	public int delete(String id) {
		log.debug("deleting "+ getTableName() +" where id="+id);
		try {
			String sql = "DELETE FROM "+getTableName()+" WHERE id=:id";
			int deletedEntities = getSession().createNativeQuery(sql).setParameter("id", id).executeUpdate();
			log.debug("delete successful" + deletedEntities);
			return deletedEntities;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	/**
	 * 更新对象
	 * @param entity
	 */
	public void update(T entity) {
		log.debug("updating "+ getTableName() +" instance");
		try {
			getSession().update(entity);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		
	}
	
	/**
	 * 根据主键查询对象
	 * @param id
	 */
	public T findById(String id) {
		return getSession().get(clazz, id);
	}
	
	/**
	 * 查询所有
	 */
	public List<T> findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
		return findAllByCriteria(detachedCriteria);
	}
	
	/**
	 * 分页查询所有
	 */
	public MyPage<T> findPageAll(int pageSize, int page) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
		return findPageByCriteria(detachedCriteria, pageSize, page);
	}
	
}