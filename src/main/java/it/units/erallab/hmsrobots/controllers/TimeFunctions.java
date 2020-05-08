/*
 * Copyright (C) 2020 Eric Medvet <eric.medvet@gmail.com> (as eric)
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.units.erallab.hmsrobots.controllers;

import it.units.erallab.hmsrobots.objects.immutable.ControllableVoxel;
import it.units.erallab.hmsrobots.util.Grid;
import it.units.erallab.hmsrobots.util.SerializableFunction;

/**
 * @author Eric Medvet <eric.medvet@gmail.com>
 */
public class TimeFunctions implements Controller<ControllableVoxel> {

  private final Grid<SerializableFunction<Double, Double>> functions;

  public TimeFunctions(Grid<SerializableFunction<Double, Double>> functions) {
    this.functions = functions;
  }

  @Override
  public void control(double t, Grid<ControllableVoxel> voxels) {
    for (Grid.Entry<ControllableVoxel> entry : voxels) {
      SerializableFunction<Double, Double> function = functions.get(entry.getX(), entry.getY());
      if ((entry.getValue() != null) && (function != null)) {
        entry.getValue().applyForce(function.apply(t));
      }
    }
  }

  public Grid<SerializableFunction<Double, Double>> getFunctions() {
    return functions;
  }

}
