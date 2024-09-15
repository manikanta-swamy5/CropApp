package com.cg.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

	private String message;
	private String details;
	private String httpCodeMessage;
	private LocalDateTime timestamp;
}
