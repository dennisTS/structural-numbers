package ua.kpi.kafpe.snm;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import ua.kpi.kafpe.snm.exception.DuplicateElementsException;

public class StructuralNumberColumn {

	private final Set<Integer> innerColumn;

	public StructuralNumberColumn(StructuralNumberColumn column) {
		this.innerColumn = new TreeSet<>(column.innerColumn);
	}
	
	public StructuralNumberColumn(Set<Integer> column) {
		this.innerColumn = new TreeSet<>(column);
	}
	
	public StructuralNumberColumn(Collection<Integer> column) {
		this.innerColumn = new TreeSet<>(column);
		
		if (this.innerColumn.size() != column.size())
			throw new DuplicateElementsException();
	}

	public StructuralNumberColumn(Integer... column) {
		this.innerColumn = new TreeSet<>(Arrays.asList(column));
		
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
	
	public void removeElement(Integer element) {
		this.innerColumn.remove(element);
	}
	
	private boolean containsAtLeastOneOf(StructuralNumberColumn otherColumn) {
		boolean contains = false;

		for (Integer anInnerColumn : otherColumn.innerColumn) {
			if (this.innerColumn.contains(anInnerColumn))
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

	public Set<Integer> getInnerColumnCopy() {
		return new HashSet<>(this.innerColumn);
	}
}
