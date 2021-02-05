/*
 * Copyright (C) 2021 Alonso del Arte
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package arithmetic;

/**
 *
 * @param <T>
 * @author Alonso del Arte
 */
public interface Arithmeticable<T extends Arithmeticable<T>> {

    T plus(T addend);

    T plus(int addend);

    T negate();

    default T minus(T subtrahend) {
        return this.plus(subtrahend.negate());
    }

    default T minus(int subtrahend) {
        return this.plus(-subtrahend);
    }

    T times(T multiplicand);

    T times(int multiplicand);

    T divides(T divisor) throws NotDivisibleException;

    T divides(int divisor) throws NotDivisibleException;

}
