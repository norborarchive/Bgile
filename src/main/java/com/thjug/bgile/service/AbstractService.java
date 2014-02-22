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
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.inject.Inject;
import com.google.inject.Provider;
import com.thjug.bgile.entity.Timeable;
import javax.persistence.NoResultException;

/**
 * 
 * @author @nuboat
 */
public abstract class AbstractService<T> {

	private final transient Class<T> entityClass;

	@Inject
	private transient Provider<EntityManager> provider;

	public AbstractService(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T find(final Object id) {
		try {
			return getEntityManager().getReference(entityClass, id);
		} catch (final NoResultException e) {
			return null;
		}
	}

	public T create(final T entity) {
		if (entity instanceof Timeable) {
			final Timeable timeable = (Timeable) entity;
			timeable.setCreated(Calendar.getInstance().getTime());
		}
		getEntityManager().persist(entity);
		return entity;
	}

	public T update(final T entity) {
		if (entity instanceof Timeable) {
			final Timeable timeable = (Timeable) entity;
			timeable.setUpdated(Calendar.getInstance().getTime());
		}
		return getEntityManager().merge(entity);
	}

	public void remove(final T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	protected EntityManager getEntityManager() {
		return this.provider.get();
	}

	@SuppressWarnings("unchecked")
	protected T findOne(final String namequery, final Object... param) {
		try {
			final Query q = getEntityManager().createNamedQuery(namequery);
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i + 1, param[i]);
			}
			return (T) q.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected List<T> findAll(final String namequery, final Object... param) {
		try {
			final Query q = getEntityManager().createNamedQuery(namequery);
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i + 1, param[i]);
			}
			return q.getResultList();
		} catch (final NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected List<T> findRange(final String namequery, final int offset, final int limit, final Object... param) {
		try {
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
		} catch (final NoResultException e) {
			return null;
		}
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

}
