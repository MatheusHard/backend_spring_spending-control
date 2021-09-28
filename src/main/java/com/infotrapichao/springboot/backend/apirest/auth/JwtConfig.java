package com.infotrapichao.springboot.backend.apirest.auth;

public class JwtConfig {

	public static final String CHAVE_SECRETA =  "chave.secreta.1q2w3e5r6u";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpQIBAAKCAQEAy/8O8QWf3FRvI3L9KGFKESywzd1fPhw57zL8ykIiquqfzB7S\r\n"
			+ "IS3/TOMHBx2AOnziHsDNHI91/oAcCgRKauKWW5OlxqON+JDT2efOxSQY8RUf7Nsh\r\n"
			+ "t/MfGHmKOI4J8j5SRTmj8+hFR4dyrfrOadBd8790DQxV5EUgovQCrZy8+ZZLdFBz\r\n"
			+ "qJTrc9bwI43LshbUj98rgGUbrFpCXly0tk/+7lmDQ+NQFkJHjSBp/rSfwI+4Ud39\r\n"
			+ "l4EVIhSiNo8Ir1i4CGFvDgXKUHdHuMioww6V+k4zq/f0yu/IKzt1Ba2XIfaZUhpr\r\n"
			+ "z7FglK23gjOVqXqoXKYS4CwGj5OHgCmlRond6wIDAQABAoIBAQC4rDkZhq72plVK\r\n"
			+ "cTGvGYwUyjFw3IP5VVI5Mh5Y99AsjGSHRCuUL/Db6PaWwWUxDZciogXNYi4+bDl1\r\n"
			+ "8/ZulpvpYVuE3NGMgYfXz5uHKquNrEMPiD0ZKvYukPDJTATDERonYwwCpXX6UCtI\r\n"
			+ "RzIP5rr3C44CtfUXxmDKRD4cbzVAd+omGcYNV48lRqwKXQvCaJNpHDoR9uxFjvpc\r\n"
			+ "kbCzg5ax2jm4yDavribFgDs057reahBwCY8Ev+wz59DdiOtaGZtWS45hYBP87aut\r\n"
			+ "NgLFiP1DllFYH137KU3rVNE9mrSCqN1w75dKzmCdDaPbNt9woL/hCNCz3vqYtGHt\r\n"
			+ "h+uq8OhBAoGBAOaZWeZ8A+G5Pol/uQ/QvceB6922X08WGsdhdsEP1wKMP8bZUG8Q\r\n"
			+ "686SzDxCWIyYALOtwpwh61w4iwQOVG1/TwbO3jhTDwKgbU16jYHxUGSwj4TLMvOS\r\n"
			+ "0n+G0ESAfUkaVDcwVHFXn2UBUsVBQHgJaDQpWc+XlOoR1PpRSCREtoALAoGBAOJ3\r\n"
			+ "ifupVqjJR5/NzCwtr54kCkEQSXBZW+lq1WciS/IhCUJXws6fWvVImxZHfp3kJDzF\r\n"
			+ "/xH8WmI7BCL9kXdQNK4SJMdgHH/f11XBJJAnH5KS+aaw0a56cS/jnb64lGOQY8uB\r\n"
			+ "6xXy9NIXEBZNGyEl/lXm5RgcG+9+Aw+kDrtqHWWhAoGATZekLG3mUHmolVOoza2L\r\n"
			+ "bipFV2EaSN5DPHuLHgNacb0HaL2O1xB2iBfVKSGZ8hTjh5IcPbUcIPfVrxOzTPTd\r\n"
			+ "foj1NTadgmDA5qUqIGM2jerrH9/P0S3CLq8KPUKO5Z46YCefAlRdifscAsumCb4A\r\n"
			+ "liyjz7pNnUVKyL+46ptEXWkCgYEAreMddre6i5UpQsHRIKbfrqx7lyO7T+G9Ggfm\r\n"
			+ "3e7Oo5bMNj2Onfpr6+SY+E1jIjb3wBZDWPsMJ1qrPXa3iAZ0vKe/x7q7h1BHVlgc\r\n"
			+ "C/eOXYwu1tfz2GW5nQzHpZaykZRfm/YgnNTVOK9uscD4sMYseKb2h0GmDYkTLqJI\r\n"
			+ "kYzUD6ECgYEAhNh1AvA8COQRc36+SqBOwVqjSbGGPrIJiivu/VV2zMTr4oEYYNGT\r\n"
			+ "j2Y0rtYsuM1xKb7alyYplBXMnJ8RJ70dSXfQlazO5F6thWQnhqSNgAeKzdO2ajZs\r\n"
			+ "ufBNL5ipNL07ZYts1DHk7D7wbmvHi2S+PF/A+2V7HC+kfRyRAyV0paE=\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy/8O8QWf3FRvI3L9KGFK\r\n"
			+ "ESywzd1fPhw57zL8ykIiquqfzB7SIS3/TOMHBx2AOnziHsDNHI91/oAcCgRKauKW\r\n"
			+ "W5OlxqON+JDT2efOxSQY8RUf7Nsht/MfGHmKOI4J8j5SRTmj8+hFR4dyrfrOadBd\r\n"
			+ "8790DQxV5EUgovQCrZy8+ZZLdFBzqJTrc9bwI43LshbUj98rgGUbrFpCXly0tk/+\r\n"
			+ "7lmDQ+NQFkJHjSBp/rSfwI+4Ud39l4EVIhSiNo8Ir1i4CGFvDgXKUHdHuMioww6V\r\n"
			+ "+k4zq/f0yu/IKzt1Ba2XIfaZUhprz7FglK23gjOVqXqoXKYS4CwGj5OHgCmlRond\r\n"
			+ "6wIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
	
}
