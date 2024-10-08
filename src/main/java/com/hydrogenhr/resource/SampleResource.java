package com.hydrogenhr.resource;

import com.hydrogenhr.core.constants.AppConstants;
import com.hydrogenhr.model.response.AppResponse;
import com.hydrogenhr.service.SampleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/30/24
 * Time: 4:48 PM
 */
@RestController
@RequestMapping("sample")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Sample API")
public class SampleResource {

    private final SampleService sampleService;

    @GetMapping
    public ResponseEntity<AppResponse<String>> sampleWithOutAuthority() {
        String response = sampleService.sampleMethod();
        return ResponseEntity.ok(AppResponse.<String>builder()
                .data(response)
                .message(AppConstants.RestMessage.success)
                .status(HttpStatus.OK.value())
                .build());
    }

    @GetMapping("secured")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasAuthority('PERMISSION_VIEW_USER')")
    public ResponseEntity<AppResponse<String>> sampleWithAuthority() {
        String response = sampleService.sampleMethod();
        return ResponseEntity.ok(AppResponse.<String>builder()
                .data(response)
                .message(AppConstants.RestMessage.success)
                .status(HttpStatus.OK.value())
                .build());
    }
}
