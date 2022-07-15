package org.grits.toolbox.ms.om.io.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.grits.toolbox.ms.om.data.Feature;
import org.grits.toolbox.ms.om.data.GlycanFeature;
import org.grits.toolbox.ms.om.data.GlycolipidFeature;
import org.grits.toolbox.ms.om.data.LipidFeature;

public class FeatureAdapter extends
XmlAdapter<FeatureAdapter.AdaptedFeature, Feature> {

@Override
public AdaptedFeature marshal(Feature feature)
    throws Exception {
    if (null == feature) {
        return null;
    }
    GlycanFeature gFeature = new GlycanFeature();
    LipidFeature lFeature = new LipidFeature();
    GlycolipidFeature glFeature = new GlycolipidFeature();
    AdaptedFeature adFeature = new AdaptedFeature();
    if (feature instanceof GlycanFeature) {
    	gFeature = (GlycanFeature) feature;
    	adFeature.glycanFeature = gFeature;
    }
    if ( feature instanceof LipidFeature ) {
    	lFeature = (LipidFeature) feature;
    	adFeature.lipidFeature = lFeature;
    }
    if ( feature instanceof GlycolipidFeature ) {
    	glFeature = (GlycolipidFeature) feature;
    	adFeature.glycolipidFeature = glFeature;
    }
    return adFeature;
}

@Override
public Feature unmarshal(AdaptedFeature adFeature)
    throws Exception {
    if (null == adFeature) {
        return null;
    }
    if (null != adFeature.glycanFeature) {
    	GlycanFeature gFeature = new GlycanFeature();
    	gFeature = adFeature.glycanFeature;
        return gFeature;
    } 
    if (null != adFeature.lipidFeature) {
    	LipidFeature lFeature = new LipidFeature();
    	lFeature = adFeature.lipidFeature;
        return lFeature;
    } 
    if (null != adFeature.glycolipidFeature) {
    	GlycolipidFeature glFeature = new GlycolipidFeature();
    	glFeature = adFeature.glycolipidFeature;
        return glFeature;
    } 
    return null;
}

public static class AdaptedFeature {

    @XmlElement
    public GlycanFeature glycanFeature;

    @XmlElement
    public GlycolipidFeature glycolipidFeature;

    @XmlElement
    public LipidFeature lipidFeature;
}


}
