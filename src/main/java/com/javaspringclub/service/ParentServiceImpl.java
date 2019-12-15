package com.javaspringclub.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaspringclub.entity.ParentDto;
import com.javaspringclub.repository.ParentRepository;

@Service
public class ParentServiceImpl implements ParentService{
	
	@Autowired
	private ParentRepository parentRepository;
	

	
	@Override
	@Transactional
	public List<ParentDto> getParentDetails() {
		List<ParentDto> list = new ArrayList<ParentDto>();
		parentRepository.findAll().forEach(e -> list.add(e));
		return list;
	}



	@Override
	@Transactional
	public ParentDto getParentDetailsById(int parent_id) {
		ParentDto parentDto = parentRepository.findById(parent_id).get();
		return parentDto;
	}



	@Override
	@Transactional
	public void updateParentDetails(ParentDto parentDto) {
		ParentDto parent = parentRepository.findById(parentDto.getParent_id()).get();
		System.out.println("ParentFromDb : "+parent.getParent_task());
		
		if(parent != null){
			parentRepository.save(parentDto);
			System.out.println("Parent updated successfully : "+parent.getParent_task());
		}
		
	}



	@Override
	@Transactional
	public void deleteParentDetails(int parent_id) {
		parentRepository.deleteById(parent_id);
	}



	@Override
	@Transactional
	public void addParentInfo(ParentDto parentDto) {
		parentRepository.save(parentDto);
		
	}

	

}
