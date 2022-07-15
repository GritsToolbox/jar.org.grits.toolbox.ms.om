package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.grits.toolbox.util.structure.glycan.filter.GlycanFilterException;
import org.grits.toolbox.util.structure.glycan.filter.om.Filter;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterVisitor;
import org.grits.toolbox.util.structure.glycan.filter.om.IntegerFilter;

@XmlRootElement(name="intensityFilter")
public class IntensityFilter extends IntegerFilter {
	
	String intensityColumnKey;

	@Override
	public boolean accept(GlycanFilterVisitor a_operator) throws GlycanFilterException {
		if (a_operator instanceof MSFilterVisitor)
			return ((MSFilterVisitor)a_operator).visit(this);
		return false;
	}

	@Override
	public Filter copy() {
		IntensityFilter newFilter = new IntensityFilter();
		newFilter.setIntensityColumnKey(getIntensityColumnKey());
		newFilter.setMin(getMin());
		newFilter.setMax(getMax());
		newFilter.setDescription(getDescription());
		newFilter.setLabel(getLabel());
		newFilter.setName(getName());
		newFilter.setClassification(getClassification());
		
		return newFilter;
	}

	@XmlAttribute
	public String getIntensityColumnKey() {
		return intensityColumnKey;
	}

	public void setIntensityColumnKey(String intensityColumnKey) {
		this.intensityColumnKey = intensityColumnKey;
	}

}
