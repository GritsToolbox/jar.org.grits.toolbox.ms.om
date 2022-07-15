package org.grits.toolbox.ms.om.data;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.grits.toolbox.ms.om.io.xml.MapOfListsAdapter;

@XmlRootElement(name="scansAnnotation")
public class ScansAnnotation {
	private HashMap<Integer,List<Feature>> scanAnnotations = new HashMap<Integer,List<Feature>>();
    private Integer intId = null;
    private String stringId = null;
    
	@XmlJavaTypeAdapter(MapOfListsAdapter.class)
	public HashMap<Integer,List<Feature>> getScanAnnotations() {
		return scanAnnotations;
	}

	public void setScanAnnotations(HashMap<Integer,List<Feature>> scanAnnotations) {
		this.scanAnnotations = scanAnnotations;
	}

	public Integer getAnnotationId() {
		return intId;
	}
	@XmlAttribute(name="annId")
	public void setAnnotationId(Integer intId) {
		this.intId = intId;
	}

	public String getStringAnnotationId() {
		return stringId;
	}
    
	@XmlAttribute(name="stringAnnId")
	public void setStringAnnotationId(String stringId) {
		this.stringId = stringId;
	}

}
