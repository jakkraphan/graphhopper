/*
 *  Copyright 2012 Peter Karich info@jetsli.de
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package de.jetsli.quadtreecomparison;

import de.jetsli.graph.geohash.SpatialKeyTree;

/**
 * Spatial Information System implementation
 *
 * @author Peter Karich
 */
public class GHSpatialTree implements SimplisticQuadTree {

    SpatialKeyTree qt;
    int skipLeftBits;
    int entriesPerBucket;

    public GHSpatialTree() {
    }

    public GHSpatialTree(int skipLeftBits, int epb) {
        this.skipLeftBits = skipLeftBits;
        entriesPerBucket = epb;
    }

    public void init(int size) {
        try {
            qt = new SpatialKeyTree(skipLeftBits) {

                @Override protected int getEntriesPerBucket() {
                    return entriesPerBucket;
                }
            }.init(size);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void put(double lat, double lon) {
        qt.add(lat, lon, 0);
    }

    public int countNodes(double lat, double lon, double radiusInKm) {
        return qt.getNodes(lat, lon, radiusInKm).size();
    }

    @Override
    public String toString() {
        // return "GHSpatial";
        return qt.toDetailString();
    }

    public int size() {
        return qt.size();
    }

    public long getEmptyEntries(boolean b) {
        return qt.getEmptyEntries(b);
    }
}