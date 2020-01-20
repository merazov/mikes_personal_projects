package com.mike.generics;

import com.google.common.reflect.TypeToken;

import lombok.Getter;

@Getter
public class IKnowMyType2<T> {
    @SuppressWarnings("serial")
    private TypeToken<T> type = new TypeToken<T>(this.getClass()) {};

    public Class<?> getClassX() {
        return getClass();
    }
}
