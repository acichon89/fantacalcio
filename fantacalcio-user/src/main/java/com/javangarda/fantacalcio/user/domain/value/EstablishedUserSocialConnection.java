package com.javangarda.fantacalcio.user.domain.value;

import lombok.Data;

@Data
public class EstablishedUserSocialConnection {

	private SocialMediaType socialMediaType;
	private String email;
	private String fullName;
}
