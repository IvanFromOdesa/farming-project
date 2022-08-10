package com.example.farmingproject.service;

import com.example.farmingproject.domain.Fertilizer;
import com.example.farmingproject.jpql.FertsNamesByProvider;
import com.example.farmingproject.repository.FertilizerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FertilizerService {

    private final FertilizerRepository fertilizerRepository;

    public List<Fertilizer> findAllFertilizers() {
        return (List<Fertilizer>) fertilizerRepository.findAll();
    }

    public Fertilizer findFertilizerById(Integer id) {
        try {
            Optional<Fertilizer> result = fertilizerRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any fertilizer with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveFertilizer(Fertilizer fertilizer) {
        fertilizerRepository.save(fertilizer);
    }

    public void deleteFertilizer(Integer id) throws EntityNotFoundException {
        Long count = fertilizerRepository.countById(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any fertilizer with id " + id);
        }
        fertilizerRepository.deleteById(id);
    }

    public List<Fertilizer> findOrganicFertilizers() {
        return fertilizerRepository.findOrganicFertilizers();
    }

    public List<Fertilizer> findUnusedFertilizers() {
        return fertilizerRepository.findUnusedFertilizers();
    }

    public Set<FertsNamesByProvider> findFertsNamesByProvider(String name) {
        Set<FertsNamesByProvider> set = new HashSet<>();
        return fertilizerRepository.findFertsNamesByProvider(name)
                .stream().flatMap(row -> {
                    set.add(new FertsNamesByProvider(
                            (String) row[0],
                            (String) row[1]
                    ));
                    return set.stream();
                }).collect(Collectors.toSet());
    }

    public void setColumnMostPopularFertilizer() {
        fertilizerRepository.setColumnMostPopularFertilizer();
    }
}
