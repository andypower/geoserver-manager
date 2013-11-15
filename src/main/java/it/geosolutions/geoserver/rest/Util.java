/*
 *  GeoServer-Manager - Simple Manager Library for GeoServer
 *
 *  Copyright (C) 2007,2013 GeoSolutions S.A.S.
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
package it.geosolutions.geoserver.rest;

import it.geosolutions.geoserver.rest.decoder.RESTStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ETj (etj at geo-solutions.it)
 */
public class Util {

    /**
     * Search for a stylename in global and in all workspaces.
     */
    public static List<RESTStyle> searchStyles(GeoServerRESTReader reader, String stylename) {

        List<RESTStyle> styles = new ArrayList<RESTStyle>();

        RESTStyle style = reader.getStyle(stylename);
        if (style != null) {
            styles.add(style);
        }

        for (String workspace : reader.getWorkspaceNames()) {
            style = reader.getStyle(workspace, stylename);
            if (style != null) {
                styles.add(style);
            }
        }

        return styles;
    }

    public static <T> List<T> safeList(List<T> list) {
        return list == null ? Collections.EMPTY_LIST : list;
    }

    public static <T> Collection<T> safeCollection(Collection<T> collection) {
        return collection == null ? Collections.EMPTY_SET : collection;
    }

    public static <TK, TV> Map<TK, TV> safeMap(Map<TK, TV> map) {
        return map == null ? Collections.EMPTY_MAP : map;
    }

    public static char getParameterSeparator(String url) {
        char parameterSeparator = '?';
        if (url.contains("?")) {
            parameterSeparator = '&';
        }
        return parameterSeparator;
    }

    public static char getParameterSeparator(StringBuilder url) {
        char parameterSeparator = '?';
        if (url.indexOf("?") != -1) {
            parameterSeparator = '&';
        }
        return parameterSeparator;
    }

    public static boolean appendParameter(StringBuilder url, String parameterName,
            String parameterValue) {
        boolean result = false;
        if (parameterName != null && !parameterName.isEmpty()
                && parameterValue != null && !parameterValue.isEmpty()) {
            char parameterSeparator = getParameterSeparator(url);
            url.append(parameterSeparator).append(parameterName.trim())
                    .append('=').append(parameterValue.trim());
        }
        return result;
    }

    public static String commaSeparatedRolesBuilder(Set<String> roles) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> iterator = roles.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if (iterator.hasNext()) {
                stringBuilder.append("%2c");//%2c == ,
            }
        }
        return stringBuilder.toString();
    }
}
