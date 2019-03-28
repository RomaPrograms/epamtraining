//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2019.03.28 at 10:10:01 PM MSK
//


package by.training.webparsing.devices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InnerDevice complex type.
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.
 *
 * <pre>
 * &lt;complexType name="InnerDevice">
 *   &lt;complexContent>
 *     &lt;extension
 *     base="{http://www.webparsing.training.by/devices}DeviceType">
 *       &lt;sequence>
 *         &lt;element name="version"
 *         type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InnerDevice", propOrder = {
    "version"
})
public class InnerDevice extends DeviceType {

    /**
     * version of device.
     */
    @XmlElement(required = true, defaultValue = "version-1.0")
    private String version;

    /**
     * Gets the value of the version property.
     *
     * @return
     *     possible object is
     *     {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     */
    public void setVersion(final String value) {
        this.version = value;
    }

}
