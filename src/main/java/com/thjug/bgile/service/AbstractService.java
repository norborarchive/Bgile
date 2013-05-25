/*
 * Attribution
 * CC BY
 * This license lets others distribute, remix, tweak,
 * and build upon your work, even commercially,
 * as long as they credit you for the original creation.
 * This is the most accommodating of licenses offered.
 * Recommended for maximum dissemination and use of licensed materials.
 *
 * http://creativecommons.org/licenses/by/3.0/
 * http://creativecommons.org/licenses/by/3.0/legalcode
 */
package com.thjug.bgile.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Calendar;
import java.util.Date;
import com.thjug.bgile.entity.Timeable;

/**
 *
 * @author @nuboat
 */
public abstract class AbstractService<T> {

	protected static final char TRUE = 'T';
	protected static final char FALSE = 'F';
	protected static final char LIVE = 'L';
	protected static final char DEAD = 'D';
	private transient final Class<T> entityClass;

	@Inject
	private transient Provider<EntityManager> provider;

	public AbstractService(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T create(final T entity) {
		final Timeable timeable = (Timeable) entity;
		timeable.setCreated(getNow());
		getEntityManager().persist(timeable);
		return entity;
	}

	public T edit(final T entity) {
		final Timeable timeable = (Timeable) entity;
		timeable.setUpdated(getNow());
		return getEntityManager().merge(entity);
	}

	public void remove(final T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T find(final Object id) {
		return getEntityManager().getReference(entityClass, id);
	}

	protected EntityManager getEntityManager() {
		return this.provider.get();
	}

	@SuppressWarnings("unchecked")
	protected <S> S findOne(final String namequery, final Object... param) {
		final Query q = getEntityManager().createNamedQuery(namequery);
		for (int i = 0; i < param.length; i++) {
			q.setParameter(i + 1, param[i]);
		}
		return (S) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findAll(final String namequery, final Object... param) {
		final Query q = getEntityManager().createNamedQuery(namequery);
		for (int i = 0; i < param.length; i++) {
			q.setParameter(i + 1, param[i]);
		}
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findRange(final String namequery, final int offset, final int limit, final Object... param) {
		final Query q = getEntityManager().createNamedQuery(namequery);
		for (int i = 0; i < param.length; i++) {
			q.setParameter(i + 1, param[i]);
		}
		if (limit != 0) {
			q.setMaxResults(limit);
		}
		if (offset != 0) {
			q.setFirstResult(offset);
		}
		return q.getResultList();
	}

	public void clearCache() {
		getEntityManager().getEntityManagerFactory().getCache().evict(entityClass);
	}

	public void clearCache(final Object id) {
		getEntityManager().getEntityManagerFactory().getCache().evict(entityClass, id);
	}

	public void clearAllCache() {
		getEntityManager().getEntityManagerFactory().getCache().evictAll();
	}

	private static final Calendar calendar = Calendar.getInstance();

	private Date getNow() {
		return calendar.getTime();
	}

}
