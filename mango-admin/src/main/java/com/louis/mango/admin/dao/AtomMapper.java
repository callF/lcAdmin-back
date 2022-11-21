package com.louis.mango.admin.dao;

import com.louis.mango.admin.model.Atom;

import java.util.LinkedHashMap;
import java.util.List;

public interface AtomMapper {
    void addAtom(Atom atom);

    List<Atom> getAtoms(LinkedHashMap map);

    Atom getById(Long id);

    void delAtom(Long id);

    Atom getByType(String type);
}
