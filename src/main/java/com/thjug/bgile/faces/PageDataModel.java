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
package com.thjug.bgile.faces;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.model.DataModel;

/**
*
* @author Wasan Anusornhirunkarn, @tone
*/
public abstract class PageDataModel<T> extends DataModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_PAGE_SIZE = 10;
	private static final int MAX_PAGE_LIST = 10;

	private Integer rowIndex = -1;
	private Integer rowCount;
	private Integer currentPage = 1;
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	private Integer maxPageLink = MAX_PAGE_LIST;
	private Integer beginPageLink;
	private Integer endPageLink;
	private String search;
	private List<T> data;

	@Override
	public boolean isRowAvailable() {
		if (data == null) {
			return false;
		}
		return rowIndex >= 0 && rowIndex < data.size();
	}

	@Override
	public T getRowData() {
		return data.get(rowIndex);
	}

	@Override
	public int getRowIndex() {
		return rowIndex;
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(final int rowCount) {
		this.rowCount = rowCount;
	}

	@Override
	public void setRowIndex(final int rowIndex) {
		this.rowIndex = rowIndex;
	}

	@Override
	public Object getWrappedData() {
		return data;
	}

	@Override
	public void setWrappedData(final Object data) {
		this.data = (List<T>) data;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(final Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(final Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageTotal() {
		return (int) Math.ceil(rowCount.doubleValue() / pageSize.doubleValue());
	}

	public Integer getMaxPageLink() {
		return maxPageLink;
	}

	public void setMaxPageLink(final Integer maxPageLink) {
		this.maxPageLink = maxPageLink;
	}

	public List<Integer> getPageLinks() {
		final List<Integer> pageLinks = new LinkedList<>();
		for (int i = beginPageLink; i <= endPageLink; i++) {
			pageLinks.add(i);
		}
		return pageLinks;
	}

	public Boolean getIsFirstPage() {
		return getCurrentPage() == 1;
	}

	public Boolean isCurrentPage(final Integer page) {
		return getCurrentPage() == page;
	}

	public Boolean getIsLastPage() {
		return getCurrentPage() == getPageTotal();
	}

	private void load() {
		final Integer first = pageSize * (currentPage - 1);
		final List<T> localdata = this.loadData(first, pageSize, search);
		this.setWrappedData(localdata);
	}

	public void load(final Integer page) {
		this.load(page, search);
	}

	public void load(final Integer page, final String search) {
		currentPage = page;
		beginPageLink = 1;
		endPageLink = beginPageLink + maxPageLink - 1;
		if (currentPage > endPageLink) {
			endPageLink = currentPage;
			beginPageLink = endPageLink - maxPageLink + 1;
		} else if (currentPage < beginPageLink) {
			beginPageLink = currentPage;
			endPageLink = beginPageLink + maxPageLink - 1;
		}
		this.search = search;
		load();
		if (endPageLink > getPageTotal()) {
			endPageLink = getPageTotal();
		}
	}

	public void loadNext() {
		final Integer nextPage = currentPage + 1;
		if (nextPage <= getPageTotal()) {
			currentPage = nextPage;
		}
		load(currentPage, this.search);
	}

	public void loadPrevious() {
		final Integer previousPage = currentPage - 1;
		if (previousPage > 0) {
			currentPage = previousPage;
		}
		load(currentPage, this.search);
	}

	protected abstract List<T> loadData(final int first, final int pageSize, final String query);

}
