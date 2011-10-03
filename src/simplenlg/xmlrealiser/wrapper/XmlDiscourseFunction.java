//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.03 at 08:36:19 PM CEST 
//


package simplenlg.xmlrealiser.wrapper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for discourseFunction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="discourseFunction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AUXILIARY"/>
 *     &lt;enumeration value="COMPLEMENT"/>
 *     &lt;enumeration value="CONJUNCTION"/>
 *     &lt;enumeration value="CUE_PHRASE"/>
 *     &lt;enumeration value="FRONT_MODIFIER"/>
 *     &lt;enumeration value="HEAD"/>
 *     &lt;enumeration value="INDIRECT_OBJECT"/>
 *     &lt;enumeration value="OBJECT"/>
 *     &lt;enumeration value="PRE_MODIFIER"/>
 *     &lt;enumeration value="POST_MODIFIER"/>
 *     &lt;enumeration value="SPECIFIER"/>
 *     &lt;enumeration value="SUBJECT"/>
 *     &lt;enumeration value="VERB_PHRASE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "discourseFunction")
@XmlEnum
public enum XmlDiscourseFunction {

    AUXILIARY,
    COMPLEMENT,
    CONJUNCTION,
    CUE_PHRASE,
    FRONT_MODIFIER,
    HEAD,
    INDIRECT_OBJECT,
    OBJECT,
    PRE_MODIFIER,
    POST_MODIFIER,
    SPECIFIER,
    SUBJECT,
    VERB_PHRASE;

    public String value() {
        return name();
    }

    public static XmlDiscourseFunction fromValue(String v) {
        return valueOf(v);
    }

}
