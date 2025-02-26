package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.config.WebMvcConfig;
import com.example.calendarManagement.interceptor.MdcInterceptor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.mockito.Mockito.*;

class WebMvcConfigTest {

    @Test
    void testAddInterceptors() {
        // Mock dependencies
        MdcInterceptor mockInterceptor = mock(MdcInterceptor.class);
        InterceptorRegistry mockRegistry = mock(InterceptorRegistry.class);

        // Create instance of WebMvcConfig
        WebMvcConfig config = new WebMvcConfig(mockInterceptor);

        // Call method
        config.addInterceptors(mockRegistry);

        // Verify that the interceptor was added
        verify(mockRegistry, times(1)).addInterceptor(mockInterceptor);
    }
}
