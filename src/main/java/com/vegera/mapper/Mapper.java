package com.vegera.mapper;

public interface Mapper<F, T> {
    T mapFrom(F entity);
}
