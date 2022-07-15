/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grits.toolbox.ms.om.io.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.grits.toolbox.ms.om.data.Annotation;
import org.grits.toolbox.ms.om.data.GlycanAnnotation;
import org.grits.toolbox.ms.om.data.GlycolipidAnnotation;
import org.grits.toolbox.ms.om.data.PeptideAnnotation;

/**
 *
 * @author ubuntu
 */
public class AnnotationAdapter extends
    XmlAdapter<AnnotationAdapter.AdaptedAnnotation, Annotation> {

    @Override
    public AdaptedAnnotation marshal(Annotation annotation)
        throws Exception {
        if (null == annotation) {
            return null;
        }
        GlycanAnnotation gAnn = new GlycanAnnotation();
        PeptideAnnotation pAnn = new PeptideAnnotation();
        GlycolipidAnnotation glAnn = new GlycolipidAnnotation();
        AdaptedAnnotation adAnn = new AdaptedAnnotation();
        if (annotation instanceof GlycanAnnotation) {
            gAnn = (GlycanAnnotation) annotation;
            adAnn.glycanAnn = gAnn;
        }
        if ( annotation instanceof PeptideAnnotation ) {
            pAnn = (PeptideAnnotation)annotation;
            adAnn.pepAnn = pAnn;
        }
        if ( annotation instanceof GlycolipidAnnotation ) {
        	glAnn = (GlycolipidAnnotation)annotation;
        	adAnn.glycolipAnn = glAnn;
        }
        return adAnn;
    }

    @Override
    public Annotation unmarshal(AdaptedAnnotation adAnn)
        throws Exception {
        if (null == adAnn) {
            return null;
        }
        if (null != adAnn.glycanAnn) {
            GlycanAnnotation gAnn = new GlycanAnnotation();
            gAnn = adAnn.glycanAnn;
            return gAnn;
        }
        if ( null != adAnn.pepAnn ) {
            PeptideAnnotation pAnn = new PeptideAnnotation();
            pAnn = adAnn.pepAnn;
            return pAnn;
        }
        if ( null != adAnn.glycolipAnn ) {
        	GlycolipidAnnotation glAnn = new GlycolipidAnnotation();
        	glAnn = adAnn.glycolipAnn;
        	return glAnn;
        }
        return null;
    }

    public static class AdaptedAnnotation {

        @XmlElement
        public GlycanAnnotation glycanAnn;

        @XmlElement
        public PeptideAnnotation pepAnn;

        @XmlElement
        public GlycolipidAnnotation glycolipAnn;
    }

}

