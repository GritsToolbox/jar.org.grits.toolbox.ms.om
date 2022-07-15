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
public class StringDoubleMapElements {

    @XmlElement
    public String key;
    @XmlElement
    public Double value;

    @SuppressWarnings("unused")
    private StringDoubleMapElements() {
    } //Required by JAXB

    public StringDoubleMapElements(String key, Double value) {
        this.key = key;
        this.value = value;
    }
}
