/*
 * (C) Copyright 2016 Richard Ballard.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.richardballard.arbeetestutils.test;

import com.github.richardballard.arbeecoretypes.function.ToBooleanFunction;
import com.google.common.collect.ImmutableList;
import net.jcip.annotations.ThreadSafe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockito.AdditionalAnswers;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ThreadSafe
public enum MockUtils {
    ;

    /**
     * Returns a mock function that always returns {@code value}.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <K, V> Function<K, V> mockFunctionSingleAnswer(@NotNull final Class<? extends K> keyClass,
                                                                 @Nullable final V value) {
        assert keyClass != null;

        final Function<K, V> function = mock(Function.class);

        when(function.apply(any(keyClass)))
                .thenReturn(value);

        return function;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <V> Supplier<V> mockSupplierSingleAnswer(@Nullable final V value) {
        final Supplier<V> supplier = mock(Supplier.class);

        when(supplier.get())
                .thenReturn(value);

        return supplier;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <V> Supplier<V> mockSupplierMultipleAnswers(@NotNull final  ImmutableList<V> values) {
        assert values != null;

        final Supplier<V> supplier = mock(Supplier.class);

        when(supplier.get())
                .thenAnswer(AdditionalAnswers.returnsElementsOf(values));

        return supplier;
    }

    @NotNull
    public static BooleanSupplier mockBooleanSupplierSingleAnswer(final boolean value) {
        final BooleanSupplier supplier = mock(BooleanSupplier.class);

        when(supplier.getAsBoolean())
                .thenReturn(value);

        return supplier;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> ToBooleanFunction<T> mockToBooleanFunctionSingleAnswer(@NotNull final Class<T> applyClass,
                                                                             final boolean value) {
        final ToBooleanFunction<T> function = mock(ToBooleanFunction.class);

        when(function.applyAsBoolean(any(applyClass)))
                .thenReturn(value);

        return function;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> ToBooleanFunction<T> mockToBooleanFunctionMultipleAnswers(@NotNull final Class<T> applyClass,
                                                                                @NotNull final ImmutableList<Boolean> values) {
        assert values != null;

        final ToBooleanFunction<T> function = mock(ToBooleanFunction.class);

        when(function.applyAsBoolean(any(applyClass)))
                .thenAnswer(AdditionalAnswers.returnsElementsOf(values));

        return function;
    }
}
