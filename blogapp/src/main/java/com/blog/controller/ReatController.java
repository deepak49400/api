package com.blog.controller;

import com.blog.dto.PostDto;
import com.blog.entities.Post;
import com.blog.mapper.PostMapper;
import com.blog.repositories.PostRepository;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ReatController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/rest/post/getAll")
    public List<PostDto> getAllLeads() {

        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
        return postDtos;

    }

    @PostMapping("/rest/post/create")
    public void saveOneLead(@RequestBody Post post) {


        postRepository.save(post);

    }
    @DeleteMapping("/rest/post/delete/{id}")
    public void deleteOneLead(@PathVariable("id") Long id) {


        postRepository.deleteById(id);


    }
    @GetMapping("/rest/post/get/{id}")
    public Post getPostByID(@PathVariable("id") Long id) {
        return postRepository.findById(id).get();
    }

    @GetMapping("/rest/post/search/{query}")
    public List<PostDto> getEmployeeByName(@PathVariable("query") String query) {
        List<Post> posts = postRepository.searchPosts(query);
        List<PostDto> postDtos = posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
        return postDtos;
    }
/*
@GetMapping("/rest/employee/get/{id}")
	public Employee getEmployeeByID(@PathVariable("id") int id) {
		return repository.retrieve(id);
	}

	@GetMapping("/rest/employee/getAll")
	//Returning is List is supported with JSON response only
	//If you want XML, then add a wrapper class as Root XML element, for example EmployeeList
	public List<Employee> getAllEmployees() {
		return repository.getAll();
	}

	@PostMapping("/rest/employee/create")
	public Employee createEmployee(@RequestBody Employee emp) {
		repository.store(emp);
		return emp;
	}

	@GetMapping("/rest/employee/search/{name}")
	public Employee getEmployeeByName(@PathVariable("name") String name) {
		return repository.search(name);
	}

	@DeleteMapping("/rest/employee/delete/{id}")
	public Employee deleteEmployeeByID(@PathVariable("id") int id) {
		return repository.delete(id);
	}

 */

}
