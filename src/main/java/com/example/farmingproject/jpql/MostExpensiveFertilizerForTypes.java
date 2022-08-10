package com.example.farmingproject.jpql;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class MostExpensiveFertilizerForTypes {

    private String type;
    private String name;
}
