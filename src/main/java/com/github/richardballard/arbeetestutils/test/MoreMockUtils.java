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
import net.jcip.annotations.ThreadSafe;
import org.assertj.core.util.Lists;
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

@SuppressWarnings("unused")
@ThreadSafe
public enum MoreMockUtils {
  ;

  /**
   * Returns a mock unary operator that always returns {@code value}.
   */
  @SuppressWarnings("unchecked")
  public static @NotNull <E> UnaryOperator<E> mockUnaryOperatorSingleAnswer(final @Nullable E value) {
    final UnaryOperator<E> operator = mock(UnaryOperator.class);

    when(operator.apply(any()))
        .thenReturn(value);

    return operator;
  }

  /**
   * Returns a mock unary operator that returns the elements of {@code values}.
   */
  @SuppressWarnings("unchecked")
  public static @NotNull <E> UnaryOperator<E> mockUnaryOperatorMultipleAnswers(final @NotNull Iterable<E> values) {

    final UnaryOperator<E> operator = mock(UnaryOperator.class);

    when(operator.apply(any()))
        .thenAnswer(returnsElementsOf(Lists.newArrayList(values)));

    return operator;
  }

  /**
   * Returns a mock function that always returns {@code value}.
   */
  @SuppressWarnings("unchecked")
  public static @NotNull <K, V> Function<K, V> mockFunctionSingleAnswer(final @NotNull Class<? extends K> keyClass,
                                                                        final @Nullable V value) {

    final Function<K, V> function = mock(Function.class);

    when(function.apply(any()))
        .thenReturn(value);

    return function;
  }

  /**
   * Returns a mock function that returns the elements of {@code values}.
   */
  @SuppressWarnings("unchecked")
  public static @NotNull <K, V> Function<K, V> mockFunctionMultipleAnswers(final @NotNull Class<? extends K> keyClass,
                                                                           final @NotNull Iterable<V> values) {

    final Function<K, V> function = mock(Function.class);

    when(function.apply(any()))
        .thenAnswer(returnsElementsOf(Lists.newArrayList(values)));

    return function;
  }

  @SuppressWarnings("unchecked")
  public static @NotNull <V> Supplier<V> mockSupplierSingleAnswer(final @Nullable V value) {
    final Supplier<V> supplier = mock(Supplier.class);

    when(supplier.get())
        .thenReturn(value);

    return supplier;
  }

  @SuppressWarnings("unchecked")
  public static @NotNull <V> Supplier<V> mockSupplierMultipleAnswers(final @NotNull Iterable<V> values) {

    final Supplier<V> supplier = mock(Supplier.class);

    when(supplier.get())
        .thenAnswer(returnsElementsOf(Lists.newArrayList(values)));

    return supplier;
  }

  public static @NotNull BooleanSupplier mockBooleanSupplierSingleAnswer(final boolean value) {
    final BooleanSupplier supplier = mock(BooleanSupplier.class);

    when(supplier.getAsBoolean())
        .thenReturn(value);

    return supplier;
  }

  public static @NotNull BooleanSupplier mockBooleanSupplierMultipleAnswers(final @NotNull Iterable<Boolean> values) {

    final BooleanSupplier supplier = mock(BooleanSupplier.class);

    when(supplier.getAsBoolean())
        .thenAnswer(returnsElementsOf(Lists.newArrayList(values)));

    return supplier;
  }

  @SuppressWarnings("unchecked")
  public static @NotNull <T> ToBooleanFunction<T> mockToBooleanFunctionSingleAnswer(final @NotNull Class<T> applyClass,
                                                                                    final boolean value) {
    final ToBooleanFunction<T> function = mock(ToBooleanFunction.class);

    when(function.applyAsBoolean(any()))
        .thenReturn(value);

    return function;
  }

  @SuppressWarnings("unchecked")
  public static @NotNull <T> ToBooleanFunction<T> mockToBooleanFunctionMultipleAnswers(
      final @NotNull Class<T> applyClass,
      final @NotNull Iterable<Boolean> values) {

    final ToBooleanFunction<T> function = mock(ToBooleanFunction.class);

    when(function.applyAsBoolean(any()))
        .thenAnswer(returnsElementsOf(Lists.newArrayList(values)));

    return function;
  }

  @SuppressWarnings("unchecked")
  public static @NotNull <T> Callable<T> mockCallableSingleAnswer(final @Nullable T value) {

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
  public static @NotNull <T> Callable<T> mockCallableMultipleAnswers(final @NotNull Iterable<T> values) {

    final Callable<T> callable = mock(Callable.class);

    try {
      when(callable.call())
          .thenAnswer(returnsElementsOf(Lists.newArrayList(values)));
    }
    catch(final Exception exc) {
      // shouldn't happen - keeping compiler happy - RMB 2016/6/19
      Throwables.throwIfUnchecked(exc);
      throw new RuntimeException(exc);
    }

    return callable;
  }

  @SuppressWarnings("unchecked")
  public static @NotNull <T> Consumer<T> mockConsumer() {
    return mock(Consumer.class);
  }
}
