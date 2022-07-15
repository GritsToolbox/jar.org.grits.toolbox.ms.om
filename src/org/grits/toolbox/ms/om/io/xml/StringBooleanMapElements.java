/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grits.toolbox.ms.om.io.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ubuntu
 */
public class StringBooleanMapElements {
  @XmlElement public String  key;
  @XmlElement public Boolean value;

  @SuppressWarnings("unused")
private StringBooleanMapElements() {} //Required by JAXB

  public StringBooleanMapElements(String key, Boolean value)
  {
    this.key   = key;
    this.value = value;
  }
}
