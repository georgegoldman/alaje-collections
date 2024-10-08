package com.hydrogenhr.service.impl;

import com.hydrogenhr.service.SampleService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/30/24
 * Time: 4:47 PM
 */
@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    @Override
    public String sampleMethod() {
        return "Sample Response";
    }
}
