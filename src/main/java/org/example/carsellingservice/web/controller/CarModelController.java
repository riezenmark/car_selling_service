package org.example.carsellingservice.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/models")
@PreAuthorize("principal.email == 'riezenmark@gmail.com'")
public class CarModelController {

}
