package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.grits.toolbox.util.structure.glycan.filter.om.FilterSetting;

@XmlRootElement(name="filter")
public class AnnotationFilter {
	
	FilterSetting filterSetting;
	String columnKey;
	Integer numTopHits = -1; // default is "All"
	
	@XmlElement
	public FilterSetting getFilterSetting() {
		return filterSetting;
	}
	public void setFilterSetting(FilterSetting filterSetting) {
		this.filterSetting = filterSetting;
	}
	
	@XmlAttribute
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	
	@XmlAttribute
	public Integer getNumTopHits() {
		return numTopHits;
	}
	public void setNumTopHits(Integer numTopHits) {
		this.numTopHits = numTopHits;
	}
	

}
