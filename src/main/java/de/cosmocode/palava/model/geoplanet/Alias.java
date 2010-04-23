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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
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
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn
    private Place place;

    /**
     * Pre-persist callback which prevents inserts.
     */
    @PrePersist
    protected void prePersist() {
        throw new UnsupportedOperationException("Alias is read-only");
    }

    /**
     * Pre-update callback which prevents updates.
     */
    @PreUpdate
    protected void preUpdate() {
        throw new UnsupportedOperationException("Alias is read-only");
    }

    /**
     * Pre-delete callback which prevents deletes.
     */
    @PreRemove
    protected void preRemove() {
        throw new UnsupportedOperationException("Alias is read-only");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLanguageCode() {
        return languageCode == null ? null : languageCode.toLowerCase();
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
