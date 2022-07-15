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
public class StringStringMapElements {
    @XmlElement
    public String key;
    @XmlElement
    public String value;

    @SuppressWarnings("unused")
    private StringStringMapElements() {
    } //Required by JAXB

    public StringStringMapElements(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
