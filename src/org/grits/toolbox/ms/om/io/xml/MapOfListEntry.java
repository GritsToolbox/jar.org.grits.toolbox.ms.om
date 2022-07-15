package org.grits.toolbox.ms.om.io.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.grits.toolbox.ms.om.data.GlycanFeature;

public class MapOfListEntry {
	
public MapOfListEntry(){}
public MapOfListEntry(Integer key,List<GlycanFeature> value){
	this.key = key;
	this.value = value;
}
    @XmlAttribute(name="scanId")
    private Integer key;

    @XmlElement
    private List<GlycanFeature> value;
    
    @XmlTransient
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
    
	@XmlTransient
	public List<GlycanFeature> getValue() {
		return value;
	}

	public void setValue(List<GlycanFeature> value) {
		this.value = value;
	}
}