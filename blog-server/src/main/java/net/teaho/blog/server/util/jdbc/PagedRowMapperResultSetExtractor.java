/*
 * @company sinobest 
 * @date    2008-1-23
 * @author  luxunheng
 */
package net.teaho.blog.server.util.jdbc;

import net.teaho.blog.server.util.jdbc.support.Page;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagedRowMapperResultSetExtractor implements ResultSetExtractor {
	private final RowMapper rowMapper;
	private final int rowsExpected;
	private long startIndex;
	private int pageNum;
	private int pageSize;
	private long total;

	public long getTotal() {
		return total;
	}

	/**
	 * Create a new RowMapperResultSetExtractor.
	 * 
	 * @param rowMapper
	 *            the RowMapper which creates an object for each row
	 */
	public PagedRowMapperResultSetExtractor(RowMapper rowMapper, int pageNum,
			int pageSize) {
		this(rowMapper, 0, pageNum, pageSize);

	}

	/**
	 * Create a new RowMapperResultSetExtractor.
	 * 
	 * @param rowMapper
	 *            the RowMapper which creates an object for each row
	 * @param rowsExpected
	 *            the number of expected rows (just used for optimized
	 *            collection handling)
	 */
	public PagedRowMapperResultSetExtractor(RowMapper rowMapper,
			int rowsExpected, int pageNum, int pageSize) {
		this(rowMapper, rowsExpected, (long) Page.getStartOfPage(pageNum,
                pageSize), pageSize);
	}

	public PagedRowMapperResultSetExtractor(RowMapper rowMapper,
			long startIndex, int pageSize) {
		this(rowMapper, 0, startIndex, pageSize);

	}

	public PagedRowMapperResultSetExtractor(RowMapper rowMapper,
			int rowsExpected, long startIndex, int pageSize) {
		Assert.notNull(rowMapper, "RowMapper is required");
		this.rowMapper = rowMapper;
		this.rowsExpected = rowsExpected;
		this.startIndex = startIndex;
		this.pageSize = pageSize;

	}

	public Object extractData(ResultSet rs) throws SQLException {
		List results = (this.rowsExpected > 0 ? new ArrayList(this.rowsExpected)
				: new ArrayList());

		rs.last(); // TODO use count sql for performance need
		this.total = rs.getRow();

		if (total > 0) {
			rs.absolute((int) this.startIndex + 1);
		} else {
			return results;
		}

		rs.setFetchSize(pageSize);
		int rowNum = 0;
		for (int rowIndex = 0; rowIndex < pageSize; rowIndex++) {
			if ((rs.getRow() > total) || (rs.getRow() < 1)) {
				break;
			}
			results.add(this.rowMapper.mapRow(rs, rowNum++));
			boolean hasNext = rs.next();
			if (!hasNext) {
				break;
			}

		}
		return results;
	}

}
