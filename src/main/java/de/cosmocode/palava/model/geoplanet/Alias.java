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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.cosmocode.json.JSONRenderer;
import de.cosmocode.palava.model.base.ReadOnly;
import de.cosmocode.palava.model.geo.AliasBase;

/**
 * Concrete entity implementation of the {@link AliasBase} interface.
 * 
 * <p>
 *   This class is part of the Palava Geoplanet package.
 * </p>
 *
 * @author Willi Schoenborn
 */
@Entity
@Table(name = "geoplanet_aliases")
@ReadOnly
public final class Alias implements AliasBase {
    
    private String name;
    
    @Column(name = "language_code")
    private String languageCode;
    
    @Column(name = "name_type")
    @Enumerated(EnumType.STRING)
    private NameType nameType;
    
    @JoinColumn
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Place place;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLanguageCode() {
        return languageCode;
    }
    
    public NameType getNameType() {
        return nameType;
    }

    @Override
    public Place getToponym() {
        return place;
    }
    
    @Override
    public JSONRenderer renderAsMap(JSONRenderer renderer) {
        return renderer.
            key("name").value(getName()).
            key("languageCode").value(getLanguageCode()).
            key("nameType").value(getNameType());
    }

}
