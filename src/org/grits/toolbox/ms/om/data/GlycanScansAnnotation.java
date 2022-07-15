package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="glycanAnnotations")
public class GlycanScansAnnotation extends ScansAnnotation {    
  
    @Override
    public boolean equals(Object obj) {
    	if( ! (obj instanceof GlycanScansAnnotation ) ) {
    		return false;
    	}
    	GlycanScansAnnotation otherObj = (GlycanScansAnnotation) obj;
    	return getAnnotationId().equals(otherObj.getAnnotationId()) && getGlycanId().equals(otherObj.getGlycanId());
    }
    
    @Deprecated
	public String getGlycanId() {
		return super.getStringAnnotationId();
	}
    @Deprecated
	@XmlAttribute(name="glycanId")
	public void setGlycanId(String glycanId) {
		super.setStringAnnotationId(glycanId);
	}
}
