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
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableCollection;
import net.jcip.annotations.ThreadSafe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.mockito.AdditionalAnswers.returnsElementsOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ThreadSafe
public enum MoreMockUtils {
    ;

    /**
     * Returns a mock unary operator that always returns {@code value}.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <E> UnaryOperator<E> mockUnaryOperatorSingleAnswer(@Nullable final E value) {
        final UnaryOperator<E> operator = mock(UnaryOperator.class);

        when(operator.apply(any()))
                .thenReturn(value);

        return operator;
    }

    /**
     * Returns a mock unary operator that returns the elements of {@code values}.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <E> UnaryOperator<E> mockUnaryOperatorMultipleAnswers(@NotNull final ImmutableCollection<E> values) {
        assert values != null;

        final UnaryOperator<E> operator = mock(UnaryOperator.class);

        when(operator.apply(any()))
                .thenAnswer(returnsElementsOf(values));

        return operator;
    }

    /**
     * Returns a mock function that always returns {@code value}.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <K, V> Function<K, V> mockFunctionSingleAnswer(@NotNull final Class<? extends K> keyClass,
                                                                 @Nullable final V value) {
        assert keyClass != null;

        final Function<K, V> function = mock(Function.class);

        when(function.apply(any()))
                .thenReturn(value);

        return function;
    }

    /**
     * Returns a mock function that returns the elements of {@code values}.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <K, V> Function<K, V> mockFunctionMultipleAnswers(@NotNull final Class<? extends K> keyClass,
                                                                    @NotNull final ImmutableCollection<V> values) {
        assert keyClass != null;
        assert values != null;

        final Function<K, V> function = mock(Function.class);

        when(function.apply(any()))
                .thenAnswer(returnsElementsOf(values));

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
    public static <V> Supplier<V> mockSupplierMultipleAnswers(@NotNull final ImmutableCollection<V> values) {
        assert values != null;

        final Supplier<V> supplier = mock(Supplier.class);

        when(supplier.get())
                .thenAnswer(returnsElementsOf(values));

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

        when(function.applyAsBoolean(any()))
                .thenReturn(value);

        return function;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> ToBooleanFunction<T> mockToBooleanFunctionMultipleAnswers(@NotNull final Class<T> applyClass,
                                                                                @NotNull final ImmutableCollection<Boolean> values) {
        assert values != null;

        final ToBooleanFunction<T> function = mock(ToBooleanFunction.class);

        when(function.applyAsBoolean(any()))
                .thenAnswer(returnsElementsOf(values));

        return function;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Callable<T> mockCallableSingleAnswer(@NotNull final T value) {
        assert value != null;

        final Callable<T> callable = mock(Callable.class);

        try {
            when(callable.call())
                    .thenReturn(value);
        }
        catch(final Exception exc) {
            // shouldn't happen - keeping compiler happy - RMB 2016/6/19
            Throwables.throwIfUnchecked(exc);
            throw new RuntimeException(exc);
        }

        return callable;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Callable<T> mockCallableMultipleAnswers(@NotNull final ImmutableCollection<T> values) {
        assert values != null;

        final Callable<T> callable = mock(Callable.class);

        try {
            when(callable.call())
                    .thenAnswer(returnsElementsOf(values));
        }
        catch(final Exception exc) {
            // shouldn't happen - keeping compiler happy - RMB 2016/6/19
            Throwables.throwIfUnchecked(exc);
            throw new RuntimeException(exc);
        }

        return callable;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Consumer<T> mockConsumer() {
        return mock(Consumer.class);
    }


}
