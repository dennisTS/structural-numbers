package ua.kpi.kafpe.snm;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import ua.kpi.kafpe.snm.exception.DuplicateElementsException;

public class StructuralNumberColumn {

	private Set<Integer> innerColumn;

	public StructuralNumberColumn(StructuralNumberColumn column) {
		this.innerColumn = new TreeSet<Integer>(column.innerColumn);
	}
	
	public StructuralNumberColumn(Set<Integer> column) {
		this.innerColumn = new TreeSet<Integer>(column);;
	}
	
	public StructuralNumberColumn(Collection<Integer> column) {
		this.innerColumn = new TreeSet<Integer>(column);
		
		if (this.innerColumn.size() != column.size())
			throw new DuplicateElementsException();
	}

	public StructuralNumberColumn(Integer... column) {
		this.innerColumn = new TreeSet<Integer>(Arrays.asList(column));
		
		if (this.innerColumn.size() != column.length)
			throw new DuplicateElementsException();
	}

	public boolean addElement(Integer element) {
		return innerColumn.add(element);
	}
	
	public boolean addAllElementsFromColumn(StructuralNumberColumn otherColumn) {
		if (this.containsAtLeastOneOf(otherColumn))
			return false;
		else {
			this.innerColumn.addAll(otherColumn.innerColumn);		
			return true;
		}
	}
	
	private boolean containsAtLeastOneOf(StructuralNumberColumn otherColumn) {
		boolean contains = false;
		
		Iterator<Integer> iterator = otherColumn.innerColumn.iterator(); 
		
		while (iterator.hasNext()) {
			if (this.innerColumn.contains(iterator.next()))
				contains = true;
		}
		
		return contains;
	}

	public int size() {
		return innerColumn.size();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (!(obj instanceof StructuralNumberColumn))
			return false;
		
		StructuralNumberColumn structuralNumberColumn = (StructuralNumberColumn) obj;
		
		return this.innerColumn.equals(structuralNumberColumn.innerColumn);
	}

	@Override
	public int hashCode() {
		return this.innerColumn.hashCode();
	}
	
	@Override
	public String toString() {
		return innerColumn.toString();
	}
}
