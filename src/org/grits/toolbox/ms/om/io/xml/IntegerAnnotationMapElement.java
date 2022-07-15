package org.grits.toolbox.ms.om.io.xml;

import javax.xml.bind.annotation.XmlElement;

import org.grits.toolbox.ms.om.data.Annotation;
import org.grits.toolbox.ms.om.data.Scan;

public class IntegerAnnotationMapElement {
	  @XmlElement public Integer  annId;
	  @XmlElement public Annotation annotation;

	  @SuppressWarnings("unused")
	private IntegerAnnotationMapElement() {} //Required by JAXB

	  public IntegerAnnotationMapElement(Integer key, Annotation value)
	  {
	    this.annId   = key;
	    this.annotation = value;
	  }
}
