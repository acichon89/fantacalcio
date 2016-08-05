package com.javangarda.fantacalcio.user.application.data;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import com.javangarda.fantacalcio.user.application.model.entity.User;
import com.javangarda.fantacalcio.user.application.model.value.Role;
import com.javangarda.fantacalcio.user.application.model.value.UserStatus;

public class FantaCalcioUserTest {

	@Test
	public void creatingTest() {
		User user = new User();
		user.setEmail("andreas@skijumping.pl");
		user.setFullName("Andreas Withoelz");
		user.addRole(Role.ROLE_USER);
		user.setStatus(UserStatus.NOT_CONFIRMED);
		FantaCalcioUser fuc = new FantaCalcioUser(user, null);
		Assert.assertFalse(fuc.isEnabled());
		Assert.assertFalse(fuc.isAccountNonExpired());
		Assert.assertFalse(fuc.isAccountNonLocked());
		Assert.assertFalse(fuc.isCredentialsNonExpired());
		Assert.assertTrue(CollectionUtils.isEmpty(fuc.getSocialMediaTypes()));
		GrantedAuthority grantedAuthority = fuc.getAuthorities().iterator().next();
		Assert.assertEquals("ROLE_USER", grantedAuthority.getAuthority());
	}

}
