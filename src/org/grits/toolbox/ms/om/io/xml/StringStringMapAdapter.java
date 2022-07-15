/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grits.toolbox.ms.om.io.xml;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author ubuntu
 */
public class StringStringMapAdapter extends XmlAdapter<StringStringMapElements[], Map<String, String>> {

    public StringStringMapElements[] marshal(Map<String, String> arg0) throws Exception {
        StringStringMapElements[] mapElements = new StringStringMapElements[arg0.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : arg0.entrySet()) {
            mapElements[i++] = new StringStringMapElements(entry.getKey(), entry.getValue());
        }

        return mapElements;
    }

    public Map<String, String> unmarshal(StringStringMapElements[] arg0) throws Exception {
        Map<String, String> r = new HashMap<String, String>();
        for (StringStringMapElements mapelement : arg0) {
            r.put(mapelement.key, mapelement.value);
        }
        return r;
    }
    
    
}
