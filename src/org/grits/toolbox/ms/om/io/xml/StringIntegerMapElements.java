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
public class StringIntegerMapElements {
    @XmlElement
    public String key;
    @XmlElement
    public Integer value;

    @SuppressWarnings("unused")
    private StringIntegerMapElements() {
    } //Required by JAXB

    public StringIntegerMapElements(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
