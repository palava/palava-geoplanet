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

import java.util.Collections;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import de.cosmocode.json.JSONRenderer;
import de.cosmocode.palava.model.base.ReadOnly;
import de.cosmocode.palava.model.geo.AbstractToponym;
import de.cosmocode.palava.model.geo.ToponymBase;

/**
 * Concrete entity implementation of the {@link ToponymBase} interface.
 * 
 * <p>
 *   This class is part of the Palava Geoplanet package.
 * </p>
 *
 * @author Willi Schoenborn
 */
@Entity
@Table(name = "geoplanet_places")
@ReadOnly
public final class Place extends AbstractToponym {

    @Column(name = "language_code")
    private String languageCode;
    
    @Column(name = "place_type")
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Place parent;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Place> children = Sets.newHashSet();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "geoplanet_neighbors",
        joinColumns = @JoinColumn(name = "place_id"),
        inverseJoinColumns = @JoinColumn(name = "neighbour_id"))
    private Set<Place> neighbors = Sets.newHashSet();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
    private Set<Alias> aliases = Sets.newHashSet();

    /**
     * Pre-persist callback which prevents inserts.
     */
    @PrePersist
    protected void prePersist() {
        throw new UnsupportedOperationException("Place is read-only");
    }

    /**
     * Pre-update callback which prevents updates.
     */
    @PreUpdate
    protected void preUpdate() {
        throw new UnsupportedOperationException("Place is read-only");
    }

    /**
     * Pre-delete callback which prevents deletes.
     */
    @PreRemove
    protected void preRemove() {
        throw new UnsupportedOperationException("Place is read-only");
    }
    
    public String getLanguageCode() {
        return languageCode;
    }
    
    public PlaceType getPlaceType() {
        return placeType;
    }
    
    /**
     * Provide the parent of this place.
     * 
     * @return the parent of this place or null if this place has no parent
     */
    public Place getParent() {
        return parent;
    }
    
    public ImmutableSet<Place> getChildren() {
        return ImmutableSet.copyOf(children);
    }
    
    /**
     * Provide all siblings of this place. Siblings
     * share the same parent and have the same {@linkplain PlaceType place type}.
     * 
     * <p>
     *   This place is not considered a siblings of his own and therefore
     *   not included in the returned set.
     * </p>
     * 
     * @return an immutable set of all siblings
     */
    public ImmutableSet<Place> getSiblings() {
        if (getParent() == null) return ImmutableSet.of();
        return ImmutableSet.copyOf(Iterables.filter(getParent().getChildren(), new Predicate<Place>() {
           
            @Override
            public boolean apply(Place input) {
                if (input.getPlaceType() == Place.this.getPlaceType()) {
                    return Place.this.equals(input);
                } else {
                    return false;
                }
            }
            
        }));
    }

    /**
     * Provide all ancestors of this place.
     * 
     * <p>
     *   This place is not considered an ancestor of his own and therefore
     *   not included in the returned set.
     * </p>
     * 
     * @return an immutable set of all ancestors
     */
    public ImmutableSet<Place> getAncestors() {
        return climbFamilyTree(ImmutableSet.<Place>builder()).build();
    }
    
    private ImmutableSet.Builder<Place> climbFamilyTree(ImmutableSet.Builder<Place> builder) {
        if (getParent() == null) {
            return builder;
        } else {
            builder.add(getParent());
            return getParent().climbFamilyTree(builder);
        }
    }
    
    public ImmutableSet<Place> getNeighbors() {
        return ImmutableSet.copyOf(neighbors);
    }
    
    @Override
    public Set<Alias> getAliases() {
        return Collections.unmodifiableSet(aliases);
    }
    
    /**
     * Retrieve the preferredAlias for a specified locale.
     * 
     * @param locale the target locale
     * @return a preferred alias of this place for the given locale
     * @throws NoSuchElementException if there is no preferred alias for
     *         the given locale
     */
    public Alias getPreferredAlias(final Locale locale) {
        final String language = locale.getLanguage();
        return Iterables.find(getAliases(), new Predicate<Alias>() {
            
            @Override
            public boolean apply(Alias input) {
                if (input.getNameType() == NameType.Q) {
                    return StringUtils.equals(input.getLanguageCode(), language);
                } else {
                    return false;
                }
            }
            
        });
    }

    @Override
    public JSONRenderer renderAsMap(JSONRenderer renderer) {
        return 
            super.renderAsMap(renderer).
            key("languageCode").value(getLanguageCode()).
            key("placeType").value(getPlaceType());
    }

}
