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
 * This exception indicates that a given number represented by a class that
 * implements <code>Arithmeticable&lt;T&gt;</code> is not divisible by another
 * number represented by an instance of that same class. This exception should
 * not be used for division by zero because that would be imply that complex
 * infinity can be usefully rounded to an actual number.
 * @author Alonso del Arte
 */
public class NotDivisibleException extends Exception {

    private final Arithmeticable badDividend;

    private final Arithmeticable badDivisor;

    public Arithmeticable getDividend() {
        return this.badDividend;
    }

    public Arithmeticable getDivisor() {
        return this.badDivisor;
    }

    public NotDivisibleException(String msg, Arithmeticable dividend,
                                 Arithmeticable divisor) {
        super(msg);
        this.badDividend = dividend;
        this.badDivisor = divisor;
    }

}
