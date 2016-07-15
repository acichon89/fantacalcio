package com.javangarda.fantacalcio.user.application.data;

import com.javangarda.fantacalcio.user.application.model.value.SocialMediaType;

import lombok.Data;

@Data
public class EstablishedUserSocialConnection {

	private SocialMediaType socialMediaType;
	private String email;
	private String fullName;
}
