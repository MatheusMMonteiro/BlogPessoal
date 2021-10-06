package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.TemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long> {

	//public List<TemaModel> findAllByEquipamentoContainingIgnoreCase(String Equipamento);
	
	public Optional<TemaModel> findById(long id);
	
	
	
}
