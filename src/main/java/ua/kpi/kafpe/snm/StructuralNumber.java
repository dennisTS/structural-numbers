package ua.kpi.kafpe.snm;

import java.util.*;

import com.google.common.collect.Queues;

import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

public final class StructuralNumber {

	public static final StructuralNumber NULL = new StructuralNumber.Builder().build();

	private final Set<StructuralNumberColumn> columns;
	
	private StructuralNumber() {
		columns = new HashSet<>();
	}

	private StructuralNumber(StructuralNumber number) {
		HashSet<StructuralNumberColumn> destinationColumns = new HashSet<>();
		
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
		public StructuralNumber perform() {
			throw new UnsupportedOperationException();
		}
		
		protected StructuralNumber newStructuralNumber() {
			return new StructuralNumber();
		}
		
		protected StructuralNumber copyStructuralNumber(StructuralNumber number) {
			if (number == NULL)
				return NULL;

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
			Set<StructuralNumberColumn> resultSet = new HashSet<>();
			
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
		for (StructuralNumberColumn column : newColumns)
			addColumn(column);
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
	}
	
	@Override
	public int hashCode() {
		return columns.hashCode();
	}
	
	public String toReadableString() {
		List<List<Integer>> list = new ArrayList<>();
		for (StructuralNumberColumn column : columns) {
			list.add(new ArrayList<>(column.getInnerColumnCopy()));
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < getRowsNumber(); i++) {
			for (List<Integer> column : list) {
				result.append(column.get(i));
				result.append(" ");
			}

			result.append("\n");
		}

		return result.toString();
	}

	@Override
	public String toString() {
		return columns.toString();
	}
	
	//TODO toSet()

}
