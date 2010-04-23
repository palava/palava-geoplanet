/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
