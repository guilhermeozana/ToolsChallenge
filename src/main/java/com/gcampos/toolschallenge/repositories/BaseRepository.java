package com.gcampos.toolschallenge.repositories;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public abstract class BaseRepository<T,I> {

    protected List<T> list = new ArrayList<>();

    public List<T> findAll(){
        return list;
    }

    public abstract Optional<T> findById(I id);

    public T save(T t) {
        list.add(t);
        return t;
    }

    public void remove(T t) {
        list.remove(t);
    }
}
