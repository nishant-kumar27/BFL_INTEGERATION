package com.bfl.gencode.auth.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"access_token",
	"expires_in",
	"refresh_expires_in",
	"refresh_token",
	"token_type",
	"not-before-policy",
	"session_state",
	"scope"
})
public class AuthResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("expires_in")
	private Integer expiresIn;
	@JsonProperty("refresh_expires_in")
	private Integer refreshExpiresIn;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("not-before-policy")
	private Integer notBeforePolicy;
	@JsonProperty("session_state")
	private String sessionState;
	@JsonProperty("scope")
	private String scope;

	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	@JsonProperty("access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@JsonProperty("expires_in")
	public Integer getExpiresIn() {
		return expiresIn;
	}

	@JsonProperty("expires_in")
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	@JsonProperty("refresh_expires_in")
	public Integer getRefreshExpiresIn() {
		return refreshExpiresIn;
	}

	@JsonProperty("refresh_expires_in")
	public void setRefreshExpiresIn(Integer refreshExpiresIn) {
		this.refreshExpiresIn = refreshExpiresIn;
	}

	@JsonProperty("refresh_token")
	public String getRefreshToken() {
		return refreshToken;
	}

	@JsonProperty("refresh_token")
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@JsonProperty("token_type")
	public String getTokenType() {
		return tokenType;
	}

	@JsonProperty("token_type")
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	@JsonProperty("not-before-policy")
	public Integer getNotBeforePolicy() {
		return notBeforePolicy;
	}

	@JsonProperty("not-before-policy")
	public void setNotBeforePolicy(Integer notBeforePolicy) {
		this.notBeforePolicy = notBeforePolicy;
	}

	@JsonProperty("session_state")
	public String getSessionState() {
		return sessionState;
	}

	@JsonProperty("session_state")
	public void setSessionState(String sessionState) {
		this.sessionState = sessionState;
	}

	@JsonProperty("scope")
	public String getScope() {
		return scope;
	}

	@JsonProperty("scope")
	public void setScope(String scope) {
		this.scope = scope;
	}
}
