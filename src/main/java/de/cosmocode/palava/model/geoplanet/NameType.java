/**
 * palava - a java-php-bridge
 * Copyright (C) 2007  CosmoCode GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.cosmocode.palava.model.geoplanet;

/**
 * Indentifies the name type of a place alias.
 * 
 * @author Willi Schoenborn
 * @author <a href="http://developer.yahoo.net/forum/index.php?showtopic=1906">Eddie B</a>
 */
public enum NameType {

    /**
     * Abbreviation
     * 
     * <p>
     *   This name is a abbreviation or code for the place (e.g. "NYC" for New York)
     * </p>
     */
    A,
    
    /**
     * English preferred
     * 
     * <p>
     *   Similar to the Q name type, except that it applies only to the English language.
     *   If you want to display the English name for a place and there is no P name,
     *   use the default place name.
     * </p>
     */
    P,
    
    /**
     * Qualified name
     * 
     * <p>
     *   This name is the preferred name for the place in a language different than
     *   that used by residents of the place (e.g. "紐約" for New York)
     * </p>
     */
    Q,
    
    /**
     * Synonym
     * 
     * <p>
     *   This name is a colloquial name for the place (e.g. "Big Apple" for New York)
     * </p>
     */
    S,
    
    /**
     * Variation
     * 
     * <p>
     *   This name is a well-known (but unofficial) name for the place
     *   (e.g. "New York City" for New York)
     * </p>
     */
    V;
    
}
