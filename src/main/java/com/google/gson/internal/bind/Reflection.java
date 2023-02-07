/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gson.internal.bind;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class Reflection {
  /**
   * Finds a compatible runtime type if it is more specific
   */
  public static Type getRuntimeTypeIfMoreSpecific(Type type, Object value) {
    if (value != null
        && (type == Object.class || type instanceof TypeVariable || type instanceof Class<?>)) {
      type = (Class<?>) value.getClass();
    }
    return type;
  }

  // TODO: this should use Joel's unsafe constructor stuff
  public static <T> T newInstance(Constructor<T> constructor) {
    try {
      Object[] args = null;
      return constructor.newInstance(args);
    } catch (InstantiationException e) {
      // TODO: JsonParseException ?
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      // TODO: don't wrap if cause is unchecked!
      // TODO: JsonParseException ?
      throw new RuntimeException(e.getTargetException());
    } catch (IllegalAccessException e) {
      throw new AssertionError(e);
    }
  }

}
