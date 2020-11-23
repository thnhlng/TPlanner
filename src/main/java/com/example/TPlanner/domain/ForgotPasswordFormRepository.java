package com.example.TPlanner.domain;

import org.springframework.data.repository.CrudRepository;

public interface ForgotPasswordFormRepository extends CrudRepository<ForgotPasswordForm, Long>{

	ForgotPasswordForm findByEmail(String email);
}
