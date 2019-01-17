package com.springboot2.jpa.app.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot2.jpa.app.main.exception.ResourceNotFoundException;
import com.springboot2.jpa.app.main.model.AppUser;
import com.springboot2.jpa.app.main.repository.AppUserRepository;
/**
 * 
 * @author Naveen
 *
 */
@RestController
@RequestMapping("/api/appuser")
public class AppUserController {

	@Autowired
	private AppUserRepository appUserRepository;

	@GetMapping("/appuserDetails")
	public List<AppUser> getAllEmployees() {
		return appUserRepository.findAll();
	}

	@GetMapping("/appuser/{id}")
	public ResponseEntity<AppUser> getAppuserById(@PathVariable(value = "id") Integer appUserId)
			throws ResourceNotFoundException {
		AppUser employee = appUserRepository.findById(appUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + appUserId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/appuser")
	public AppUser createAppuser(@Valid @RequestBody AppUser appUser) throws ResourceNotFoundException {
		List<AppUser> appUserList = appUserRepository.findAll();
		AppUser existsAppUser = appUserList.stream()
				.filter(newUser -> newUser.getUserName().equals(appUser.getUserName())).findAny().orElse(null);
		if (existsAppUser != null) {
			throw new ResourceNotFoundException("UserName is exists :: " + appUser.getUserName());
		}
		return appUserRepository.save(appUser);
	}

	@PutMapping("/appuser/{id}")
	public ResponseEntity<AppUser> updateEmployee(@PathVariable(value = "id") Integer appUserId,
			@Valid @RequestBody AppUser appUserDetails) throws ResourceNotFoundException {
		AppUser appUser = appUserRepository.findById(appUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + appUserId));
		appUser.setUserName(appUserDetails.getUserName());
		appUser.setUserPass(appUserDetails.getUserPass());
		final AppUser updatedAppUser = appUserRepository.save(appUser);
		return ResponseEntity.ok(updatedAppUser);
	}

	@DeleteMapping("/appuser/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer appUserId)
			throws ResourceNotFoundException {
		AppUser appUser = appUserRepository.findById(appUserId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + appUserId));
		appUserRepository.delete(appUser);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
