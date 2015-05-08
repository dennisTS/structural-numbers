package ua.kpi.kafpe.snm;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ua.kpi.kafpe.snm.exception.SizeInconsistencyException;

public final class StructuralNumber {

	public static final StructuralNumber NULL = new StructuralNumber.Builder().build();

	private static final String NULL_ARGUMENT_MESSAGE = "Argument is null";
	
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
	
	public StructuralNumber add(StructuralNumber addend) {
		if (addend == null)
			throw new IllegalArgumentException(NULL_ARGUMENT_MESSAGE);
		
		if (addend.isNull())
			return this;
		else if (this.isNull())
			return addend;
		
		if (this.equals(addend))
			return StructuralNumber.NULL;
		
		if (this.getRowsNumber() != addend.getRowsNumber())
			throw new SizeInconsistencyException();
		
		StructuralNumber result = new StructuralNumber(this);
		
		result.addAllColumns(addend.columns);
		
		return result;
	}
	
	public StructuralNumber multiply(StructuralNumber factor) {
		if (factor == null)
			throw new IllegalArgumentException();
		
		if (factor.isNull())
			return this;
		else if (this.isNull())
			return factor;
		
		if (this.equals(factor))
			return StructuralNumber.NULL;
		
		return new Multiplication(this, factor).perform();
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
		
		protected boolean addColumnToNumber(StructuralNumberColumn column, StructuralNumber number) {
			return number.addColumn(column);
		}
		
		protected void addAllColumnsToNumber(Set<StructuralNumberColumn> newColumns, StructuralNumber number) {
			number.addAllColumns(newColumns);
		}
		
		protected Set<StructuralNumberColumn> getColumnsFromNumber(StructuralNumber number) {
			return number.columns;
		}
	}
	
	protected boolean addColumn(StructuralNumberColumn column) {
		checkForSizeConsistency(column);
		 
		if (!columns.add(column)) {
			columns.remove(column);
			return false;
		}
			
		return true;
	}
	
	protected void addAllColumns(Set<StructuralNumberColumn> newColumns) {
		Iterator<StructuralNumberColumn> iterator = newColumns.iterator();
		while (iterator.hasNext()) {
			StructuralNumberColumn column = iterator.next();
			
			checkForSizeConsistency(column);
			
			if (!columns.add(column)) {
				columns.remove(column);
			}
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

}
