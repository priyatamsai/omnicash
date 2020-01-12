package com.example.omnicash;

import com.example.omnicash.model.Outlet;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

public class Pair<A, B> {
    @Getter
    @Setter
    private A first;
    @Getter
    @Setter
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }


}
