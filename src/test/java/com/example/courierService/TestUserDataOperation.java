package com.example.courierService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.courierService.model.UserAdmin;
import com.example.courierService.model.UserCourier;
import com.example.courierService.model.UserForwarder;
import com.example.courierService.repository.UserAdminRepository;
import com.example.courierService.repository.UserCourierRepository;
import com.example.courierService.repository.UserForwarderRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestUserDataOperation {

	@Mock
	private UserAdminRepository userAdminRepository;

	@Mock
	private UserCourierRepository userCourierRepository;

	@Mock
	private UserForwarderRepository userForwarderRepository;

	@Test
	public void userAdmin(){
		UserAdmin exampleUser = new UserAdmin(null, "admin", "admin", "6000", "6000");
		when(userAdminRepository.findByLogin("admin")).thenReturn(Optional.of(exampleUser));
		Optional<UserAdmin> userFromRepository = userAdminRepository.findByLogin("admin");
		assertEquals("6000", userFromRepository.get().getLogin());
		assertEquals("6000", userFromRepository.get().getPassword());
		assertEquals("admin", userFromRepository.get().getLastName());
	}

	@Test
	public void userCourier(){
		UserCourier userCourier = new UserCourier(null, "userCourier", "userCourier", "400", "400", new ArrayList<>(), null);
		when(userCourierRepository.findByLogin("400")).thenReturn(Optional.of(userCourier));
		Optional<UserCourier> userFromRepository = userCourierRepository.findByLogin("400");
		assertEquals("400", userFromRepository.get().getLogin());
		assertEquals("400", userFromRepository.get().getPassword());
		assertEquals("userCourier", userFromRepository.get().getLastName());
		assertEquals("userCourier", userFromRepository.get().getFirstName());
	}

	@Test
	public void userForwarder(){
		UserForwarder userForwarder = new UserForwarder(null, "userForwarder", "userForwarder", "5000", "5000",
				Collections.emptyList(), Collections.emptyList());

		when(userForwarderRepository.findByLogin("5000")).thenReturn(Optional.of(userForwarder));
		Optional<UserForwarder> userFromRepository = userForwarderRepository.findByLogin("5000");
		assertEquals("5000", userFromRepository.get().getLogin());
		assertEquals("5000", userFromRepository.get().getPassword());
		assertEquals("userForwarder", userFromRepository.get().getLastName());
		assertEquals("userForwarder", userFromRepository.get().getFirstName());
	}


}
