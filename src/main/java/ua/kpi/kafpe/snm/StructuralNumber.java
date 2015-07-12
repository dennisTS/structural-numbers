package ua.kpi.kafpe.snm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Queues;

import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

public final class StructuralNumber {

	public static final StructuralNumber NULL = new StructuralNumber.Builder().build();

	private final Set<StructuralNumberColumn> columns;
	
	private StructuralNumber() {
		columns = new HashSet<StructuralNumberColumn>();
	}

	private StructuralNumber(StructuralNumber number) {
		HashSet<StructuralNumberColumn> destinationColumns = new HashSet<StructuralNumberColumn>();
		
		for (final StructuralNumberColumn col : number.columns) {
			destinationColumns.add(new StructuralNumberColumn(col));
		}
		
		this.columns = destinationColumns;
	}
	
	public int getColumnsNumber() {
		return columns.size();
	}
	
	public int getRowsNumber() {
		if (columns.iterator().hasNext())
			return columns.iterator().next().size();
		
		return 0;
	}
	
	public boolean isNull() {
		return columns.isEmpty();
	}
	
	public static class Builder {
		private StructuralNumber number = new StructuralNumber();
		
		public Builder() {
			
		}
		
		public Builder addColumn(Integer...column) {
			if (column.length != 0) {
				StructuralNumberColumn sNColumn = new StructuralNumberColumn(column);
				number.addColumn(sNColumn);
			}

			return this;
		}
		
		public Builder addColumn(Collection<Integer> column) {
			if (column.size() != 0) {
				StructuralNumberColumn sNColumn = new StructuralNumberColumn(column);
				number.addColumn(sNColumn);
			}
			
			return this;
		}
		
		public StructuralNumber build() {
			return number;
		}
	}
	
	public static abstract class StructuralNumberOperation {
		public abstract StructuralNumber perform();
		
		protected StructuralNumber newStructuralNumber() {
			return new StructuralNumber();
		}
		
		protected StructuralNumber copyStructuralNumber(StructuralNumber number) {
			return new StructuralNumber(number);
		}
		
		protected Queue<StructuralNumber> copyStructuralNumbers(StructuralNumber... numbers) {
			Queue<StructuralNumber> queue = Queues.newArrayDeque();
			
			for (StructuralNumber number : numbers)
				queue.add(new StructuralNumber(number));
			
			return queue;
		}
		
		protected boolean addColumnToNumber(StructuralNumberColumn column, StructuralNumber number) {
			return number.addColumn(column);
		}
		
		protected void addAllColumnsToNumber(Set<StructuralNumberColumn> newColumns, StructuralNumber number) {
			number.addAllColumns(newColumns);
		}
		
		protected Set<StructuralNumberColumn> getColumnsCopyFromNumber(StructuralNumber number) {
			Set<StructuralNumberColumn> resultSet = new HashSet<StructuralNumberColumn>();
			
			for (StructuralNumberColumn column : number.columns) {
				resultSet.add(new StructuralNumberColumn(column));
			}
			
			return resultSet;
		}
	}
	
	protected boolean addColumn(StructuralNumberColumn column) {
		checkForSizeConsistency(column);
		
		StructuralNumberColumn columnCopy = new StructuralNumberColumn(column);
		 
		if (!columns.add(columnCopy)) {
			columns.remove(columnCopy);
			return false;
		}
			
		return true;
	}
	
	protected void addAllColumns(Set<StructuralNumberColumn> newColumns) {
		Iterator<StructuralNumberColumn> iterator = newColumns.iterator();
		while (iterator.hasNext()) {
			StructuralNumberColumn column = iterator.next();
			
			addColumn(column);
		}
	}
	
	private void checkForSizeConsistency(StructuralNumberColumn column) {
		if (getRowsNumber() != 0 && getRowsNumber() != column.size())
			throw new SizeInconsistencyException();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (!(obj instanceof StructuralNumber))
			return false;
		
		StructuralNumber structuralNumber = (StructuralNumber) obj;
		
		return this.columns.equals(structuralNumber.columns);
	};
	
	@Override
	public int hashCode() {
		return columns.hashCode();
	}
	
	@Override
	public String toString() {
		return columns.toString();
	}
	
	//TODO toSet()

}
