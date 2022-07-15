/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grits.toolbox.ms.om.io.xml;

/**
 *
 * @author ubuntu
 */
import javax.xml.bind.annotation.XmlElement;

import org.grits.toolbox.ms.om.data.Scan;

class MapElements
{
  @XmlElement public Integer  key;
  @XmlElement public Scan value;

  @SuppressWarnings("unused")
private MapElements() {} //Required by JAXB

  public MapElements(Integer key, Scan value)
  {
    this.key   = key;
    this.value = value;
  }
}