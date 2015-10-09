/*
 *  GeoServer-Manager - Simple Manager Library for GeoServer
 *  
 *  Copyright (C) 2007,2015 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package it.geosolutions.geoserver.rest.decoder.gwc;

import it.geosolutions.geoserver.rest.decoder.utils.JDOMBuilder;
import org.jdom.Element;

/**
 * Parse <TT>RegexParameterFilter</TT>s returned as XML REST objects.
 *
 * <P>This is the XML Schema representation:
 * <PRE>
 * {@code
    <xs:complexType name="RegexParameterFilter">
      <xs:sequence>
        <xs:element name="key" type="xs:string">
        </xs:element>
        <xs:element maxOccurs="1" minOccurs="0" name="defaultValue" type="xs:string">
        </xs:element>
        <xs:element maxOccurs="1" minOccurs="0" name="normalize" type="gwc:CaseNormalize">
        </xs:element>
        <xs:element name="regex" type="xs:string">
        </xs:element>
      </xs:sequence>
    </xs:complexType>
 * }</PRE>
 * @author Nazzareno Sileno
 */
public class GWCRegexParameterFilter {
    
    private final Element regexParameterFilterElem;

    public static GWCRegexParameterFilter build(String response) {
        if (response == null) {
            return null;
        }

        Element pb = JDOMBuilder.buildElement(response);
        if (pb != null) {
            return new GWCRegexParameterFilter(pb);
        } else {
            return null;
        }
    }

    public GWCRegexParameterFilter(Element regexParameterFilterElem) {
        this.regexParameterFilterElem = regexParameterFilterElem;
    }

    public String getKey() {
        return this.regexParameterFilterElem.getChildText("key");
    }

    public String getDefaultValue() {
        return this.regexParameterFilterElem.getChildText("defaultValue");
    }

    public GWCCaseNormalize getNormalize() {
        GWCCaseNormalize normalize = null;
        final Element normalizeRoot = regexParameterFilterElem.getChild("normalize");
        if(normalizeRoot != null){
            normalize = new GWCCaseNormalize(normalizeRoot);
        }
        return normalize;
    }    
    
    public String getRegex() {
        return this.regexParameterFilterElem.getChildText("regex");
    }
    
}
